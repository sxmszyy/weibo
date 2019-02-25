package com.example.hp.weibo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 评论页面
 */

public class CheckPinglunActivity extends AppCompatActivity {
    ///list控件
    private RecyclerView _list;
    ///微博号
    private String Weibonum;
    ///用户头像
    private String weibohead;
    ///用户昵称
    private String weiboname;
    ///手机号
    private String Phone;
    ///评论号
    private String pinglunid;
    ///头像
    private ImageView head;
    ///昵称
    private TextView name;
    ///图片
    private Bitmap weiboheadbmp;
    ///返回文本框控件
    private TextView fanhui;
    ///数据
    private ArrayList<PinglunInfo> pinglun_data;

    int count = 0;
    ///评论数按钮
    private Button numPinglun;
    ///评论按钮
    private Button btnPinglun;
    ///适配器
    private QQAdapter adapter;

    //MVC
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pinglun);

        ///获取上一活动所传参数
        Intent intent = getIntent();
        Phone = intent.getStringExtra("phone");
        Weibonum = intent.getStringExtra("weibonum");
        weibohead = intent.getStringExtra("weibohead");
        weiboname = intent.getStringExtra("weiboname");

        ///初始化控件
        head = (ImageView) findViewById(R.id.userhead);
        ///开皮线程，获取头像
        new Thread(new Runnable() {
            @Override
            public void run() {
                weiboheadbmp = HttpUtil.getURLimage(weibohead);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        head.setImageBitmap(weiboheadbmp);
                    }
                });
            }
        }).start();
        name = (TextView) findViewById(R.id.userid) ;
        name.setText(weiboname);

        fanhui = (TextView) findViewById(R.id.fanhui);
        ///返回文本框点击事件
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckPinglunActivity.this, WeiBoActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });

        ///初始化数据
        initData();

        _list = (RecyclerView) findViewById(R.id.recycler_view);
        ///布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        _list.setLayoutManager(manager);

        /// 适配器
        adapter = new QQAdapter(pinglun_data);
        _list.setAdapter(adapter);

        numPinglun = (Button)findViewById(R.id.NumPinlun_btn);

        btnPinglun = (Button)findViewById(R.id.Pinlun_btn);
        ///评论按钮点击事件
        btnPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///跳出对话框是否评论这条微博
                AlertDialog.Builder builder=new AlertDialog.Builder(CheckPinglunActivity.this);
                builder.setMessage("确定评论这条微博？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    ///点击取消不执行任何操作，返回当前活动
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            ///点击确定跳转至查看评论活动
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(CheckPinglunActivity.this, WeibopinglunActivity.class);
                                ///活动间传递参数
                                intent.putExtra("flag", 1);
                                intent.putExtra("phone", Phone);
                                intent.putExtra("weibonum", Weibonum);
                                intent.putExtra("weibohead", weibohead);
                                intent.putExtra("weiboname", weiboname);
                                intent.putExtra("pinglunid", "0");
                                startActivity(intent);
                            }
                        });
                builder.show();
            }
        });
    }

    /**
     * 定义控件变量
     * 控件初始化
     */
    private class QQHolder extends RecyclerView.ViewHolder{
        /// 代表列表的整体
        View _PinglunView;
        ///评论类
        PinglunInfo imfo;
        ///图片
        Bitmap bitmap;
        /// 头像
        ImageView _pinglunimgUser;
        /// 用户昵称
        TextView _pinglunname;
        /// 评论内容
        TextView _tvPinglunWords;
        /// 发言时间
        TextView _tvPinglunTime;

        /// 头像
        ImageView _dianzanimgUser;
        /// 用户昵称
        TextView _dianzanname;
        /// 点赞图标
        ImageView _imgZan;
        /// 用户简介
        TextView _tvjianjie;

        /// 头像
        ImageView _zhuanfaimgUser;
        /// 用户昵称
        TextView _zhuanfaname;
        /// 转发微博
        TextView _tvzhuanfa;
        /// 转发时间
        TextView _tvzhuanfaTime;
        ///	该评论回复数
        TextView _tvhuifu;

        /**
         * 定义控件变量
         * 控件初始化
         */
        public QQHolder(View view) {
            super(view);
            _PinglunView = view;
            // 图片
            _pinglunimgUser = (ImageView)view.findViewById(R.id.pinglunimgUser);
            // 用户id
            _pinglunname = (TextView)view.findViewById(R.id.pinglunname);
            // 评论内容
            _tvPinglunWords = (TextView)view.findViewById(R.id.pinglun);
            // 评论时间
            _tvPinglunTime = (TextView)view.findViewById(R.id.pingluntime);
            //该评论回复数
            _tvhuifu = (TextView)view.findViewById(R.id.tv_huifu);
        }
    }

    /**
     * 适配器
     */
    private class QQAdapter extends RecyclerView.Adapter<QQHolder>{
        /// 数据
        private List<PinglunInfo> _data;

        /**
         * 初始化数据
         * @param _data
         */
        public QQAdapter(List<PinglunInfo> _data) {
            this._data = _data;
        }

        /**
         * 告诉计算机，总共有多少行的数据要显示
         */
        @Override
        public int getItemCount() {
            return _data.size();
        }

        /**
         * 创建视图
         */
        @Override
        public QQHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pinglun, parent, false);
            final QQHolder holder = new QQHolder(view);

            return holder;
        }

        /**
         * 绑定数据
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(final QQHolder holder, int position) {
            final PinglunInfo info = _data.get(position);

            ///评论内容点击事件
            holder._tvPinglunWords.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(CheckPinglunActivity.this);
                    ///跳出对话框是否回复这条评论
                    builder.setMessage("确定回复这条评论？");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                ///点击确定跳转至添加评论活动
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(CheckPinglunActivity.this, AddPinglunActivity.class);
                                    ///传递活动切换时必要参数
                                    intent.putExtra("flag", 1);
                                    intent.putExtra("phone", Phone);
                                    intent.putExtra("weibonum", Weibonum);
                                    intent.putExtra("weibohead", weibohead);
                                    intent.putExtra("weiboname", weiboname);
                                    intent.putExtra("pinglunname", info.getName());;
                                    intent.putExtra("pinglunid", info.getPinglunid());
                                    intent.putExtra("weiboname", weiboname);
                                    startActivity(intent);
                                }
                            });
                    builder.show();
                }
            });

            ///回复数文本框点击事件
            holder._tvhuifu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ///跳转至查看回复活动
                    Intent intent = new Intent(CheckPinglunActivity.this, CheckHuifuActivity.class);
                    ///传递活动切换时必要参数
                    intent.putExtra("phone", Phone);
                    intent.putExtra("weibonum", Weibonum);
                    intent.putExtra("weibohead", weibohead);
                    intent.putExtra("weiboname", weiboname);
                    intent.putExtra("pinglunhead", info.getHead());
                    intent.putExtra("pinglunname", info.getName());
                    intent.putExtra("pingluntime", info.getPingluntime());
                    intent.putExtra("pinglunword", info.getPinglunword());
                    intent.putExtra("pinglunid", info.getPinglunid());
                    startActivity(intent);
                }
            });
            ///开辟线程接受图片
            new Thread(new Runnable() {
                @Override
                public void run() {
                    holder.bitmap = HttpUtil.getURLimage(info.getHead());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holder._pinglunimgUser.setImageBitmap(holder.bitmap);
                        }
                    });
                }
            }).start();
            ///各控件赋值
            holder._pinglunname.setText(info.getName());
            holder._tvPinglunWords.setText(info.getPinglunword());
            holder._tvPinglunTime.setText(info.getPingluntime());
        }
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
     * 解析数据
     * @param jsonDate 数据
     */
    public void parseJSONWithGSON(String jsonDate) {
        ///清空数据
        pinglun_data.clear();

        Gson gson = new Gson();

        ///根据PinglunInfo类格式进行解析
        ArrayList<PinglunInfo> pinglunList = gson.fromJson(jsonDate, new TypeToken<List<PinglunInfo>>(){
        }.getType());
        ///将数据装入列表
        for (PinglunInfo info : pinglunList) {
            pinglun_data.add(info);
        }
        ///评论数按钮刷新
        numPinglun.setText("评论 " + pinglun_data.size());
        ///刷新评论列表
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData(){
        pinglun_data = new ArrayList<PinglunInfo>();

        pinglunData();
    }

    /**
     * 获取服务器数据
     */
    private void pinglunData(){
        ///清空数据
        pinglun_data.clear();
        ///向服务器提交数据
        RequestBody requestBody = new FormBody.Builder()
                .add("phone", Phone)
                .add("weibonum", Weibonum) // 新增参数
                .build();
        HttpUtil.getByOkHttp("Pinglun_Json.jsp", requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                /// 联网失败，提示用户网络异常
                show("网络异常");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ///获取返回值
                String jsonDate = response.body().string().trim();
                //show(jsonDate);
                ///进行解析
                parseJSONWithGSON(jsonDate);
            }
        });
    }
}
