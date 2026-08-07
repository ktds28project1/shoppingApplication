package common;

import java.util.Map;
import java.util.Objects;

import domain.Buyer;
import domain.Inquiry;
import domain.Order;
import domain.Product;
import domain.Review;
import domain.Seller;

/**
 * 구매자 목록
 * 판매자 목록
 * 상품 목록
 * 거래 목록
 * 리뷰 목록
 * 문의 목록
 */
public record ShoppingData(Map<String, Buyer> buyerList, Map<String, Seller> sellerList
							, Map<Long, Product> productList, Map<Long, Order> orderList
							, Map<Long, Review> reviewList, Map<Long, Inquiry> inquiryList) {
	public ShoppingData{
		Objects.requireNonNull(buyerList);
		Objects.requireNonNull(sellerList);
		Objects.requireNonNull(productList);
		Objects.requireNonNull(orderList);
		Objects.requireNonNull(reviewList);
		Objects.requireNonNull(inquiryList);
	}
}
