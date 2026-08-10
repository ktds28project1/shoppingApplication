package serviceimpl;

import domain.Buyer;
import domain.Order;
import domain.Review;
import service.ReviewService;
import util.Reader;

import java.util.Map;

import common.ShoppingData;

public class ReviewServiceImpl implements ReviewService {

	private Map<Long, Order> orderList;
	
    private Map<Long, Review> reviewMap;
    private long reviewSeq = 1L; // 리뷰 번호 자동 생성

    public ReviewServiceImpl(ShoppingData data){
    	this.orderList = data.orderList();
    	this.reviewMap = data.reviewList();
    }
    
    @Override
    public void addReview(Buyer buyer) {
        if (buyer == null) {
            System.out.println("로그인이 필요한 기능입니다.");
            return;
        }

        System.out.println("\n=== [" + buyer.getUserId() + "]님의 리뷰 작성 ===");

        // 리뷰 작성이 가능한 본인의 구매 내역 출력
        boolean hasOrder = false;
        System.out.println("[나의 구매 내역]");
        for (Order order : orderList.values()) {
            if (order.getBuyer().equals(buyer.getUserId())) {
                System.out.println("- 주문번호: " + order.getOrderNumber() + " | 상품번호: " + order.getProduct() + " | 수량: " + order.getQuantity());
                hasOrder = true;
            }
        }

        if (!hasOrder) {
            System.out.println("구매하신 상품 내역이 없어 리뷰를 작성할 수 없습니다.");
            return;
        }

        // 리뷰 작성할 상품 선택 및 구매 이력 검증
        long targetProductNum = (long) Reader.readInt("\n리뷰를 작성할 상품 번호를 입력해주세요: ");

        boolean canReview = false;
        for (Order order : orderList.values()) {
            if (order.getBuyer().equals(buyer.getUserId()) && order.getProduct() == targetProductNum) {
                canReview = true;
                break;
            }
        }

        if (!canReview) {
            System.out.println("해당 상품을 구매한 이력이 확인되지 않습니다.");
            return;
        }

        // 중복 리뷰 작성 검증
        for (Review existingReview : reviewMap.values()) {
            // 현재 로그인한 구매자 아이디와 선택한 상품 번호가 일치하는 리뷰가 이미 존재한다면
            if (existingReview.getBuyer().equals(buyer.getUserId()) && existingReview.getProductNumber() == targetProductNum) {
                System.out.println("이미 해당 상품에 대한 리뷰를 등록하셨습니다. (중복 리뷰 등록 불가)");
                return; // 등록 중단
            }
        }

        // 리뷰 작성
        String content = Reader.validateInput("1. 리뷰 내용을 작성해주세요: ");

        int rating = 0;
        while (true) {
            rating = Reader.readInt("2. 별점을 입력해주세요 (1~5): ");
            if (rating >= 1 && rating <= 5) {
                break;
            }
            System.out.println("별점은 1에서 5 사이의 숫자만 입력 가능합니다.");
        }

        // 리뷰 객체 생성 및 저장
        long currentReviewNum = reviewSeq++;
        Review review = new Review(targetProductNum, buyer.getUserId(), content, (double) rating);

        reviewMap.put(currentReviewNum, review);

        System.out.println("\n리뷰가 성공적으로 등록되었습니다! (리뷰 번호: " + currentReviewNum + ")");

    }
}
