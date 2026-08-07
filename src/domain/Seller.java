package domain;


/**
 * 판매자
 */
public class Seller extends User{

	/** 대표자명 */
	private String ownerName;
	
	/** 판매자 고유 sid */
	private String sellerSid;
	
	/** 매출 */
	private long sales;
	
	public Seller(String companyName, String businessNumber, String ownerName, String ownerPhone, String address, String password) {
		super(businessNumber, companyName, password, address, ownerPhone);
		this.ownerName = ownerName;
		
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public long getSales() {
		return this.sales;
	}

	public void setSales(long sales) {
		this.sales = sales;
	}


	public String getSid() {
        return sellerSid;
    }
}
