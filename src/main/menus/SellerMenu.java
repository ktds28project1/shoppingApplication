package main.menus;

import java.util.function.BiConsumer;

import common.Menu;
import domain.Seller;
import service.SellerService;

public enum SellerMenu implements Menu<Seller, SellerService> {
	
	정보수정("정보수정",(service,target) -> service.modifySeller(target))
	, 상품관리("상품관리",(service,target) -> service.manageProducts(target))
	//, 문의목록조회("문의목록조회",(service,target)->{})
	, 문의내용조회("문의내용조회",(service,target)-> service.printInquiry(target) )
	, 문의답변("문의답변",(service,target)-> service.replyInquiry(target) )
	;

	private final String description;
	private final BiConsumer<SellerService, Seller> order;

	SellerMenu(String description, BiConsumer<SellerService, Seller> order){
		this.description = description;
		this.order = order;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void order(SellerService service, Seller target) {
		order.accept(service, target);
	}
}
