package service;

import domain.Buyer;
import domain.Order;
import domain.Review;
import domain.Seller;

import java.util.List;

public interface ReviewService {

    // 리뷰 등록
    void addReview(Buyer buyer, List<Order> orderList);

}
