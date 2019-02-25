package com.example.hp.weibo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * 添加评论页面
 */

public class AddPinglunActivity extends AppCompatActivity{

    ///编辑框
    private EditText edit1;
    ///确定按钮
    private Button btnqueding;
    ///取消按钮
    private Button btnquxiao;
    ///微博号
    private String Weibonum;
    ///微博头像
    private String weibohead;
    ///微博昵称
    private String weiboname;
    ///手机号
    private String Phone;
    ///上一级评论内容
    private String pinglunWord;
    ///上一级评论时间
    private String pinglunTime;
    ///上一家评论等级
    private String level;
    ///评论号
    private String pinglunid;
    ///评论人昵称
    private String pinglunname;
    ///评论内容
    private String pinglunword;
    ///评论人头像
    private String pinglunhead;
    ///评论时间
    private String pingluntime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pinglun);

        ///接收上一活动所传参数
        Intent intent = getIntent();

        Phone = intent.getStringExtra("phone");
        Weibonum = intent.getStringExtra("weibonum");
        weibohead = intent.getStringExtra("weibohead");
        weiboname = intent.getStringExtra("weiboname");
        level = intent.getStringExtra("pinglunid");

        pinglunid = intent.getStringExtra("pinglunid");
        pinglunhead = intent.getStringExtra("pinglunhead");
        pinglunname = intent.getStringExtra("pinglunname");
        pingluntime = intent.getStringExtra("pingluntime");
        pinglunword = intent.getStringExtra("pinglunword");

        final int flag1 = intent.getIntExtra("flag",0);

        ///初始化控件
        edit1 = (EditText)findViewById(R.id.edit1);
        btnqueding = (Button)findViewById(R.id.queding);
        btnquxiao = (Button)findViewById(R.id.quxiao);
        ///返回确定按钮点击事件
        btnqueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///从编辑框获取评论内容
                pinglunWord = edit1.getText().toString();
                if (!pinglunWord.equals("")){
                    ///正文不为空
                    ///获取系统时间
                    pinglunWord = "回复@" + pinglunname + ":" + pinglunWord;
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
                    pinglunTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
                    ///向服务器提交参数
                    RequestBody requestBody = new FormBody.Builder()
                            .add("phone", Phone)// 新增参数
                            .add("pinglunword", pinglunWord)
                            .add("pingluntime", pinglunTime)
                            .add("weibonum", Weibonum)
                            .add("level", level)
                            .build();
                    HttpUtil.getByOkHttp("addpinglun.jsp", requestBody, new Callback() {
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
                            int data = Integer.parseInt(xmlData);
                            if (data == 1) {
                                ///返回值为1，提示用户发表成功
                                show("发表成功");
                            }else{
                                ///返回值为0，提示用户发表失败
                                show("发表失败");
                            }
                        }
                    });
                    ///跳出对话框，是否确定发送评论
                    AlertDialog.Builder builder=new AlertDialog.Builder(AddPinglunActivity.this);
                    builder.setMessage("确定发送这条评论或回复？");
                    ///点击取消不执行操作，返回当前页面
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    ///点击确定跳转至原页面
                    builder.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (flag1 == 1){
                                        ///跳转至查看评论活动
                                        Intent intent = new Intent(AddPinglunActivity.this, CheckPinglunActivity.class);
                                        intent.putExtra("phone", Phone);
                                        intent.putExtra("weibonum", Weibonum);
                                        intent.putExtra("weibohead", weibohead);
                                        intent.putExtra("weiboname", weiboname);
                                        startActivity(intent);
                                    }
                                    else if(flag1 == 2){
                                        ///跳转至查看回复活动
                                        Intent intent = new Intent(AddPinglunActivity.this, CheckHuifuActivity.class);
                                        intent.putExtra("phone", Phone);
                                        intent.putExtra("weibonum", Weibonum);
                                        intent.putExtra("weibohead", weibohead);
                                        intent.putExtra("weiboname", weiboname);
                                        intent.putExtra("pinglunhead", pinglunhead);
                                        intent.putExtra("pinglunname", pinglunname);
                                        intent.putExtra("pingluntime", pingluntime);
                                        intent.putExtra("pinglunword", pinglunword);
                                        intent.putExtra("pinglunid", pinglunid);
                                        startActivity(intent);
                                    }
                                }
                            });
                    builder.show();
                }else {
                    ///评论或回复为空，提示用户内容不能没空
                    show("内容不能为空");
                }
            }
        });

        ///取消按钮点击事件
        btnquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///跳出对话框，是否取消发送评论
                AlertDialog.Builder builder=new AlertDialog.Builder(AddPinglunActivity.this);
                builder.setMessage("确定取消评论或回复？");
                ///点击取消不执行操作，返回当前页面
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                ///点击确定取消回复或评论，并跳转至原页面
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (flag1 == 1){
                                    ///跳转至查看评论活动
                                    Intent intent = new Intent(AddPinglunActivity.this, CheckPinglunActivity.class);
                                    intent.putExtra("phone", Phone);
                                    intent.putExtra("weibonum", Weibonum);
                                    intent.putExtra("weibohead", weibohead);
                                    intent.putExtra("weiboname", weiboname);
                                    startActivity(intent);
                                }
                                else if(flag1 == 2){
                                    ///跳转至查看回复活动
                                    Intent intent = new Intent(AddPinglunActivity.this, CheckHuifuActivity.class);
                                    intent.putExtra("phone", Phone);
                                    intent.putExtra("weibonum", Weibonum);
                                    intent.putExtra("weibohead", weibohead);
                                    intent.putExtra("weiboname", weiboname);
                                    intent.putExtra("pinglunhead", pinglunhead);
                                    intent.putExtra("pinglunname", pinglunname);
                                    intent.putExtra("pingluntime", pingluntime);
                                    intent.putExtra("pinglunword", pinglunword);
                                    intent.putExtra("pinglunid", pinglunid);
                                    startActivity(intent);
                                }
                            }
                        });
                builder.show();
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
