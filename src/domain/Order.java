package domain;

import java.time.LocalDateTime;

/**
 * 주문 내역
 */
public class Order {
	
	/** 주문 번호 */
	private final long orderNumber;
	
	/** 주문자 아이디 */
	private final String buyer;
	
	/** 주문 상품 번호 */
	private final long product;
	
	/** 주문 수량 */
	private final int quantity;
	
	/** 사용한 포인트 */
	private final int usePoint;
	
	/** 지불 금액 */
	private final int price;
	
	/** 배송 주소 */
	private final String address;
	
	/** 수령자 이름 */
	private final String name;
	
	/** 수령자 연락처 */
	private final String phone;
	
	/** 결제 계좌번호 */
	private final String account;
	
	/** 주문 일시 */
	private final LocalDateTime orderDate;
	
	public Order(long orderNumber, String buyer, long product, int quantity, int userPoint, int price
			, String address, String name, String phone, String account) {
		this.orderNumber = orderNumber;
		this.buyer = buyer;
		this.product = product;
		this.quantity = quantity;
		this.usePoint = userPoint;
		this.price = price;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.account = account;
		this.orderDate = LocalDateTime.now();
	}

	public long getOrderNumber() {
		return this.orderNumber;
	}

	public String getBuyer() {
		return this.buyer;
	}

	public long getProduct() {
		return this.product;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public int getUsePoint() {
		return this.usePoint;
	}

	public int getPrice() {
		return this.price;
	}

	public String getAddress() {
		return this.address;
	}

	public String getName() {
		return this.name;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getAccount() {
		return this.account;
	}

	public LocalDateTime getOrderDate() {
		return this.orderDate;
	}
}
