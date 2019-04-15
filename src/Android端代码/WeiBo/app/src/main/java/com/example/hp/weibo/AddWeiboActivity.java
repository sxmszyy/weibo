package com.example.hp.weibo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 发表微博页面
 */
public class AddWeiboActivity extends AppCompatActivity {

    ///手机号
    private String Phone;
    ///正文
    private String Word;
    ///时间
    private String Time;
    ///返回文本框
    private TextView _tvBack;
    ///发送按钮
    private Button _btnFasong;
    ///正文编辑框
    private EditText _etWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weibo);

        ///获取手机号
        Intent intent = getIntent();
        Phone = intent.getStringExtra("phone");

        ///初始化控件
        _etWord = (EditText) findViewById(R.id.etWord);
        _tvBack = (TextView)findViewById(R.id.tvBack);
        ///返回文本框点击事件
        _tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddWeiboActivity.this, WeiBoActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _btnFasong = (Button) findViewById(R.id.btnFasong);
        ///发送按钮点击事件
        _btnFasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///获取正文
                Word = _etWord.getText().toString().trim();
                if (!Word.equals("")) {
                    ///正文不为空
                    ///获取系统时间
                    Calendar calendar = Calendar.getInstance();
                    //年
                    int year = calendar.get(Calendar.YEAR);
                    //月
                    int month = calendar.get(Calendar.MONTH) + 1;
                    //日
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    //小时
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    //分钟
                    int minute = calendar.get(Calendar.MINUTE);
                    //秒
                    int second = calendar.get(Calendar.SECOND);
                    //2018-12-18 20:04:01.00
                    Time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
                    ///向服务器提交数据
                    RequestBody requestBody = new FormBody.Builder()
                            .add("phone", Phone)// 新增参数
                            .add("word", Word)
                            .add("time", Time)
                            .build();
                    HttpUtil.getByOkHttp("addweibo.jsp", requestBody, new Callback() {
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
                                ///返回值为1，提示用户发表成功，跳转页面
                                show("发表成功");
                                Intent intent = new Intent(AddWeiboActivity.this, WeiBoActivity.class);
                                intent.putExtra("phone", Phone);
                                startActivity(intent);
                            } else {
                                ///返回值为0，提示用户发表失败
                                show("发表失败");
                            }
                        }
                    });
                } else {
                    ///正文为空，提示用户内容不能没空
                    show("内容不能为空");
                }
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
}
