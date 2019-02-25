package com.example.hp.weibo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现页面
 */
public class FaxianActivity extends AppCompatActivity {

    ///手机号
    private String Phone;
    ///微博图片
    private ImageView _imgWeibo;
    ///发现图片
    private ImageView _imgFaxian;
    ///消息图片
    private ImageView _imgXiaoxi;
    ///视频图片
    private ImageView _imgShiping;
    ///我图片
    private ImageView _imgWo;
    /// list控件
    private RecyclerView _lstQQ;
    ///数据
    private List<weiboshow_1> _data;
    ///适配器
    private QQAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faxian);

        ///获取手机号
        Intent intent = getIntent();
        Phone = intent.getStringExtra("phone");

        ///初始化控件
        _imgWeibo = (ImageView) findViewById(R.id.imgWeibo);
        ///微博图片点击事件
        _imgWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaxianActivity.this, WeiBoActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgShiping = (ImageView) findViewById(R.id.imgShiping);
        ///视频图片点击事件
        _imgShiping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaxianActivity.this, ShipingActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgXiaoxi = (ImageView) findViewById(R.id.imgXiaoxi);
        ///消息图片点击事件
        _imgXiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaxianActivity.this, XiaoxiActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgWo = (ImageView) findViewById(R.id.imgWo);
        ///我图片点击事件
        _imgWo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaxianActivity.this, WoActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });

        _lstQQ = (RecyclerView) findViewById(R.id.lstQQ);
        /// 初始化数据
        initData();

        // 布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        _lstQQ.setLayoutManager(manager);
        /// 适配器
        _adapter = new QQAdapter(_data);
        _lstQQ.setAdapter(_adapter);
    }

    /**
     * 定义控件变量
     * 控件初始化
     */
    private class QQHolder extends RecyclerView.ViewHolder{
        View _qqView; // 代表列表的整体
        // 图片
        ImageView _imgQq;

        /**
         * 初始化控件
         * @param view
         *
         */
        public QQHolder(View view) {
            super(view);
            _qqView = view;
            // 图片
            _imgQq = (ImageView)view.findViewById(R.id.imgQQ);
            // 标题
        }
    }

    /**
     * 适配器
     */
    private class QQAdapter extends RecyclerView.Adapter<QQHolder>{
        /// 数据
        private List<weiboshow_1> _data;

        /**
         * 初始化数据
         * @param _data
         */
        public QQAdapter(List<weiboshow_1> _data) {
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
         * 创建视图，关联recycle_view_1
         */
        public QQHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_view_1, parent, false);
            final QQHolder holder = new QQHolder(view);

            return holder;
        }

        @Override
        /**
         * 绑定数据
         * @param holder
         * @param position
         */
        public void onBindViewHolder(QQHolder holder, int position) {
            weiboshow_1 info = _data.get(position);
            holder._imgQq.setImageResource(info.get_imgId());
        }
    }

    /**
     * 初始化数据
     */
    private void initData(){
        _data = new ArrayList<weiboshow_1>();
        ///添加数据
        _data.add( new weiboshow_1(R.drawable.guanggao_1));
        _data.add( new weiboshow_1(R.drawable.daohang_1));
        _data.add( new weiboshow_1(R.drawable.daohang_2));
        _data.add( new weiboshow_1(R.drawable.toutiao_1));
        _data.add( new weiboshow_1(R.drawable.toutiao_2));
        _data.add( new weiboshow_1(R.drawable.toutiao_3));
        _data.add( new weiboshow_1(R.drawable.toutiao_4));
        _data.add( new weiboshow_1(R.drawable.toutiao_5));
        _data.add( new weiboshow_1(R.drawable.toutiao_6));
    }
}
