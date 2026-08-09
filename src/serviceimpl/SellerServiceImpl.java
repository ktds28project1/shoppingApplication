package serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Product;
import domain.Seller;
import service.SellerService;
import util.Reader;

public class SellerServiceImpl implements SellerService {
	private Map<String, Seller> sellerList;


	public SellerServiceImpl() {
		this.sellerList = new HashMap<>();
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

		String sellerSid = (Math.random()*1000) + "";


		this.sellerList.put(businessNumber, new Seller(name, businessNumber, representativeName, representativeNumber,
				sellerAddress, sellerPassWord, sellerSid));

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

	//판매자 수정
	@Override
	public void modifySeller(Seller seller) {
		

		if (seller == null) {
		    System.out.println("로그인이 필요한 기능입니다.");
		    return;
		}
		
		System.out.println("\n 판매자 정보 수정 ");
		
		String password = Reader.readString("비밀번호를 입력하세요: ");
		if (!seller.getPassword().equals(password)) {
			System.out.println("잘못된 비밀번호 입니다.");
			return;
		}

		// 수정할 정보 입력
		String companyName = Reader.validateInput("변경할 상호명: ");
        String ownerName = Reader.validateInput("변경할 대표자명: ");
        String ownerPhone = Reader.validateInput("변경할 대표자 연락처: ");
        String address = Reader.validateInput("변경할 사업장 주소: ");
        String newPassword = Reader.validateInput("변경할 비밀번호: ");


        seller.setName(companyName);          // 상호명 (User)
        seller.setOwnerName(ownerName);       // 대표자명 (Seller)
        seller.setPhoneNumber(ownerPhone);    // 연락처 (User)
        seller.setAddress(address);           // 주소 (User)
        seller.setPassword(newPassword);      // 비밀번호 (User)
        
        System.out.println("정보 수정이 완료되었습니다.");
	}


  @Override
  public void replyInquiry(int inquiryNumber) {
    // TODO Auto-generated method stub
    
  }

}
