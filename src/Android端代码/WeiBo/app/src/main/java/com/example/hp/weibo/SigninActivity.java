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

/**
 * 手机号登录页面
 */
public class SigninActivity extends AppCompatActivity {

    ///手机号
    private String Phone;
    ///获取验证码按钮
    private Button _btnPhone;
    ///手机号编辑框
    private EditText _etPhone;
    ///切换登录方式文本框
    private TextView _tvBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ///初始化控件
        _btnPhone = (Button) findViewById(R.id.btnPhone);
        _etPhone = (EditText) findViewById(R.id.etPhone);
        _tvBtn1 = (TextView) findViewById(R.id.tvBtn1);

        ///获取验证码按钮点击事件
        _btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///获取手机号
                Phone = _etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(Phone)) {
                    ///手机号为空，提示用户手机号不能为空
                    Toast.makeText(getApplicationContext(), "手机号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    ///手机号不为空提示，跳转页面
                    Intent intent = new Intent(SigninActivity.this, CodeActivity.class);
                    intent.putExtra("phone", Phone);
                    startActivity(intent);
                }
            }
        });

        ///切换登录方式文本框点击事件
        _tvBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, PassActivity.class);
                startActivity(intent);
            }
        });
    }
}
