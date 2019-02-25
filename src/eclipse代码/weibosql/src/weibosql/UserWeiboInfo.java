package weibosql;

public class UserWeiboInfo {
	String phone;
	String weibonum;
	public UserWeiboInfo() {
		super();
	}
	public UserWeiboInfo(String phone, String weibonum) {
		super();
		this.phone = phone;
		this.weibonum = weibonum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWeibonum() {
		return weibonum;
	}
	public void setWeibonum(String weibonum) {
		this.weibonum = weibonum;
	}
}
