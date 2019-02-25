package com.example.hp.weibo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 验证码处理页面
 *
 * 根据服务器返回的不同的值，进行登录，注册，登录失败等操作
 *
 */

public class CodeActivity extends AppCompatActivity {

    ///登录按钮
    private Button _btnSignin;
    ///验证码输入框
    private EditText _etCode;
    ///显示手机号文本框
    private TextView _tvPhone;
    ///手机号
    private String Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        ///获取手机号
        Intent intent = getIntent();
        Phone = intent.getStringExtra("phone");
        ///请求发送验证码
        SMSSDK.getVerificationCode("86", Phone);

        ///初始化控件
        _btnSignin = (Button) findViewById(R.id.btnSignin);
        _etCode = (EditText) findViewById(R.id.etCode);
        _tvPhone = (TextView) findViewById(R.id.tvPhone);

        ///修改控件值
        _tvPhone.setText(Phone);

        ///登录按钮点击事件
        _btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///获取验证码编辑框中的验证码
                String code = _etCode.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    ///如果为空提示用户验证码不能为空
                    Toast.makeText(getApplicationContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    ///如果不为空，将验证码提交给第三方服务器
                    SMSSDK.submitVerificationCode("+86", Phone, code);
                }
            }
        });

        ///注册回调
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected void onDestroy() {
        ///防止内存泄漏
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    /**
     * 回调处理
     */
    private EventHandler eventHandler = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            super.afterEvent(event, result, data);
            if (result == SMSSDK.RESULT_COMPLETE) {
                ///回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    ///提交验证码成功
                    show("提交验证码成功");
                    /// 向服务器提交数据
                    RequestBody requestBody = new FormBody.Builder()
                            .add("phone", Phone)// 新增参数
                            .build();
                    HttpUtil.getByOkHttp("add.jsp", requestBody, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            /// 联网失败，提示用户网络异常
                            show("网络异常");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            ///获取返回值
                            String xmlData = response.body().string().trim();
                            ///转化为int形式
                            int data = Integer.parseInt(xmlData);
                            //String responseData = parseXMLWithPull(xmlData);
                            ///返回值为2，提示用户登录成功，跳转页面
                            if (data == 2) {
                                show("登录成功");
                                Intent intent = new Intent(CodeActivity.this, WeiBoActivity.class);
                                intent.putExtra("phone", Phone);
                                startActivity(intent);
                            }
                            ///返回值为1，提示用户注册成功，跳转页面
                            else if (data == 1){
                                show("注册成功");
                                Intent intent = new Intent(CodeActivity.this, ConfirmPasswordActivity.class);
                                intent.putExtra("phone", Phone);
                                startActivity(intent);
                            }
                            ///返回值为0，提示用户注册失败
                            else{
                                show("注册失败");
                            }
                            //show(responseData);
                        }
                    });
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    ///获取验证码成功
                    show("验证码已发送至您手机");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    ///返回支持发送验证码的国家列表
                }
            } else {
                ///验证码错误
                show("验证码错误");
            }
        }
    };

    /**
     * 根据不同操作，给用户不同提示
     *
     * @param response 要显示的值
     */
    private void show(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
