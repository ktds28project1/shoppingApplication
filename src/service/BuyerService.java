package service;

import java.util.List;
import java.util.Map;
import domain.Buyer;
import domain.Product;
import domain.User;

public interface BuyerService {

	// 구매자 등록
	void registBuyer();
	// 구매자 로그인
	Buyer buyerLogin();
	/**
	 * 구매자를 받아 정보를 수정하는 메소드
	 * 내부에서 한번 더 비밀번호 입력 받음
	 * @param buyer 로그인한 구매자
	 * @throws IllegalStateException 이미 탈퇴한 사용자인 경우 예외 발생
	 */
	void modifyBuyer(Buyer buyer);
	
	/**
	 * 구매자를 받아 탈퇴 시키는 메소드
	 * 내부에서 한번 더 비밀번호 입력 받음
	 * @param buyer 로그인한 구매자
	 * @throws IllegalStateException 이미 탈퇴한 사용자인 경우 예외 발생
	 */
	void deleteBuyer(Buyer buyer);
	
	/**
	 * 상품 검색 및 조회 메소드
	 * 상품의 이름으로 검색해서 검색된 상품 목록을 출력
	 * 검색어는 내부에서 입력 받음 (미입력 시 전체 조회)
	 */
	void searchProductByKeyword(List<Product> productList);
	
    /**
     * 상품 상세 조회 메소드
     * 상품의 번호를 입력해서 상세 정보를 출력
     * 번호는 내부에서 입력 받음
     */
	void printProductDetailByNumber(List<Product> productList);
	
	// 상품 구매
	void buyProduct(Buyer buyer, Map<Long, Product> productMap);
	
	// 상품 리뷰 등록
	
	// 상품 문의 등록
	void addInquiry(User user);

	// 거래 목록 조회
	void printOrderList(Buyer buyer);

	// 문의 목록 조회
	
	// 문의 내용 조회
}
