package domain;

/**
 * 판매자
 */
public class Seller {
	
	/** 판매자명 */
	private final String companyName;
	
	/** 사업자 등록번호 */
	private final String businessNumber;
	
	/** 대표자명 */
	private String ownerName;
	
	/** 대표자 연락처 */
	private String ownerPhone;
	
	/** 주소 */
	private String address;
	
	/** 비밀번호 */
	private String password;
	
	/** 매출 */
	private long sales;
	
	public Seller(String companyName, String businessNumber, String ownerName, String ownerPhone, String address, String password) {
		this.companyName = companyName;
		this.businessNumber = businessNumber;
		this.ownerName = ownerName;
		this.ownerPhone = ownerPhone;
		this.address = address;
		this.password = password;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerPhone() {
		return this.ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getSales() {
		return this.sales;
	}

	public void setSales(long sales) {
		this.sales = sales;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public String getBusinessNumber() {
		return this.businessNumber;
	}
}
