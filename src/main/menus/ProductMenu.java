package main.menus;

import java.util.function.BiConsumer;

import common.Menu;
import domain.Seller;
import serviceimpl.ProductServiceImpl;

/**
 * 미완
 */
public enum ProductMenu implements Menu<Seller, ProductServiceImpl> {
	상품등록("상품 정보 등록(입고)", (service,target) -> service.addProduct(target) )
	, 상품재입고("상품 재 입고(품절 후 재 입고)", (service,target) -> service.restockProduct(target) )
	, 상품삭제("상품 정보 삭제", (service,target) -> service.deleteProduct(target) )
	, 상품수정("상품 정보 수정", (service,target) -> service.updateProduct(target) )
	, 상품조회("내 등록 상품 목록 보기", (service,target) -> service.printMyProducts(target) )
	; 
	
	
	private final String description;
	private final BiConsumer<ProductServiceImpl, Seller> order;
	
	private ProductMenu() {
		this.description = null;
		this.order = null;
	}
	
	ProductMenu(String description, BiConsumer<ProductServiceImpl, Seller> order){
		this.description = description;
		this.order = order;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void order(ProductServiceImpl service, Seller target) {
		order.accept(service, target);
	}
}
