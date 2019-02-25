package weibosql;

public class GuanzhuInfo {
	private String phone;
	private String guanzhuphone;
	
	
	public GuanzhuInfo() {
		super();
	}
	public GuanzhuInfo(String phone, String guanzhuphone) {
		super();
		this.phone = phone;
		this.guanzhuphone = guanzhuphone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGuanzhuphone() {
		return guanzhuphone;
	}
	public void setGuanzhuphone(String guanzhuphone) {
		this.guanzhuphone = guanzhuphone;
	}
	
	
}
