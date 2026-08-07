package serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Buyer;
import domain.Inquiry;
import domain.Product;
import domain.User;
import service.BuyerService;
import util.Reader;

public class BuyerServiceImpl implements BuyerService {
	private Map<String, Buyer> buyerMap;
	private static Map<Long, Inquiry> inquiryList;
	private static Map<Long, Product> productList;
	private static User user;
	
	public BuyerServiceImpl() {
		this.buyerMap = new HashMap<>();
		this.productList = new HashMap<>();
		this.inquiryList = new HashMap<>();
		
	}

	public boolean findUserId(String userId) {
		if (buyerMap.containsKey(userId)) {
			return true;
		}

		return false;
	}

	public String inputUserId(String message) {
		while (true) {
			String input = Reader.validateInput(message);
			if (findUserId(input)) {
				System.out.println("중복된 아이디 입니다.");
				continue;
			}
			return input;
		}
	}

	public boolean checkPassword(String userId, String password) {
		if (buyerMap.containsKey(userId) && buyerMap.get(userId).getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	public void notFoundProductNumber(long productNumber) {
		
		
		if (this.productList.containsKey(productNumber)) {
			return;
		}

		System.out.println("유효하지 않은 상품번호입니다.");
		
	}

	@Override
	public void registBuyer() {
		System.out.println("=====구매자 등록=====");
		String userId = inputUserId("구매자 아이디");
		String username = Reader.validateInput("이름");
		String password = Reader.validateInput("비밀번호");
		String address = Reader.validateInput("주소");
		String phoneNumber = Reader.validateInput("연락처");

		this.buyerMap.put(userId, new Buyer(userId, username, password, address, phoneNumber));
	}

	@Override
	public Buyer buyerLogin() {
		int failCount = 0;
		while (true) {
			if (failCount >= 5) {
				System.out.println("5회 이상 로그인 실패로 더 이상 로그인 시도를 할 수 없습니다.");
				return null;
			}
			String userId = Reader.readString("구매자 아이디");
			if (!findUserId(userId)) {
				System.out.println("존재하지 않거나 잘못된 아이디입니다");
				failCount++;
				continue;
			}
			Buyer buyer = this.buyerMap.get(userId);
			String password = Reader.readString("비밀번호");

			if (checkPassword(userId, password)) {
				return buyer;
			}
			System.out.println("잘못된 비밀번호 입니다. (" + (failCount + 1) + "회 실패)");
			failCount++;

		}
	}

	@Override
	public void modifyBuyer(Buyer buyer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBuyer(Buyer buyer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInquiry() {

		

		long inquiryNumber = this.inquiryList.size() + 1;

		long productNumber = (long) Reader.readInt("문의하실 상품번호 ");
		
		// 상품번호가 맞지않을 경우
		notFoundProductNumber(productNumber);

		String inquiryUserId = user.getUserId();
		String title = Reader.readString("문의 제목 ");

		String content = Reader.readString("문의 내용을 작성해주세요");

		this.inquiryList.put(productNumber, new Inquiry(inquiryNumber, productNumber, inquiryUserId, title, content));
		
		
	}


}
