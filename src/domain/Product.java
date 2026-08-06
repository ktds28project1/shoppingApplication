package domain;

/**
 * 제품
 */
public class Product {
	
	/** 제품 번호 */
	private final long productNumber;
	
	/** 판매자 등록번호 */
	private final String seller;
	
	/** 제품명 */
	private String name;
	
	/** 제품 가격 */
	private int price;
	
	/** 재고 수 */
	private int stock;
	
	/** 제품 설명 */
	private String description;
	
	/** 판매 중단 여부 */
	private boolean active;
	
	public Product(long productNumber, String seller, String name, int price, int stock, String description) {
		this.productNumber = productNumber;
		this.seller = seller;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.active = true; // 기본 판매 상태 활성화
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getProductNumber() {
		return this.productNumber;
	}

	public String getSeller() {
		return this.seller;
	}
}
