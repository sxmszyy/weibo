package com.example.hp.weibo;

/**
 * 微博我的界面
 */
public class weiboshow_1 {
    ///图片
    private int _imgId;

    /**
     * 无参数构造函数
     */
    public weiboshow_1() {
    }

    /**
     * 带参数构造函数
     * @param _imgId 图片
     */
    public weiboshow_1(int _imgId) {
        this._imgId = _imgId;
    }

    /**
     * 获取图片
     * @return 图片
     */
    public int get_imgId() {
        return _imgId;
    }

    /**
     * 设置图片
     * @param _imgId 图片
     */
    public void set_imgId(int _imgId) {
        this._imgId = _imgId;
    }
}
