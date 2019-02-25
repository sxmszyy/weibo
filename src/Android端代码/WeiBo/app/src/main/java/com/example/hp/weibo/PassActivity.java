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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 手机号密码登录页面
 *
 * 根据服务器返回的不同的值，进行登录，登录失败等操作
 */
public class PassActivity extends AppCompatActivity {

    ///手机号
    private String Phone;
    ///密码
    private String PassWord;
    ///登录按钮
    private Button _btnSignin;
    ///手机编辑框
    private EditText _etPhone;
    ///密码编辑框
    private EditText _etPassWord;
    ///登录方式切换按钮
    private TextView _tvBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);

        ///初始化控件
        _btnSignin = (Button) findViewById(R.id.btnSignin);
        _etPhone = (EditText) findViewById(R.id.etPhone);
        _etPassWord = (EditText) findViewById(R.id.etPassWord);
        _tvBtn1 = (TextView) findViewById(R.id.tvBtn1);

        ///登录按钮点击事件
        _btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///进行登录
                signin();
            }
        });

        ///登录方式切换按钮点击事件
        _tvBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }


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

    /**
     * 登录校验
     *
     * 进行手机号没密码校验
     */
    private void signin(){
        ///获取手机号
        Phone = _etPhone.getText().toString().trim();
        ///获取密码
        PassWord = _etPassWord.getText().toString().trim();
        if (TextUtils.isEmpty(Phone) || TextUtils.isEmpty(PassWord)) {
            ///手机号或密码为空，提示用户手机号和密码不能为空
            Toast.makeText(getApplicationContext(), "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            ///手机号和密码动不为空，提交数据给服务器
            RequestBody requestBody = new FormBody.Builder()
                    .add("phone", Phone)// 新增参数
                    .add("password", PassWord)
                    .build();
            //10.13.188.222
            //172.20.10.6
            HttpUtil.getByOkHttp("signin.jsp", requestBody, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    /// 联网失败，提示用户网络异常
                    show("网络异常");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ///获取返回值
                    String xmlData = response.body().string().trim();
                    //show(xmlData);
                    ///转化为int形式
                    int data = Integer.parseInt(xmlData);
                    if (data == 1) {
                        ///返回值为1，提示用户登录成功，跳转页面
                        show("登录成功");
                        Intent intent = new Intent(PassActivity.this, WeiBoActivity.class);
                        intent.putExtra("phone", Phone);
                        startActivity(intent);
                    }else{
                        ///返回值为0，提示用户账号或密码错误
                        show("账号或密码错误");
                    }
                }
            });
        }
    }
}
