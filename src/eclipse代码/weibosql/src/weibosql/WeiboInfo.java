package weibosql;

public class WeiboInfo {
	private String weibonum;
    private String head;
    private String name;
    private String time;
    private String introduce;
    private String follow;
    private String word;
    private String image;
    private String label;
    private int zhuan;
    private int zan;
    private int ping;
    private String iszan;
	
	public WeiboInfo() {
		super();
	}

	public WeiboInfo(String weibonum, String head, String name, String time, String introduce, String follow,
			String word, String image, String label, int zhuan, int zan, int ping, String iszan) {
		super();
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



	public String getWeibonum() {
		return weibonum;
	}

	public void setWeibonum(String weibonum) {
		this.weibonum = weibonum;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getFollow() {
		return follow;
	}

	public void setFollow(String follow) {
		this.follow = follow;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getZhuan() {
		return zhuan;
	}

	public void setZhuan(int zhuan) {
		this.zhuan = zhuan;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public int getPing() {
		return ping;
	}

	public void setPing(int ping) {
		this.ping = ping;
	}

	public String getIszan() {
		return iszan;
	}

	public void setIszan(String iszan) {
		this.iszan = iszan;
	}
	
}
