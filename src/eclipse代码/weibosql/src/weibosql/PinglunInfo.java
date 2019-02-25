package weibosql;

public class PinglunInfo {

	private  String Pinglunid;
    // 头像
    private String Head;
    //昵称
    private String Name;
    // 评论
    private String PinglunWords;
    // 评论时间
    private String PinglunTime;
    //level
    private String Level;

    public PinglunInfo(String _Pinglunid, String _head, String _name, 
    		String _pinglunWords,String _PinglunTime, String _level) {
        this.Pinglunid = _Pinglunid;
        this.Head = _head;
        this.Name = _name;
        this.PinglunWords = _pinglunWords;
        this.PinglunTime = _PinglunTime;
        this.Level = _level;
    }

    public PinglunInfo() {
    }

    public String getPinglunid() {
        return Pinglunid;
    }

    public void setPinglunid(String _Pinglunid) {
        this.Pinglunid = _Pinglunid;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String _head) {
        this.Head = _head;
    }

    public String getName() {
        return Name;
    }

    public void setName(String _name) {
        this.Name = _name;
    }

    public String getPinglunWords() {
        return PinglunWords;
    }

    public void setPinglunWords(String _pinglunWords) {
        this.PinglunWords = _pinglunWords;
    }

    public String getPinglunTime() {
        return PinglunTime;
    }

    public void setPinglunTime(String _PinglunTime) {
        this.PinglunTime = _PinglunTime;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String _level) {
        this.Level = _level;
    }
}
