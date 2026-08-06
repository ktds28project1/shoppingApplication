package serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

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
		String BusinessNumber = Reader.validateInput("사업자 등록번호 : ");
		String RepresentativeName = Reader.validateInput("대표자 명 : ");
		String RepresentativeNumber = Reader.validateInput("대표자 연락처 : ");
		String SellerAddress = Reader.validateInput("사업장 주소 : : ");
		String SellerPassWord = Reader.validateInput("판매자 비밀번호 : ");

		

		if (sellerList.containsKey(BusinessNumber)) {

			System.out.println("중복된 사업자 등록번호입니다");
			return;
		}

		this.sellerList.put(BusinessNumber, new Seller(name, BusinessNumber, RepresentativeName, RepresentativeNumber,
				SellerAddress, SellerPassWord));

	}
	
	
	//판매자 로그인 
	@Override
	public Seller SellerLogin() {

		boolean equals = false;

		int count = 0;

		while (count < 5) {

			String businessNumber = Reader.readString("사업자 등록번호 : ").trim();

			if (!sellerList.containsKey(businessNumber)) {
				System.out.println("입력하신 사업자 등록번호가 없습니다.");
				count++;
				continue;
			}

			String password = Reader.readString("판매자 비밀번호 : ").trim();

			if (!sellerList.get(businessNumber).getPassword().equals(password)) {
				System.out.println("잘못된 비밀번호 입니다.");
				count++;
				continue;
			}

			equals = true;
			if (equals) {
				System.out.println(sellerList.get(businessNumber).getName() + "님 환영합니다.");
				return sellerList.get(businessNumber);
			}

		}
		return null;
	}

}
