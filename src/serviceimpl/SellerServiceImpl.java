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

	public boolean isEmpty(String str) {
		str = str.trim();
		Predicate<String> Value = (s) -> s == (null) || s.isBlank();
		boolean isEmpty = Value.test(str);
		if (isEmpty) {
			System.out.println("공백은 입력하실수 없습니다.");
			return true;
		}
		return false;
	}

	@Override
	public void addSeller() {

		String name = null;
		String BusinessNumber = null;
		String RepresentativeName = null;
		String RepresentativeNumber = null;
		String SellerAddress = null;
		String SellerPassWord = null;

		name = Reader.readString("이름 : ").trim();
		BusinessNumber = Reader.readString("사업자 등록번호 : ").trim();
		RepresentativeName = Reader.readString("대표자 명 : ").trim();
		RepresentativeNumber = Reader.readString("대표자 연락처 : ").trim();
		SellerAddress = Reader.readString("사업장 주소 : : ").trim();
		SellerPassWord = Reader.readString("판매자 비밀번호 : ").trim();

		if (isEmpty(name) || isEmpty(BusinessNumber) || isEmpty(RepresentativeName) || isEmpty(RepresentativeNumber)
				|| isEmpty(SellerAddress) || isEmpty(SellerPassWord)) {
			return;
		}

		for (int i = 0; i < sellerList.size(); i++) {

			if (sellerList.get(i).getUserId().equals(BusinessNumber)) {

				System.out.println("중복된 사업자 등록번호입니다");
				return;
			}
		}

		this.sellerList.put(BusinessNumber, new Seller(name, BusinessNumber, RepresentativeName, RepresentativeNumber,
				SellerAddress, SellerPassWord));

	}

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
			if(equals) {
				System.out.println(sellerList.get(businessNumber).getName()+ "님 환영합니다.");
				return sellerList.get(businessNumber);
			}

		}
		return null;
	}

}
