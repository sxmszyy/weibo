package com.example.hp.weibo;

/**
 * 微博类
 */
public class WeiboInfo {
    ///微博号
    private String weibonum;
    ///头像
    private String head;
    ///用户名
    private String name;
    ///时间
    private String time;
    ///说明
    private String introduce;
    ///关注
    private String follow;
    ///正文
    private String word;
    ///图片
    private String image;
    ///标签
    private String label;
    ///转发
    private int zhuan;
    ///赞
    private int zan;
    ///评论
    private int ping;
    ///是否赞
    private String iszan;

    /**
     * 无参数构造函数
     */
    public WeiboInfo() {
    }

    /**
     * 带参数构造函数
     * @param weibonum 微博号
     * @param head 头像
     * @param name 用户名
     * @param time 时间
     * @param introduce 说明
     * @param follow 关注
     * @param word 正文
     * @param image 图片
     * @param label 标签
     * @param zhuan 转发
     * @param zan 赞
     * @param ping 评论
     * @param iszan 是否赞
     */
    public WeiboInfo(String weibonum, String head, String name, String time, String introduce, String follow, String word, String image, String label, int zhuan, int zan, int ping, String iszan) {
        this.weibonum = weibonum;
        this.head = head;
        this.name = name;
        this.time = time;
        this.introduce = introduce;
        this.follow = follow;
        this.word = word;
        this.image = image;
        this.label = label;
        this.zhuan = zhuan;
        this.zan = zan;
        this.ping = ping;
        this.iszan = iszan;
    }

    /**
     * 获取微博号
     * @return 微博号
     */
    public String getWeibonum() {
        return weibonum;
    }

    /**
     * 设置微博号
     * @param weibonum 微博号
     */
    public void setWeibonum(String weibonum) {
        this.weibonum = weibonum;
    }

    /**
     * 获取头像
     * @return 头像
     */
    public String getHead() {
        return head;
    }

    /**
     * 设置头像
     * @param head 头像
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取时间
     * @return 时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置时间
     * @param time 时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取说明
     * @return 说明
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置说明
     * @param introduce 说明
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取关注
     * @return 关注
     */
    public String getFollow() {
        return follow;
    }

    /**
     * 设置关注
     * @param follow 关注
     */
    public void setFollow(String follow) {
        this.follow = follow;
    }

    /**
     * 获取正文
     * @return 正文
     */
    public String getWord() {
        return word;
    }

    /**
     * 设置正文
     * @param word 正文
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * 获取图片
     * @return 图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片
     * @param image 图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取标签
     * @return 标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置标签
     * @param label 标签
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取评论
     * @return 评论
     */
    public int getPing() {
        return ping;
    }

    /**
     * 设置评论
     * @param ping 评论
     */
    public void setPing(int ping) {
        this.ping = ping;
    }

    /**
     * 获取赞
     * @return 赞
     */
    public int getZan() {
        return zan;
    }

    /**
     * 设置赞
     * @param zan 赞
     */
    public void setZan(int zan) {
        this.zan = zan;
    }

    /**
     * 获取转发
     * @return 转发
     */
    public int getZhuan() {
        return zhuan;
    }

    /**
     * 设置转发
     * @param zhuan 转发
     */
    public void setZhuan(int zhuan) {
        this.zhuan = zhuan;
    }

    /**
     * 获取是否赞
     * @return 是否赞
     */
    public String getIszan() {
        return iszan;
    }

    /**
     * 设置是否赞
     * @param iszan 是否赞
     */
    public void setIszan(String iszan) {
        this.iszan = iszan;
    }
}
