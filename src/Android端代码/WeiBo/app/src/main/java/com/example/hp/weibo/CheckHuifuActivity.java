package com.example.hp.weibo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CheckHuifuActivity extends AppCompatActivity {

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
    ///评论用户昵称
    private String pinglunname;
    ///评论
    private String pinglunword;
    ///评论用户头像
    private String pinglunhead;
    ///评论时间
    private String pingluntime;

    PinglunInfo data1 = new PinglunInfo();
    PinglunInfo data2 = new PinglunInfo();
    ///头像控件
    private ImageView head;
    ///回复数文本框
    private TextView numHuifu;
    ///返回按钮
    private TextView huifufanhui;
    ///图片
    private Bitmap pinglunheadbmp;

    ///数据
    private ArrayList<PinglunInfo> huifu_data = new ArrayList<PinglunInfo>();
    ///适配器
    private HuifuAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_huifu);

        ///接收上一活动所传参数
        Intent intent = getIntent();
        Phone = intent.getStringExtra("phone");
        Weibonum = intent.getStringExtra("weibonum");
        weibohead = intent.getStringExtra("weibohead");
        weiboname = intent.getStringExtra("weiboname");
        pinglunid = intent.getStringExtra("pinglunid");
        pinglunhead = intent.getStringExtra("pinglunhead");
        pinglunname = intent.getStringExtra("pinglunname");
        pingluntime = intent.getStringExtra("pingluntime");
        pinglunword = intent.getStringExtra("pinglunword");

        ///初始化数据
        initData();

        ///初始化控件
        _list = (RecyclerView) findViewById(R.id.recycler_view2);
        ///布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        _list.setLayoutManager(manager);

        /// 适配器
        adapter = new HuifuAdapter(huifu_data);
        _list.setAdapter(adapter);

        numHuifu = (TextView) findViewById(R.id.num_huifu);
        huifufanhui = (TextView)findViewById(R.id.huifufanhui);
        ///回复数文本框点击事件
        huifufanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckHuifuActivity.this, CheckPinglunActivity.class);
                ///活动切换时传递参数
                intent.putExtra("phone", Phone);
                intent.putExtra("weibonum", Weibonum);
                intent.putExtra("weibohead", weibohead);
                intent.putExtra("weiboname", weiboname);
                startActivity(intent);
            }
        });
    }


    /**
     * 定义控件变量
     * 控件初始化
     */
    private class HuifuHolder extends RecyclerView.ViewHolder{
        /// 代表列表的整体
        View _PinglunView;
        ///评论类
        PinglunInfo imfo;
        ///位图
        Bitmap bitmap;
        /// 头像
        ImageView _huifuimgUser;
        /// 用户昵称
       TextView _huifuname;
        /// 评论内容
        TextView _tvHuifuWords;
        /// 发言时间
        TextView _tvHuifuTime;


        /**
         * 初始化控件
         * @param view
         */
        public HuifuHolder(View view) {
            super(view);
            _PinglunView = view;
            // 图片
            _huifuimgUser = (ImageView)view.findViewById(R.id.HuifuimgUser);
            // 用户id
            _huifuname = (TextView)view.findViewById(R.id.huifuname);
            // 评论内容
            _tvHuifuWords = (TextView)view.findViewById(R.id.huifuword);
            // 评论时间
            _tvHuifuTime = (TextView)view.findViewById(R.id.huifutime);
        }
    }

    /**
     * 适配器
     */
    private class HuifuAdapter extends RecyclerView.Adapter<CheckHuifuActivity.HuifuHolder>{
        // 数据
        private List<PinglunInfo> _data;

        /**
         * 初始化数据
         * @param _data
         */
        public HuifuAdapter(List<PinglunInfo> _data) {
            this._data = _data;
        }

        @Override
        /**
         * 告诉计算机，总共有多少行的数据要显示
         */
        public int getItemCount() {
            return _data.size();
        }

        @Override
        /**
         * 创建视图，关联huifu.xml
         */
        public CheckHuifuActivity.HuifuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.huifu, parent, false);
            final CheckHuifuActivity.HuifuHolder holder = new CheckHuifuActivity.HuifuHolder(view);
            // .View代表界面
            return holder;
        }

        @Override
        /**
         * 绑定数据
         * @param holder
         * @param position
         */
        public void onBindViewHolder(final CheckHuifuActivity.HuifuHolder holder, int position) {
            final PinglunInfo info = _data.get(position);

            ///回复内容点击事件
            holder._tvHuifuWords.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(CheckHuifuActivity.this);
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
                                    Intent intent = new Intent(CheckHuifuActivity.this, AddPinglunActivity.class);
                                    ///传递活动切换时必要参数
                                    intent.putExtra("flag", 2);
                                    intent.putExtra("phone", Phone);
                                    intent.putExtra("weibonum", Weibonum);
                                    intent.putExtra("weibohead", weibohead);
                                    intent.putExtra("weiboname", weiboname);
                                    intent.putExtra("pinglunid", pinglunid);
                                    intent.putExtra("pinglunhead", pinglunhead);
                                    intent.putExtra("pinglunname", pinglunname);
                                    intent.putExtra("pingluntime", pingluntime);
                                    intent.putExtra("pinglunword", pinglunword);
                                    startActivity(intent);
                                }
                            });
                    builder.show();
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
                            holder._huifuimgUser.setImageBitmap(holder.bitmap);
                        }
                    });
                }
            }).start();
            ///各控件赋值
            holder._huifuname.setText(info.getName());
            holder._tvHuifuWords.setText(info.getPinglunword());
            holder._tvHuifuTime.setText(info.getPingluntime());

            if (info.getLevel() == "0"){
                holder._huifuimgUser.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder._huifuname.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder._tvHuifuWords.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder._tvHuifuTime.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
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
        huifu_data.clear();

        Gson gson = new Gson();
        ///根据PinglunInfo类格式进行解析
        ArrayList<PinglunInfo> pinglunList = gson.fromJson(jsonDate, new TypeToken<List<PinglunInfo>>(){
        }.getType());
        ///将数据装入列表
        for (PinglunInfo info : pinglunList) {
            huifu_data.add(info);
        }
        ///回复数文本框刷新
        numHuifu.setText("该评论下" + huifu_data.size() + "条回复");
        ///将列表第一行设为第一级评论
        if (huifu_data.size() > 0) {
            data2 = huifu_data.get(0);
            huifu_data.set(0, data1);
            huifu_data.add(data2);
        }else {
            huifu_data.add(data1);
        }
        ///刷新回复列表
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
        huifuData();
    }

    /**
     * 获取服务器数据
     */
    private void huifuData(){
        ///清空数据
        huifu_data.clear();
        ///向服务器提交数据
        data1 = new PinglunInfo(pinglunid, pinglunhead, pinglunname, pinglunword, pingluntime, "0");
        RequestBody requestBody = new FormBody.Builder()
                .add("phone", Phone)
                .add("weibonum", Weibonum) // 新增参数
                .add("Pinglunid", pinglunid)
                .build();
        HttpUtil.getByOkHttp("Huifu_Json.jsp", requestBody, new Callback() {
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
