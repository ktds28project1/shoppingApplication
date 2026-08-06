package domain;

/**
 * 구매자
 */
public class Buyer extends User{
	

	
	/** 구매자 포인트 */
	private long point;
	
	/** 구매자 계좌 잔액 */
	private long money;
	
	/** 활성화 여부 */
	private boolean active;
	
	public Buyer(String id, String name, String password, String address, String phone) {
		super(id, name, password, address, phone);
		this.active = true;
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

}