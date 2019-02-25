package com.example.hp.weibo;

/**
 * 微博消息界面
 */
public class weiboshow_2 {
    ///图片
    private int _imgId;
    ///标题
    private String _title;
    ///最新消息
    private String _newWords;
    ///消息时间
    private String _wordTime;
    ///未读消息
    private String _unReadNum;

    /**
     * 不带参数构造函数
     */
    public weiboshow_2() {
    }

    /**
     * 带参数构造函数
     * @param _imgId 图片
     * @param _title 标题
     * @param _newWords 最新消息
     * @param _wordTime 时间
     * @param _unReadNum 未读消息
     */
    public weiboshow_2(int _imgId, String _title, String _newWords, String _wordTime, String _unReadNum) {
        this._imgId = _imgId;
        this._title = _title;
        this._newWords = _newWords;
        this._wordTime = _wordTime;
        this._unReadNum = _unReadNum;
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

    /**
     * 获取标题
     * @return 标题
     */
    public String get_title() {
        return _title;
    }

    /**
     * 设置标题
     * @param _title 标题
     */
    public void set_title(String _title) {
        this._title = _title;
    }

    /**
     * 获取消息
     * @return 消息
     */
    public String get_newWords() {
        return _newWords;
    }

    /**
     * 设置消息
     * @param _newWords 消息
     */
    public void set_newWords(String _newWords) {
        this._newWords = _newWords;
    }

    /**
     * 获取时间
     * @return 时间
     */
    public String get_wordTime() {
        return _wordTime;
    }

    /**
     * 设置时间
     * @param _wordTime 时间
     */
    public void set_wordTime(String _wordTime) {
        this._wordTime = _wordTime;
    }

    /**
     * 获取未读消息数
     * @return 未读消息数
     */
    public String get_unReadNum() {
        return _unReadNum;
    }

    /**
     * 设置未读消息数
     * @param _unReadNum 未读消息数
     */
    public void set_unReadNum(String _unReadNum) { this._unReadNum = _unReadNum; }
}