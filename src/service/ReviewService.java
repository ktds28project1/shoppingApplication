package service;

import domain.Review;
import domain.Seller;

public interface ReviewService {

    // 리뷰 등록
    void addReview(Seller seller, Review review);

}
