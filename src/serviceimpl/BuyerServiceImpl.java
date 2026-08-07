package serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import domain.Buyer;
import domain.Order;
import domain.Product;
import service.BuyerService;
import util.Reader;

public class BuyerServiceImpl implements BuyerService {
	private Map<String, Buyer> buyerMap;
	private List<Order> orderList;
	

	public BuyerServiceImpl() {
		this.buyerMap = new HashMap<>();
		this.orderList = new ArrayList<>();
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
		this.setBuyer(buyer, "탈퇴한 사용자 수정 발생", () -> true,  () -> {
			// FIXME 입력 유효성 검사 유무 확인 필요
			buyer.setName(Reader.readString("변경할 이름: "));
			buyer.setPassword(Reader.readString("변경할 비밀번호: "));
			buyer.setAddress(Reader.readString("변경할 주소: "));
			buyer.setPhoneNumber(Reader.readString("변경할 전화번호: "));
		});
	}

	@Override
	public void deleteBuyer(Buyer buyer) {
		this.setBuyer(buyer, "탈퇴한 사용자 재탈퇴 시도 발생"
					 , () -> buyer.getUserId() == Reader.readString("아이디를 입력하세요: ") 
					 , () -> buyer.setActive(false));
	}

    @Override
    public void searchProductByKeyword(List<Product> productList) {
        final String keyword = Reader.readString("검색어를 입력하세요: "); 

        List<Product> searchedProducts = new ArrayList<>();
        productList.stream() // Stream<Product>
                   .filter(product -> product.getName().contains(keyword)) // Stream<Product>
                   .forEach(product -> searchedProducts.add(product)); // void
      

        printSearchedProduct(searchedProducts);
    }
    
    private void printSearchedProduct(List<Product> productList) {
      for (Product p : productList) {
        // TODO 검색된 상품 리스트 출력 구현
      }
    }

    @Override
    public void printProductDetailByNumber(List<Product> productList) {
      // TODO Auto-generated method stub
      
    }
    
	private void setBuyer(Buyer buyer, String err, Supplier<Boolean> check, Runnable action) {
		if (!buyer.isActive()) {
			throw new IllegalStateException(err);
		}
		
		if (!check.get()) {
			System.out.println("아이디가 틀렸습니다.");
		}
		
		String password = Reader.readString("비밀번호를 입력해주세요: ");
		if (buyer.getPassword().equals(password)) {
			action.run();
		} else {
			System.out.println("비밀번호를 틀렸습니다.");
		}
	}

	@Override
	public void buyProduct(Buyer buyer, Map<Long, Product> productMap) {
		
		
	}

	@Override
	public void printOrderList(Buyer buyer) {
		
		
	}
}
