package weibosql;

public class UserInfo {
	private String phone;
	private String password;
	private String name;
	private String head;
	
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public UserInfo() {
		super();
	}

	public UserInfo(String phone) {
		super();
		this.phone = phone;
	}

	public UserInfo(String phone, String password, String name) {
		super();
		this.phone = phone;
		this.password = password;
		this.name = name;
	}
	
	public UserInfo(String phone, String password, String name, String head) {
		super();
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.head = head;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
