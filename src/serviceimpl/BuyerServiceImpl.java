package serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import domain.Buyer;
import service.BuyerService;
import util.Reader;

public class BuyerServiceImpl implements BuyerService {
	private Map<String, Buyer> buyerMap;

	public BuyerServiceImpl() {
		this.buyerMap = new HashMap<>();
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
		this.setBuyer(buyer, "탈퇴한 사용자 수정 발생", () -> {
			// FIXME 입력 유효성 검사 유무 확인 필요
			buyer.setName("");
			buyer.setPassword("");
			buyer.setAddress("");
			buyer.setPhoneNumber("");
		});
	}

	@Override
	public void deleteBuyer(Buyer buyer) {
		this.setBuyer(buyer, "탈퇴한 사용자 재탈퇴 시도 발생", () -> buyer.setActive(false));
	}

	private void setBuyer(Buyer buyer, String err, Runnable action) {
		if (!buyer.isActive()) {
			throw new IllegalStateException(err);
		}
		
		String password = Reader.readString("비밀번호를 입력해주세요: ");
		if (buyer.getPassword().equals(password)) {
			action.run();
		} else {
			System.out.println("비밀번호를 틀렸습니다.");
		}
	}
}
