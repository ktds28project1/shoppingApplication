package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Inquiry;
//import domain.Product; // 나중에 product 리스트의 <>을 Product에 넣을 계획중 
//import domain.User; //나중에 user데이터 클래스 에서 userId가져오기 
import util.Reader;

public class ProductInquiry {
	private static Map<Long, Inquiry> inquiryList;
//	private static User user = "asdf";
	private static TestUser user = new TestUser();
	private static List<Long> product;
	
	
//	public ProductInquiry() {
//		this.inquiryList = new HashMap<>();
//	}
	
	public static void notFoundProductNumber(long productNumber) {
		product = List.of(1234l,345l,35l,56l,76576l,878l,1234l);
		for(int i = 0; i < product.size(); i ++) {
			if(product.get(i) == productNumber) {
				return;
			}
		}
		System.out.println("유효하지 않은 상품번호입니다.");
		
	}
	
	public static void addInquiry() {
		inquiryList = new HashMap<>();
//		user.getUserid();
		long inquiryNumber = 1;
		
		long productNumber = (long)Reader.readInt("문의하실 상품번호 ");
		//상품번호가 맞지않을 경우
		notFoundProductNumber(productNumber);
		
		String inquiryUserId = user.getUserid();
		String title = Reader.readString("문의 제목 ");
		
		String content = Reader.readString("문의 내용을 작성해주세요");
		
//		this.
		inquiryList.put(productNumber, new Inquiry(inquiryNumber, productNumber,
				                                   inquiryUserId, title, content));
		inquiryNumber +=1 ;
	}
	
	public static void main(String[] args) {
		addInquiry();
	}

}
