package main.menus;

import java.util.function.BiConsumer;

import common.Menu;
import domain.Buyer;
import service.BuyerService;

public enum BuyerMenu implements Menu<Buyer, BuyerService> {
	정보수정("정보수정", (target,service) -> service.modifyBuyer(target) )
	, 탈퇴("탈퇴", (target,service) -> service.deleteBuyer(target) )
	, 상품조회("상품조회", (target,service) -> service.searchProductByKeyword(target) )
	, 상품상세조회("상품상세조회", (target,service) -> service.printProductDetailByNumber(target) )
	, 상품구매("상품구매", (target,service) -> service.buyProduct(target) )
	, 상품리뷰등록("상품리뷰등록", (target,service) -> service.addReview(target) )
	, 상품문의등록("상품문의등록", (target,service) -> service.addInquiry(target) )
	, 거래내용확인("거래내용확인", (target,service) -> service.printOrderList(target) )
	;

	private final String description;
	private final BiConsumer<Buyer, BuyerService> order;
	
	BuyerMenu(String description, BiConsumer<Buyer, BuyerService> order){
		this.description = description;
		this.order = order;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void order(BuyerService service, Buyer target) {
		this.order.accept(target, service);
	}
}
