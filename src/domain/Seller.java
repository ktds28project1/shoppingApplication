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
	
	public Seller(String companyName, String businessNumber, String ownerName, String ownerPhone, String address, String password,String sellerSid) {
		super(businessNumber, companyName, password, address, ownerPhone);
		this.ownerName = ownerName;
		this.sellerSid = sellerSid;
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

	public String getSellerSid() {
		return sellerSid;
	}

	
	
	
}
