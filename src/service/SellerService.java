package service;

import domain.Seller;

public interface SellerService {

	// 판매자 등록
	void addSeller();
	
	// 판매자 로그인
	Seller SellerLogin();
    
	// 판매자 상품관리
	void modifySeller(Seller seller);
	
	// 문의 조회
	
	// 문의 답변
}
