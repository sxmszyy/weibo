package com.example.hp.weibo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


/**
 * 微博主页
 */
public class WeiBoActivity extends AppCompatActivity {

    ///是否为热门
    private boolean isRemen = true;
    ///  默认头像
    private String WebHead = "image/head.jpg";
    ///手机号
    private String Phone;
    /// list控件
    private RecyclerView _lstWB;
    ///下拉刷新控件
    private SwipeRefreshLayout _swipeRefresh;
    ///发表微博图片
    private ImageView _imgAdd;
    /// 数据
    private List<WeiboInfo> _data;
    ///关注文本框
    private TextView _tvGuanzhu;
    ///热门文本框
    private TextView _tvRemen;
    ///微博图片
    private ImageView _imgWeibo;
    ///发现图片
    private ImageView _imgFaxian;
    ///消息图片
    private ImageView _imgXiaoxi;
    ///视屏图片
    private ImageView _imgShiping;
    ///我图片
    private ImageView _imgWo;
    /// 适配器
    private WBAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_wei_bo);

        ///获取手机号
        Intent intent = getIntent();
        Phone = intent.getStringExtra("phone");

        ///初始化控件
        _lstWB = (RecyclerView)findViewById(R.id.recycler_view);
        _swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        _swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        ///下拉刷新事件
        _swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                ///进行刷新
                refreshData();
            }
        });
        _imgAdd = (ImageView) findViewById(R.id.imgAdd);
        ///发表微博图片点击事件
        _imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiBoActivity.this, AddWeiboActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgWeibo = (ImageView) findViewById(R.id.imgWeibo);
        _imgWeibo.setImageResource(R.drawable.weiboing);
        ///微博图片点击事件
        _imgWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///进行刷新
                refreshData();
            }
        });
        _imgShiping = (ImageView) findViewById(R.id.imgShiping);
        ///视频图片点击事件
        _imgShiping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiBoActivity.this, ShipingActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgFaxian = (ImageView) findViewById(R.id.imgFaxian);
        ///发现图片点击事件
        _imgFaxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiBoActivity.this, FaxianActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgXiaoxi = (ImageView) findViewById(R.id.imgXiaoxi);
        ///消息图片点击事件
        _imgXiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiBoActivity.this, XiaoxiActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgWo = (ImageView) findViewById(R.id.imgWo);
        ///我图片点击事件
        _imgWo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiBoActivity.this, WoActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _tvGuanzhu = (TextView)findViewById(R.id.tvGuanzhu);
        _tvRemen = (TextView)findViewById(R.id.tvRemen);
        _tvRemen.setTextColor(Color.rgb(0, 0, 0));
        ///关注文本框点击事件
        _tvGuanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///改变界面
                isRemen = false;
                _tvGuanzhu.setTextColor(Color.rgb(0, 0, 0));
                _tvRemen.setTextColor(Color.rgb(136, 136, 136));
                refreshData();
            }
        });
        ///热门文本框点击事件
        _tvRemen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///改变界面
                isRemen = true;
                _tvGuanzhu.setTextColor(Color.rgb(136, 136, 136));
                _tvRemen.setTextColor(Color.rgb(0, 0, 0));
                refreshData();
            }
        });
        /// 初始化数据
        initData();

        /// 布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        _lstWB.setLayoutManager(manager);
        /// 适配器
        _adapter = new WBAdapter(_data);
        _lstWB.setAdapter(_adapter);

    }

    /**
     * 定义控件变量
     * 控件初始化
     */
    private class WBHolder extends RecyclerView.ViewHolder{
        ///微博类
        WeiboInfo info;
        ///图片
        Bitmap bitmap;
        /// 头像
        ImageView _imgHead;
        ///图片1
        ImageView _imgTu1;
        ///图片2
        ImageView _imgTu2;
        ///图片3
        ImageView _imgTu3;
        ///图片4
        ImageView _imgTu4;
        ///图片5
        ImageView _imgTu5;
        ///图片6
        ImageView _imgTu6;
        ///图片7
        ImageView _imgTu7;
        ///图片8
        ImageView _imgTu8;
        ///图片9
        ImageView _imgTu9;
        /// 名字文本框
        TextView _tvName;
        /// 时间文本框
        TextView _tvTime;
        /// 说明文本框
        TextView _tvIntroduce;
        /// 关注按钮
        Button _btnFollow;
        /// 正文文本框
        TextView _tvWord;
        /// 标签文本框
        TextView _tvLabel;
        /// 转按钮
        Button _btnZhuan;
        /// 赞按钮
        Button _btnZan;
        /// 评按钮
        Button _btnPing;
        ///关闭文本框
        TextView _tvClose;

        /**
         * 初始化控件
         * @param view
         */
        public WBHolder(View view) {
            super(view);
            // 头像
            _imgHead = (ImageView)view.findViewById(R.id.imgHead);
            // 图
            _imgTu1 = (ImageView)view.findViewById(R.id.imgTu1);
            _imgTu2 = (ImageView)view.findViewById(R.id.imgTu2);
            _imgTu3 = (ImageView)view.findViewById(R.id.imgTu3);
            _imgTu4 = (ImageView)view.findViewById(R.id.imgTu4);
            _imgTu5 = (ImageView)view.findViewById(R.id.imgTu5);
            _imgTu6 = (ImageView)view.findViewById(R.id.imgTu6);
            _imgTu7 = (ImageView)view.findViewById(R.id.imgTu7);
            _imgTu8 = (ImageView)view.findViewById(R.id.imgTu8);
            _imgTu9 = (ImageView)view.findViewById(R.id.imgTu9);
            // 名字
            _tvName = (TextView)view.findViewById(R.id.tvName);
            // 正文
            _tvWord = (TextView)view.findViewById(R.id.tvWord);
            // 时间
            _tvTime = (TextView)view.findViewById(R.id.tvTime);
            // 说明
            _tvIntroduce = (TextView)view.findViewById(R.id.tvIntroduce);
            // 时间
            _tvLabel = (TextView)view.findViewById(R.id.tvLabel);
            // 关注
            _btnFollow = (Button)view.findViewById(R.id.btnFollow);
            // 转
            _btnZhuan = (Button)view.findViewById(R.id.btnZhuan);
            // 赞
            _btnZan = (Button)view.findViewById(R.id.btnZan);
            // 评
            _btnPing = (Button)view.findViewById(R.id.btnPing);
            // 关闭
            _tvClose = (TextView)view.findViewById(R.id.tvClose);
        }
    }

    /**
     * 适配器
     */
    private class WBAdapter extends RecyclerView.Adapter<WBHolder>{
        private List<WeiboInfo> _data;/// 数据

        /**
         * 初始化数据
         * @param _data
         */
        public WBAdapter(List<WeiboInfo> _data) {
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
         * 创建视图，关联activity_wei_bo.xml
         */
        public WBHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_wei_bo, parent, false);
            final WBHolder holder = new WBHolder(view);

            return holder;
        }

        @Override
        /**
         * 绑定数据
         * @param holder
         * @param position
         */
        public void onBindViewHolder(final WBHolder holder, int position) {
            final WeiboInfo info = _data.get(position);

            ///开辟线程接受图片
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ///获取图片
                    holder.bitmap = HttpUtil.getURLimage(info.getHead());
                    ///修改界面
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holder._imgHead.setImageBitmap(holder.bitmap);
                        }
                    });
                }
            }).start();
            String isImage = info.getImage();
            if (isImage.equals("0")) {
                ///图片为空
                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) holder._imgTu1.getLayoutParams();
                linearParams.height = 0;
                holder._imgTu1.setLayoutParams(linearParams);
            } else {
                ///图片不为空，开辟线程接受图片
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ///获取图片
                        final Bitmap bitmap = HttpUtil.getURLimage("image/" + info.getImage());
                        ///修改界面
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holder._imgTu1.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }
            ///修改界面
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) holder._imgTu2.getLayoutParams();
            linearParams.height = 0;
            holder._imgTu2.setLayoutParams(linearParams);
            linearParams = (LinearLayout.LayoutParams) holder._imgTu3.getLayoutParams();
            linearParams.height = 0;
            holder._imgTu3.setLayoutParams(linearParams);
            linearParams = (LinearLayout.LayoutParams) holder._imgTu4.getLayoutParams();
            linearParams.height = 0;
            holder._imgTu4.setLayoutParams(linearParams);
            linearParams = (LinearLayout.LayoutParams) holder._imgTu5.getLayoutParams();
            linearParams.height = 0;
            holder._imgTu5.setLayoutParams(linearParams);
            linearParams = (LinearLayout.LayoutParams) holder._imgTu6.getLayoutParams();
            linearParams.height = 0;
            holder._imgTu6.setLayoutParams(linearParams);
            linearParams = (LinearLayout.LayoutParams) holder._imgTu7.getLayoutParams();
            linearParams.height = 0;
            holder._imgTu7.setLayoutParams(linearParams);
            linearParams = (LinearLayout.LayoutParams) holder._imgTu8.getLayoutParams();
            linearParams.height = 0;
            holder._imgTu8.setLayoutParams(linearParams);
            linearParams = (LinearLayout.LayoutParams) holder._imgTu9.getLayoutParams();
            linearParams.height = 0;
            holder._imgTu9.setLayoutParams(linearParams);
            holder._tvName.setText(info.getName());
            holder._tvTime.setText(info.getTime());
            holder._tvWord.setText(info.getWord());
            holder._tvLabel.setText(info.getLabel());
            holder._tvIntroduce.setText(info.getIntroduce());
            holder._btnFollow.setText(info.getFollow());
            ///关注按钮点击事件
            holder._btnFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ///获取关注情况
                    String guanzhu = holder._btnFollow.getText().toString();
                    if (guanzhu.equals("+关注"))
                    {
                        ///进行关注
                        guanzhu(info.getWeibonum(), "1");
                    }
                    else{
                        ///进行取消关注
                        guanzhu(info.getWeibonum(), "2");
                    }
                }
            });
            int zhuan = info.getZhuan();
            holder._btnZhuan.setText("" + zhuan);
            int zan = info.getZan();
            holder._btnZan.setText("" + zan);
            if (info.getIszan().equals("1")){
                Drawable drawableLeft = getResources().getDrawable(R.drawable.yidianzan);
                drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                holder._btnZan.setCompoundDrawables(drawableLeft, null, null, null);
            }
            else{
                Drawable drawableLeft = getResources().getDrawable(R.drawable.weidianzan);
                drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                holder._btnZan.setCompoundDrawables(drawableLeft, null, null, null);
            }
            int ping = info.getPing();
            holder._btnPing.setText("" + ping);
            ///关闭文本框点击事件
            holder._tvClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///修改界面
                    _data.remove(info);
                    _adapter.notifyDataSetChanged();
                }
            });
            ///赞按钮点击事件
            holder._btnZan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///进行赞
                    zan(info.getWeibonum(), holder);
                }
            });
            ///评论按钮点击事件
            holder._btnPing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///跳转评论页面
                    Intent intent = new Intent(WeiBoActivity.this, WeibopinglunActivity.class);
                    intent.putExtra("flag", 0);
                    intent.putExtra("phone", Phone);
                    intent.putExtra("weibonum", info.getWeibonum());
                    intent.putExtra("weibohead", info.getHead());
                    intent.putExtra("weiboname", info.getName());
                    intent.putExtra("pinglunid", "0");
                    startActivity(intent);
                }
            });
            ///正文文本框点击事件
            holder._tvWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转评论展示页面
                    Intent intent = new Intent(WeiBoActivity.this, CheckPinglunActivity.class);
                    intent.putExtra("phone", Phone);
                    intent.putExtra("weibonum", info.getWeibonum());
                    intent.putExtra("weibohead", info.getHead());
                    intent.putExtra("weiboname", info.getName());
                    startActivity(intent);
                }
            });
            ///转发按钮点击事件
            holder._btnZhuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ///跳转转发页面
                    Intent intent = new Intent(WeiBoActivity.this, ZhuanfaActivity.class);
                    intent.putExtra("phone", Phone);
                    intent.putExtra("weibonum", info.getWeibonum());
                    intent.putExtra("weiboname", info.getName());
                    intent.putExtra("image", info.getImage());
                    intent.putExtra("word", info.getWord());
                    startActivity(intent);
                }
            });

        }
    }

    /**
     * 初始化数据
     */
    private void initData(){
        _data = new ArrayList<WeiboInfo>();
        ///获取网络数据
        webData("selectremen.jsp");
    }

    /**
     * 获取服务器数据
     *
     * @param web 网址
     */
    private void webData(String web){
        ///清空数据
        _data.clear();
        ///向服务器提交数据
        RequestBody requestBody = new FormBody.Builder()
                .add("phone", Phone)// 新增参数
                .build();
        HttpUtil.getByOkHttp(web, requestBody, new Callback() {
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

    /**
     * 刷新数据
     */
    private void refreshData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isRemen) {
                            ///获取热门数据
                            webData("selectremen.jsp");
                        }
                        else{
                            ///获取关注数据
                            webData("selectguanzhu.jsp");
                        }
                        ///刷新
                        _adapter.notifyDataSetChanged();
                        _swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    /**
     * 解析数据
     * @param jsonDate 数据
     */
    public void parseJSONWithGSON(String jsonDate) {
        //清空数据
        _data.clear();
        Gson gson = new Gson();
        ///根据WeiboInfo类格式进行解析
        List<WeiboInfo> weiboList = gson.fromJson(jsonDate, new TypeToken<List<WeiboInfo>>() {
        }.getType());
        ///将数据装入列表
        for (WeiboInfo info : weiboList) {
            _data.add(info);
        }
        ///刷新页面
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _adapter.notifyDataSetChanged();
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
     * 点赞或取消赞
     * @param weibonum 微博号
     * @param holder
     */
    private void zan(String weibonum, final WBHolder holder){
        ///向服务器提交数据
        RequestBody requestBody = new FormBody.Builder()
                .add("phone", Phone)// 新增参数
                .add("weibonum", weibonum)
                .build();
        HttpUtil.getByOkHttp("zan.jsp", requestBody, new Callback() {
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
                    ///返回值为1，进行点赞
                    final Drawable drawableLeft = getResources().getDrawable(R.drawable.yidianzan);
                    drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                    ///修改界面
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holder._btnZan.setCompoundDrawables(drawableLeft, null, null, null);
                            int zan = Integer.parseInt(holder._btnZan.getText().toString());
                            zan = zan + 1;
                            holder._btnZan.setText("" + zan);
                        }
                    });
                } else if (data == 2) {
                    ///返回值为2，进行取消点赞
                    final Drawable drawableLeft = getResources().getDrawable(R.drawable.weidianzan);
                    drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                    ///修改界面
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holder._btnZan.setCompoundDrawables(drawableLeft, null, null, null);
                            int zan = Integer.parseInt(holder._btnZan.getText().toString());
                            zan = zan - 1;
                            holder._btnZan.setText("" + zan);
                        }
                    });
                } else {
                    ///返回值为0，提示用户请在点一次
                    show("请再点一次");
                }
            }
        });
    }

    /**
     * 关注或取消关注
     * @param weibonum 微博号
     * @param flag 标记
     */
    private void guanzhu(String weibonum, String flag){
        ///向服务器提交数据
        RequestBody requestBody = new FormBody.Builder()
                .add("phone", Phone)// 新增参数
                .add("weibonum", weibonum)
                .add("flag", flag)
                .build();
        HttpUtil.getByOkHttp("guanzhu.jsp", requestBody, new Callback() {
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
                    ///返回值为1，提示用户关注成功，刷新界面
                    refreshData();
                    show("关注成功");
                } else if (data == 2) {
                    ///返回值为2，提示用户取消关注，刷新界面
                    refreshData();
                    show("取消关注");
                } else {
                    ///返回值为0，提示用户请再点一次
                    show("请再点一次");
                }
            }
        });
    }
}
