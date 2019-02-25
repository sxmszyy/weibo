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
 * 转发微博页面
 */
public class ZhuanfaActivity extends AppCompatActivity {

    ///手机号
    private String Phone;
    ///图片
    private String Image;
    ///正文
    private String Word;
    ///转发正文
    private String zhuanWord;
    ///微博号
    private String Weibonum;
    ///微博名
    private String Weiboname;
    ///时间
    private String Time;
    ///返回文本框
    TextView _tvBack;
    ///转发按钮
    Button _btnZhuanfa;
    ///正文编辑框
    EditText _etWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanfa);

        ///获取手机号、图片、转发正文、微博号、微博名
        Intent intent = getIntent();
        Phone = intent.getStringExtra("phone");
        Image = intent.getStringExtra("image");
        zhuanWord = intent.getStringExtra("word");
        Weibonum = intent.getStringExtra("weibonum");
        Weiboname = intent.getStringExtra("weiboname");

        ///初始化控件
        _etWord = (EditText) findViewById(R.id.etWord);
        _tvBack = (TextView)findViewById(R.id.tvBack);
        ///返回文本框点击事件
        _tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZhuanfaActivity.this, WeiBoActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _btnZhuanfa = (Button) findViewById(R.id.btnZhuanfa);
        ///转发按钮点击事件
        _btnZhuanfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///获取正文
                Word = _etWord.getText().toString().trim();
                ///正文不为空
                ///获取系统时间
                Calendar calendar = Calendar.getInstance();
                //年
                int year = calendar.get(Calendar.YEAR);
                //月
                int month = calendar.get(Calendar.MONTH);
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
                        .add("word", Word + "   @" + Weiboname + "   " + zhuanWord)
                        .add("weibonum", Weibonum)
                        .add("image", Image)
                        .add("time", Time)
                        .build();
                HttpUtil.getByOkHttp("zhuanfaweibo.jsp", requestBody, new Callback() {
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
                            ///返回值为1，提示用户转发成功，跳转页面
                            show("转发成功");
                            Intent intent = new Intent(ZhuanfaActivity.this, WeiBoActivity.class);
                            intent.putExtra("phone", Phone);
                            startActivity(intent);
                        }else{
                            ///返回值为0，提示用户发表失败
                            show("转发失败");
                        }
                    }
                });
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
