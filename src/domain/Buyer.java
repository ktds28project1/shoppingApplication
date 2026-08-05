package domain;

/**
 * 구매자
 */
public class Buyer {
	
	/** 구매자 아이디 */
	private final String id;
	
	/** 구매자 이름 */
	private String name;
	
	/** 구매자 비밀번호 */
	private String password;
	
	/** 구매자 주소 */
	private String address;
	
	/** 구매자 연락처 */
	private String phone;
	
	/** 구매자 포인트 */
	private long point;
	
	/** 구매자 계좌 잔액 */
	private long money;
	
	/** 활성화 여부 */
	private boolean active;
	
	public Buyer(String id, String name, String password, String address, String phone, long money) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.money = money;
		this.active = true;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getPoint() {
		return this.point;
	}

	public void setPoint(long point) {
		this.point = point;
	}

	public long getMoney() {
		return this.money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getId() {
		return this.id;
	}
}