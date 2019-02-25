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
 * 消息主页
 */
public class XiaoxiActivity extends AppCompatActivity {

    ///手机号
    private String Phone;
    ///微博图片
    private ImageView _imgWeibo;
    ///发现图片
    private ImageView _imgFaxian;
    ///消息图片
    private ImageView _imgXiaoxi;
    ///时评图片
    private ImageView _imgShiping;
    ///我图片
    private ImageView _imgWo;
    /// list控件
    private RecyclerView _lstQQ;
    ///数据
    private List<weiboshow_2> _data;
    ///适配器
    private QQAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi);

        ///获取手机号
        Intent intent = getIntent();
        Phone = intent.getStringExtra("phone");

        ///初始化控件
        _imgWeibo = (ImageView) findViewById(R.id.imgWeibo);
        ///微博图片点击事件
        _imgWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiaoxiActivity.this, WeiBoActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgShiping = (ImageView) findViewById(R.id.imgShiping);
        ///视频图片点击事件
        _imgShiping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiaoxiActivity.this, ShipingActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgFaxian = (ImageView) findViewById(R.id.imgFaxian);
        ///发现图片点击事件
        _imgFaxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiaoxiActivity.this, FaxianActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });
        _imgWo = (ImageView) findViewById(R.id.imgWo);
        ///我图片点击事件
        _imgWo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiaoxiActivity.this, WoActivity.class);
                intent.putExtra("phone", Phone);
                startActivity(intent);
            }
        });

        _lstQQ = (RecyclerView) findViewById(R.id.lstQQ);
        /// 初始化数据
        initData();

        /// 布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        _lstQQ.setLayoutManager(manager);
        // 适配器
        _adapter = new QQAdapter(_data);
        _lstQQ.setAdapter(_adapter);
    }

    /**
     * 定义控件变量
     * 控件初始化
     */
    private class QQHolder extends RecyclerView.ViewHolder{
        View _qqView; /// 代表列表的整体
        ImageView _imgQq;/// 图片
        TextView _tvTitle;/// 标题
        TextView _tvNewWords;/// 最新消息
        TextView _tvWordTime;/// 消息时间
        TextView _tvUnReadNum;/// 未读的数量

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
            _tvTitle = (TextView)view.findViewById(R.id.tvTitle);
            // 最新消息
            _tvNewWords = (TextView)view.findViewById(R.id.tvNewWords);
            // 时间
            _tvWordTime = (TextView)view.findViewById(R.id.tvTime);
            // 未读的数量
            _tvUnReadNum = (TextView)view.findViewById(R.id.tvUnReadNum);
        }
    }

    /**
     * 适配器
     */
    private class QQAdapter extends RecyclerView.Adapter<QQHolder>{
        /// 数据
        private List<weiboshow_2> _data;

        /**
         * 初始化数据
         * @param _data
         */
        public QQAdapter(List<weiboshow_2> _data) {
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
         * 创建视图，关联recycle_view_1
         */
        @Override
        public QQHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_view_1, parent, false);
            final QQHolder holder = new QQHolder(view);
            //View代表界面
            return holder;
        }

        @Override
        /**
         * 绑定数据
         * @param holder
         * @param position
         */
        public void onBindViewHolder(QQHolder holder, int position) {
            weiboshow_2 info = _data.get(position);
            holder._imgQq.setImageResource(info.get_imgId());
            holder._tvTitle.setText(info.get_title());
            holder._tvNewWords.setText(info.get_newWords());
            holder._tvWordTime.setText(info.get_wordTime());
            holder._tvUnReadNum.setText(info.get_unReadNum());
        }
    }

    /**
     * 初始化数据
     */
    private void initData(){
        _data = new ArrayList<weiboshow_2>();
        ///添加数据
        _data.add( new weiboshow_2(
                R.drawable.wode,
                "@我的", "", "", ""
        ));
        _data.add( new weiboshow_2(
                R.drawable.pinglun2,
                "评论", "", "", ""
        ));
        _data.add( new weiboshow_2(
                R.drawable.zan,
                "赞", "", "", ""
        ));
        _data.add( new weiboshow_2(
                R.drawable.xinwen,
                "新浪新闻", "每天煮饭放一把，远离三高....", "17:54", "2"
        ));
        _data.add( new weiboshow_2(
                R.drawable.dingyue,
                "订阅消息", "蘑菇头电影：[图片]", "14：21", ""
        ));
        _data.add( new weiboshow_2(
                R.drawable.weiborenwu,
                "微博任务", "积分奖励领取通知", "12-17", ""
        ));
        _data.add( new weiboshow_2(
                R.drawable.weibohuiyuan,
                "微博会员", "Eva：收一个计算器，....", "14：10", "3"
        ));
        _data.add( new weiboshow_2(
                R.drawable.text_1,
                "优乐购优惠券3群", "客服：[图片]秋冬新款....", "14：12", "42"
        ));
        _data.add( new weiboshow_2(
                R.drawable.text_2,
                "我的电脑", "[文档]国家励志奖学金....", "昨天", ""
        ));
    }
}