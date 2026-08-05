package domain;

import java.time.LocalDateTime;

/**
 * 리뷰
 */
public class Review {
	
	/** 제품 번호 */
	private final long productNumber;
	
	/** 구매자 아이디 */
	private final String buyer;
	
	/** 리뷰 내용 */
	private final String content;
	
	/** 별점 */
	private final double rating;
	
	private final LocalDateTime writeDate;
	
	public Review(long productNumber, String buyer, String content, double rating) {
		this.productNumber = productNumber;
		this.buyer = buyer;
		this.content = content;
		this.rating = rating;
		this.writeDate = LocalDateTime.now();
	}

	public long getProductNumber() {
		return this.productNumber;
	}

	public String getBuyer() {
		return this.buyer;
	}

	public String getContent() {
		return this.content;
	}

	public double getRating() {
		return this.rating;
	}

	public LocalDateTime getWriteDate() {
		return this.writeDate;
	}
}
