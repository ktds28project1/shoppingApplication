package domain;

public class User {
	/** 사용자 아이디 */
	private final String userId;
	/** 사용자 이름 */
	private String name;
	/** 사용자 비밀번호 */
	private String password;
	/** 사용자 주소 */
	private String address;
	/** 사용자 연락처 */
	private String phoneNumber;
	
	public User(String userId, String name, String password, String address, String phoneNumber) {
		
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
