package serviceimpl;

import java.util.List;
import java.util.Map;

import common.ShoppingData;
import domain.Inquiry;
import domain.Product;
import domain.Seller;
import service.ProductService;
import service.SellerService;
import util.Reader;

public class SellerServiceImpl implements SellerService {
	private Map<String, Seller> sellerList;
	private Map<Long, Inquiry> inquiryList;
	private Map<Long, Product> productList;
	private ProductService product;

	public SellerServiceImpl(ShoppingData shoppingData) {
		this.sellerList = shoppingData.sellerList();
		this.inquiryList = shoppingData.inquiryList();
		this.productList = shoppingData.productList();
		this.product = new ProductServiceImpl(shoppingData);
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

	// 문의 답변
	@Override
	public void replyInquiry(Seller seller) {
		Inquiry inquiry = getInquiryByNumber(this.inquiryList.values().stream().toList());
		if (canAnswer(seller, inquiry, this.productList.values().stream().toList())) {
			inquiry.setAnswer(inputInquiryAnswer());			
		}
	}
	
	private Inquiry getInquiryByNumber(List<Inquiry> inquiryList) {
		long inquiryNumber = 0;
	    while (true) {
	    	inquiryNumber = Reader.readInt("답변할 문의 번호를 입력하세요: ");
	    	for (Inquiry i : inquiryList) {
	    		if (i.getInquiryNumber() == inquiryNumber) {
	    			return i;
	    		}
	    	}
	    	System.out.println("해당 번호의 문의가 존재하지 않습니다. 다시 입력해주세요.");
	    }
	}
	
	private boolean canAnswer(Seller seller, Inquiry inquiry, List<Product> productList) {
		if (!isSellersProduct(seller, inquiry, productList)) {
			System.out.println("로그인한 판매자의 상품이 아닙니다.");
			return false;
		}
		if (inquiry.getAnswer() != null) {
			System.out.println("이미 답변이 완료된 문의입니다.");
			return false;
		}
		return true;
	}
	
	private boolean isSellersProduct(Seller seller, Inquiry inquiry, List<Product> productList) {
		Product product = getProductByNumber(inquiry.getProductNumber(), productList);
		if (product == null) {
			System.out.println("해당 상품이 존재하지 않습니다.");
			return false;
		} else {
			return seller.getSid().equals(product.getSeller());			
		}
	}
	
	private Product getProductByNumber(long productNumber, List<Product> productList) {
		return productList.stream() // Stream<Product>
				.filter(p -> p.getProductNumber() == productNumber) // Stream<Product>
				.findFirst() // Optional<Product>
				.orElse(null); // Product
	}
	  
	private String inputInquiryAnswer() {
		return Reader.validateInput("구매자 문의 답변");
	}
	
	  //구매자 문의 내용 조회
		@Override
		public void printInquiry(Seller seller) {
			boolean found = false;
			for(Product product : this.productList.values()) {
				if(product.getSeller().equals(seller.getSid())) {
					System.out.println(product.getProductNumber() +". "+ product.getName());
				}
			}
			long productNumber = (long)Reader.readInt("문의를 조회하실 상품의 번호를 입력하세요.");
			Product product = this.productList.get(productNumber);
			if (product == null) {
				System.out.println("존재하지 않는 상품입니다.");
				return;
			}
			if(!(product.getSeller().equals(seller.getSid()))) {
				System.out.println("본인이 판매하는 상품이 아닙니다.");
				return;
			}
			for (Inquiry inq : this.inquiryList.values()) {
				if (inq.getProductNumber() == productNumber) {
					System.out.println(inq);
					found = true;
				}
			}
			if(!found) {
				System.out.println("등록된 문의가 없습니다.");
			}
		}
  
		@Override
		public void manageProducts(Seller seller) {
			this.product.manageProducts(seller);
		}
}
