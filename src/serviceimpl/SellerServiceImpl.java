package serviceimpl;

import java.util.HashMap;
import java.util.Map;

import domain.Seller;
import service.SellerService;
import util.Reader;

public class SellerServiceImpl implements SellerService {
	private Map<String, Seller> sellerList;

	public SellerServiceImpl() {
		sellerList = new HashMap<>();
	}
	
	
	//사업자 등록 
	@Override
	public void addSeller() {

		

		String name = Reader.validateInput("이름 : ");
		String businessNumber = Reader.validateInput("사업자 등록번호 : ");
		if (sellerList.containsKey(businessNumber)) {

			System.out.println("중복된 사업자 등록번호입니다");
			return;
		}
		
		String representativeName = Reader.validateInput("대표자 명 : ");
		String representativeNumber = Reader.validateInput("대표자 연락처 : ");
		String sellerAddress = Reader.validateInput("사업장 주소 : : ");
		String sellerPassWord = Reader.validateInput("판매자 비밀번호 : ");

		

		this.sellerList.put(businessNumber, new Seller(name, businessNumber, representativeName, representativeNumber,
				sellerAddress, sellerPassWord));

	}
	
	
	//판매자 로그인 
	@Override
	public Seller sellerLogin() {

		int count = 0;

		while (count < 5) {

			String businessNumber = Reader.readString("사업자 등록번호 : ");

			if (!sellerList.containsKey(businessNumber)) {
				System.out.println("입력하신 사업자 등록번호가 없습니다.");
				count++;
				continue;
			}

			String password = Reader.readString("판매자 비밀번호 : ");

			if (!sellerList.get(businessNumber).getPassword().equals(password)) {
				System.out.println("잘못된 비밀번호 입니다.");
				count++;
				continue;
			}

			
			System.out.println(sellerList.get(businessNumber).getName() + "님 환영합니다.");
			return sellerList.get(businessNumber);
			

		}
		return null;
	}

}
