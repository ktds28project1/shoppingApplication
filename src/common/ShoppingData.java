package common;

import java.util.List;
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
public record ShoppingData(List<Buyer> buyerList, List<Seller> sellerList
							, List<Product> productList, List<Order> orderList
							, List<Review> reviewList, List<Inquiry> inquiryList) {
	public ShoppingData{
		Objects.requireNonNull(buyerList);
		Objects.requireNonNull(sellerList);
		Objects.requireNonNull(productList);
		Objects.requireNonNull(orderList);
		Objects.requireNonNull(reviewList);
		Objects.requireNonNull(inquiryList);
	}
}
