package com.example.myapplication;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.Login.Account;
import com.example.myapplication.utils.Utils;
import com.mob.MobSDK;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.json.JSONArray;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends Activity {

    // UI references.
    private EditText telephone;
    private EditText test;
    private EditText password;
    private EditText passwordAgain;
    private Button registerButton;
    private Button sendTestButton;

    boolean requireSuccessfully;//标记验证码发送申请成功
    int codeMatched = 0;//验证码是否正确,0错误，-1正确
    private int TEST_TIME_SPACE = 120;//发送验证码短信的时间间隔
    private enum Result{VALID,TELEPHONE_ERROR,TEST_ERROR,PASSWORD_TOO_SHORT,
        PASSWORD_FORMAT_ERROR,PASSWORD_AGAIN_UNMATCHED,HAS_REGISTERED}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MobSDK.init(this);

        telephone = (EditText)findViewById(R.id.telephone);
        test = (EditText)findViewById(R.id.test);
        password = (EditText)findViewById(R.id.passwordInput);
        passwordAgain = (EditText)findViewById(R.id.passwordInputRefine);

        registerButton = (Button) findViewById(R.id.registerButton);
        sendTestButton = (Button) findViewById(R.id.sendRefineText);

        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);

        //为发送验证码按钮绑定事件处理
        sendTestButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String telephoneText = telephone.getText().toString();
                if(isTelephoneValid(telephoneText)) {
                    //SMSSDK.getVerificationCode("86", telephoneText);
                    requireSuccessfully = false;
                    codeMatched = 0;
                    sendTestButton.setClickable(false);

                    //开一个新线程去处理，保证其访问结果出现后再进行下一步操作；
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                            SMSSDK.getVerificationCode("86", telephoneText);
                        }
                    });
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    sendTestButton.setTextColor(getResources().getColor(R.color.smssdk_gray));
                    sendTestButton.setClickable(false);
                    sendTestButton.setBackgroundColor(getResources().getColor(R.color.smssdk_bg_gray));
                    handler.sendEmptyMessage(0);
                }
                else{
                    telephone.setError("手机号格式有误");
                }
            }
        });

        //为注册按钮绑定事件处理
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String telephoneText = telephone.getText().toString();
                final String testText = test.getText().toString();

                registerButton.setTextColor(getResources().getColor(R.color.smssdk_gray));
                registerButton.setBackgroundColor(getResources().getColor(R.color.smssdk_bg_gray));
                registerButton.setClickable(false);
                switch (attemptRegister()){
                    case VALID:{
                        hasRegistered(telephoneText,new Handler(){
                            @Override
                            public void handleMessage(@NonNull Message msg) {
                                super.handleMessage(msg);
                                boolean result = (boolean)msg.obj;
                                if(result){
                                    telephone.setError("该手机号已被注册");
                                    telephone.setText("");
                                    telephone.requestFocus();
                                }
                                else{
                                    testValid(telephoneText,testText);
                                }
                            }
                        });
                    };break;
                    case TELEPHONE_ERROR:{
                        telephone.setError("手机号格式有误");
                    };break;
                    case PASSWORD_TOO_SHORT:{
                        password.setError("密码过短");
                        password.setText("");
                        passwordAgain.setText("");
                        password.requestFocus();
                    };break;
                    case PASSWORD_FORMAT_ERROR:{
                        password.setError("密码格式有误");
                        password.setText("");
                        passwordAgain.setText("");
                        password.requestFocus();
                    };break;
                    case PASSWORD_AGAIN_UNMATCHED:{
                        password.setError("两次输入的密码不一致");
                        password.setText("");
                        passwordAgain.setText("");
                        password.requestFocus();
                    };break;
                }
                registerButton.setTextColor(getResources().getColor(R.color.sortpagetextcolor));
                registerButton.setClickable(true);
                registerButton.setBackgroundColor(getResources().getColor(R.color.sortpagebgcolor));

            }
        });


    }
    //根据手机号和密码，存储新用户的信息
    private void storeNewCustomer(String telephoneText, String passwordText, final Handler handler) {
        Account account = Account.getAccount();
        account.setTelephone(telephoneText);
        account.setPassword(passwordText);
        Utils.mysql("INSERT INTO users (telephone,password,nick_name) VALUES ('"+telephoneText+"','"+passwordText+"','用户')",new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                handler.sendEmptyMessageDelayed(0x11,1600);
            }
        });
    }

    //为了让验证码两分钟内只能发送一次,改变按钮进度
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what<TEST_TIME_SPACE){
                sendTestButton.setText("重新发送（"+(TEST_TIME_SPACE-msg.what)+")");

                sendEmptyMessageDelayed(msg.what+1,1000);
            }
            else{
                sendTestButton.setTextColor(getResources().getColor(R.color.sortpagetextcolor));
                sendTestButton.setClickable(true);
                sendTestButton.setBackgroundColor(getResources().getColor(R.color.sortpagebgcolor));
            }
        }
    } ;

    private Result attemptRegister() {
        String telephoneText = telephone.getText().toString();
        String passwordText = password.getText().toString();
        String passwordAgainText = passwordAgain.getText().toString();
        if(!isTelephoneValid(telephoneText))
            return Result.TELEPHONE_ERROR;
        if(!isPasswordLengthValid(passwordText))
            return Result.PASSWORD_TOO_SHORT;
        if(!isPasswordFormatValid(passwordText))
            return Result.PASSWORD_FORMAT_ERROR;
        if(!passwordText.equals(passwordAgainText))
            return Result.PASSWORD_AGAIN_UNMATCHED;

        return Result.VALID;
    }

    //与数据库比对检测是否以被注册
    private void hasRegistered(String telephoneText, final Handler handler) {
         Utils.mysql("SELECT * FROM users WHERE telephone='"+telephoneText+"'",new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray results = (JSONArray) msg.obj;
                Message msg2 = new Message();
                if(results != null && results.length() > 0){
                    msg2.obj = true;
                }
                else{
                    msg2.obj = false;
                }
                handler.sendMessage(msg2);
            }
        });
    }

    private boolean isTelephoneValid(String telephoneText) {
        return telephoneText.matches("1\\d{10}");
    }

    private void testValid(final String telephoneText, final String testText) {
        LemonBubble.showRoundProgress(RegisterActivity.this,"注册中...");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 提交验证码，其中的code表示验证码，如“1357”
                SMSSDK.submitVerificationCode("86", telephoneText, testText);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isPasswordLengthValid(String passwordText) {
        int length = passwordText.length();
        return length > 7 && length <= 20;
    }

    private boolean isPasswordFormatValid(String passwordText) {
        return passwordText.matches("[a-zA-z]\\w+");
    }

    //用于处理验证码发送请求和接收验证的处理器
    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    String telephoneText = telephone.getText().toString();
                    String passwordText = password.getText().toString();
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            requireSuccessfully = true;
                        } else {
                            return false;
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            LemonBubble.showRight(RegisterActivity.this,"注册成功",1500);
                            //registerButton.setProgress(100);
                            //向服务器插入新数据
                            storeNewCustomer(telephoneText,passwordText,new Handler(){
                                @Override
                                public void handleMessage(@NonNull Message msg) {
                                    super.handleMessage(msg);
                                    //之后结束当前页
                                    RegisterActivity.this.finish();
                                }
                            });

                        } else {
                            // TODO 处理错误的结果
                            test.setError("验证码错误");
                            return false;
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };

//

}

