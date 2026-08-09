package service;

import java.util.List;

import domain.Inquiry;
import domain.Product;
import domain.Seller;

public interface SellerService {

	// 판매자 등록
	void addSeller();
	
	// 판매자 로그인
	Seller sellerLogin();

	void modifySeller(Seller seller);
	
	/**
	 * 구매자 문의 답변 
	 * 1. 존재하는 문의인지 문의 번호 검증 
	 * 2. 로그인한 판매자의 상품에 대한 문의인지 검증 
	 * 3. 유효한 입력인지 검증 (공백)
	 * 
	 * @param seller 로그인한 판매자
	 * @param productList
	 * @param inquiryList
	 */
	void replyInquiry(Seller seller, List<Product> productList, List<Inquiry> inquiryList);

  // 문의 조회
  void printInquiry(Seller seller);
  
}
