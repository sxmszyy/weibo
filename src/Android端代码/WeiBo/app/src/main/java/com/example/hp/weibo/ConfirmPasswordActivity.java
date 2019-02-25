package com.example.hp.weibo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 注册确认密码页面
 *
 * 根据服务器返回的不同的值，进行注册，注册失败等操作
 */

public class ConfirmPasswordActivity extends AppCompatActivity {

    ///手机号
    private String Phone;
    ///密码1
    private String PassWord1;
    ///密码2
    private String PassWord2;
    ///用户名
    private String Name;
    ///注册按钮
    private Button _btnConfirm;
    ///用户名编辑框
    private EditText _etName;
    ///密码1编辑框
    private EditText _etPassWord1;
    ///密码2编辑框
    private EditText _etPassWord2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);

        ///获取手机号
        Intent intent = getIntent();
        Phone = intent.getStringExtra("phone");

        ///初始化控件
        _btnConfirm = (Button) findViewById(R.id.btnConfirm);
        _etPassWord1 = (EditText) findViewById(R.id.etPassWord1);
        _etPassWord2 = (EditText) findViewById(R.id.etPassWord2);
        _etName = (EditText) findViewById(R.id.etName);

        ///注册按钮点击事件
        _btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///进行确认
                confirm();
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
     * 确认密码，用户名
     */
    private void confirm() {
        ///获取密码1
        PassWord1 = _etPassWord1.getText().toString().trim();
        ///获取密码2
        PassWord2 = _etPassWord2.getText().toString().trim();
        ///获取用户名
        Name = _etName.getText().toString().trim();
        if (TextUtils.isEmpty(PassWord1) || TextUtils.isEmpty(PassWord2)) {
            ///密码1或密码2为空，提示用户密码不能为空
            Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Name)){
            ///用户名为空，提示用户用户名不能为空
            Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (PassWord1.equals(PassWord2)) {
                ///用户名和密码动不为空，提交数据给服务器
                RequestBody requestBody = new FormBody.Builder()
                        .add("phone", Phone)// 新增参数
                        .add("password", PassWord1)
                        .add("name", Name)
                        .build();
                HttpUtil.getByOkHttp("updateuser.jsp", requestBody, new Callback() {
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
                        if (data == 1) {
                            ///返回值为1，提示用户成功，跳转页面
                            show("成功");
                            Intent intent = new Intent(ConfirmPasswordActivity.this, WeiBoActivity.class);
                            intent.putExtra("phone", Phone);
                            startActivity(intent);
                        }else{
                            ///返回值为0，提示用户失败
                            show("失败");
                        }
                        //show(xmlData);
                    }
                });
            }
            else{
                ///密码1和密码2不一致，提示用户密码不一致
                Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
