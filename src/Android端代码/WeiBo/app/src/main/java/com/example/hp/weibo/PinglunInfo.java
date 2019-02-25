package com.example.hp.weibo;

/**
 * 评论类
 */

public class PinglunInfo {

    ///评论号
    private  String pinglunid;
    ///评论用户头像
    private String head;
    ///评论用户昵称
    private String name;
    ///评论内容
    private String pinglunword;
    ///评论时间
    private String pingluntime;
    ///评论等级
    private String level;

    /**
     * 带参数构造函数
     * @param pinglunid 评论号
     * @param head 评论用户头像
     * @param name 评论用户昵称
     * @param pingluntime 评论时间
     * @param level 评论等级
     * @param pinglunword 评论内容
     */
    public PinglunInfo(String pinglunid, String head, String name, String pinglunword, String pingluntime, String level) {
        this.pinglunid = pinglunid;
        this.head = head;
        this.name = name;
        this.pinglunword = pinglunword;
        this.pingluntime = pingluntime;
        this.level = level;
    }

    @Override
    public String toString() {
        return "PinglunInfo{" +
                "pinglunid='" + pinglunid + '\'' +
                ", head='" + head + '\'' +
                ", name='" + name + '\'' +
                ", pinglunword='" + pinglunword + '\'' +
                ", pingluntime='" + pingluntime + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    /**
     * 无参数构造函数
     */
    public PinglunInfo() {
    }

    /**
     *获取评论号
     * @return 评论号
     */
    public String getPinglunid() {
        return pinglunid;
    }

    /**
     * 获取评论号
     * @param pinglunid 评论号
     */
    public void setPinglunid(String pinglunid) {
        this.pinglunid = pinglunid;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置有户名
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取时间
     * @return 时间
     */
    public String getPingluntime() {
        return pingluntime;
    }

    /**、
     * 设置时间
     * @param pingluntime 时间
     */
    public void setPingluntime(String pingluntime) {
        this.pingluntime = pingluntime;
    }

    /**
     * 获取评论
     * @return 评论
     */
    public String getPinglunword() {
        return pinglunword;
    }

    /**
     * 设置评论
     * @param pinglunword 评论
     */
    public void setPinglunword(String pinglunword) {
        this.pinglunword = pinglunword;
    }

    /**
     * 获取评论等级
     * @return 评论等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置品论等级
     * @param level 评论等级
     */
    public void setLevel(String level) {
        this.level = level;
    }
}
