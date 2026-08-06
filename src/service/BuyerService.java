package service;

import domain.Buyer;

public interface BuyerService {

	// 구매자 등록
	void registBuyer();
	// 구매자 로그인
	Buyer buyerLogin();
	// 구매자 정보 수정
	void modifyBuyer(String id);
	
	// 구매자 탈퇴
	void deleteBuyer(String id);
	
	// 상품 조회
	
	// 상품 상세 조회
	
	// 상품 구매
	
	// 상품 리뷰 등록
	
	// 상품 문의 등록
	
	// 거래 목록 조회
	
	// 문의 목록 조회
	
	// 문의 내용 조회
}
