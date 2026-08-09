package main;

import java.util.HashMap;
import java.util.Map;

import common.ShoppingData;
import domain.Buyer;
import domain.Inquiry;
import domain.Order;
import domain.Product;
import domain.Review;
import domain.Seller;
import main.menus.BuyerMenu;
import main.menus.SellerMenu;
import service.BuyerService;
import service.SellerService;
import serviceimpl.BuyerServiceImpl;
import serviceimpl.SellerServiceImpl;
import util.MenuRender;
import util.Reader;

public class Main {
	
	public static void main(String[] args) {
		
		Map<String, Buyer> buyerList = new HashMap<>();
		Map<String, Seller> sellerList = new HashMap<>();
		Map<Long, Product> productList = new HashMap<>();
		Map<Long, Order> orderList = new HashMap<>();
		Map<Long, Review> reviewList = new HashMap<>();
		Map<Long, Inquiry> inquiryList = new HashMap<>();
		ShoppingData data = new ShoppingData(buyerList, sellerList, productList, orderList, reviewList, inquiryList);
		
		BuyerService buyerService = new BuyerServiceImpl(data);
		SellerService sellerService = new SellerServiceImpl(data);
		
		Buyer buyer = null;
		Seller seller = null;
		
		while(true) {
			
			// 로그인 로직
			while(buyer == null && seller == null) {
				
				printMenu();
				
				int answer = Reader.readInt("메뉴 선택: ",1,4);
				
				if ( answer == 1 ) {
					buyer = buyerService.buyerLogin();
				} else if ( answer == 2 ) {
					buyerService.registBuyer();
				} else if ( answer == 3 ) {
					seller = sellerService.sellerLogin();
				} else if ( answer == 4 ) {
					sellerService.addSeller();
				}
			}
			
			// buyer 로그인
			if(buyer != null) {
				MenuRender.render("====== 구매자 서비스 ======", "로그아웃", BuyerMenu.values(), buyerService, buyer);
			}
			// seller 로그인
			else if(seller != null) {
				MenuRender.render("====== 판매자 서비스 ======", "로그아웃", SellerMenu.values(), sellerService, seller);
			}
			
			buyer = null;
			seller = null;
		}
	}
	
	private static void printMenu() {
		System.out.println("1. 구매자 로그인");
		System.out.println("2. 구매자 회원가입");
		System.out.println("3. 판매자 로그인");
		System.out.println("4. 판매자 회원가입");
	}
}
