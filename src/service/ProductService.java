package service;

import domain.Seller;

public interface ProductService {

    // 판매자 상품관리
    void manageProducts(Seller seller);
    
    /**
     * 상품 검색 및 조회 메소드
     * 상품의 이름으로 검색해서 검색된 상품 목록을 출력
     * 검색어는 내부에서 입력 받음 (미입력 시 전체 조회)
     */
    void searchProductByKeyword();
    
    /**
     * 상품 상세 조회 메소드
     * 상품의 번호를 입력해서 상세 정보를 출력
     * 번호는 내부에서 입력 받음
     */
    void printProductDetailByNumber();
    

}
