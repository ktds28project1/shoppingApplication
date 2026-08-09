package serviceimpl;

import java.util.ArrayList;
import java.util.List;

import common.ShoppingData;
import domain.Buyer;
import domain.Inquiry;
import domain.Product;
import domain.Review;
import domain.Seller;
import main.menus.ProductMenu;
import service.ProductService;
import util.MenuRender;
import util.Reader;

public class ProductServiceImpl implements ProductService {

    private List<Product> productList;
    private long productSeq = 1000L; // 상품번호 생성
    
    private List<Seller> sellerList;
    private List<Review> reviewList;
    private List<Inquiry> inquiryList;
    private List<Buyer> buyerList;
    
    public ProductServiceImpl(ShoppingData data) {
        this.productList = data.productList().values().stream().toList();
        this.sellerList = data.sellerList().values().stream().toList();
        this.reviewList = data.reviewList().values().stream().toList();
        this.inquiryList = data.inquiryList().values().stream().toList();
        this.buyerList = data.buyerList().values().stream().toList();
    }

    @Override
    public void manageProducts(Seller seller) {
        if (seller == null) {
            System.out.println("로그인이 필요한 기능입니다.");
            return;
        }
        
        String title = "\n=== [" + seller.getOwnerName() + "] 상품 관리 메뉴 ===";
        String back = "이전 메뉴로 돌아가기";
        
        MenuRender.render(title, back, ProductMenu.values(), this, seller);
        
        /* 상단 MenuRender 로 대체
        while (true) {
            System.out.println("\n=== [" + seller.getOwnerName() + "] 상품 관리 메뉴 ===");
            System.out.println("1. 상품 정보 등록(입고)");
            System.out.println("2. 상품 재 입고(품절 후 재 입고)");
            System.out.println("3. 상품 정보 삭제");
            System.out.println("4. 상품 정보 수정");
            System.out.println("5. 내 등록 상품 목록 보기");
            System.out.println("0. 이전 메뉴로 돌아가기");

            int menu = Reader.readInt("메뉴 선택 : ");

            if (menu == 1) {
                addProduct(seller);
            } else if (menu == 2) {
                restockProduct(seller);
            } else if (menu == 3) {
                deleteProduct(seller);
            } else if (menu == 4) {
                updateProduct(seller);
            } else if (menu == 5) {
                printMyProducts(seller);
            } else if (menu == 0) {
                System.out.println("상품 관리를 종료합니다.");
                return;
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
            }
        }
        */
    }

    // 상품 정보 등록(입고)
    public void addProduct(Seller seller) {
        System.out.println("\n--- 상품 등록 ---");
        String name = Reader.validateInput("상품 명 : ");
        int price = Reader.readInt("상품 가격(할인 전 가격) : ");
        int stock = Reader.readInt("상품 재고 수 : ");
        String description = Reader.validateInput("상품 설명 : ");

        long productNum = ++productSeq;
        Product product = new Product(productNum, seller.getSid(), name, price, stock, description);

        productList.add(product);

        System.out.println("상품 등록이 완료되었습니다. (상품 번호: " + productNum + ")");
    }

    // 상품 재 입고(품절 후 재 입고)
    public void restockProduct(Seller seller) {
        System.out.println("\n--- 상품 재 입고 ---");
        long productNum = Reader.readInt("재입고할 상품 번호 : ");
        Product product = findMyProduct(seller, productNum);

        if (product == null) return;

        int addStock = Reader.readInt("추가 입고 수량 : ");
        if (addStock <= 0) {
            System.out.println("1개 이상의 수량을 입력해야 합니다.");
            return;
        }

        product.setStock(product.getStock() + addStock);
        if (!product.isActive()) {
            product.setActive(true);
        }
        System.out.println("재고가 추가되었습니다. 현재 재고: " + product.getStock() + "개");
    }

    // 상품 정보 삭제 (판매 중단 처리)
    public void deleteProduct(Seller seller) {
        System.out.println("\n--- 상품 삭제 ---");
        long productNum = Reader.readInt("삭제할 상품 번호 : ");
        Product product = findMyProduct(seller, productNum);

        if (product == null) return;

        product.setActive(false);

        System.out.println("상품 번호 " + productNum + " 번 상품이 삭제(판매 중단)되었습니다.");
    }

    // 상품 정보 수정 (상품명, 가격, 상품 설명)
    public void updateProduct(Seller seller) {
        System.out.println("\n--- 상품 정보 수정 ---");
        long productNum = Reader.readInt("수정할 상품 번호 : ");
        Product product = findMyProduct(seller, productNum);

        if (product == null) return;

        System.out.println("수정할 정보를 입력해 주세요. (변경하지 않으려면 엔터 입력)");

        String newName = Reader.readString("새 상품 명 [" + product.getName() + "] : ");
        if (!newName.isBlank()) {
            product.setName(newName);
        }

        String priceStr = Reader.readString("새 상품 가격 [" + product.getPrice() + "] : ");
        if (!priceStr.isBlank()) {
            try {
                product.setPrice(Integer.parseInt(priceStr));
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력 가능합니다. 가격 수정이 취소되었습니다.");
            }
        }

        String newDesc = Reader.readString("새 상품 설명 [" + product.getDescription() + "] : ");
        if (!newDesc.isBlank()) {
            product.setDescription(newDesc);
        }

        System.out.println("상품 정보 수정이 완료되었습니다.");
    }

    // 내 상품 목록 조회
    public void printMyProducts(Seller seller) {
        System.out.println("\n--- 내 등록 상품 목록 ---");
        boolean hasProduct = false;

        for (Product product : productList) {
            if (product.getSeller().equals(seller.getSid()) && product.isActive()) {
                System.out.printf("[%d] %s | 가격: %d원 | 재고: %d개 | 설명: %s\n",
                        product.getProductNumber(),
                        product.getName(),
                        product.getPrice(),
                        product.getStock(),
                        product.getDescription());
                hasProduct = true;
            }
        }

        if (!hasProduct) {
            System.out.println("등록되었거나 활성화된 상품이 없습니다.");
        }
    }

    private Product findMyProduct(Seller seller, long productNumber) {
        for (Product product : productList) {
            if (product.getProductNumber() == productNumber) {
                if (!product.getSeller().equals(seller.getSid())) {
                    System.out.println("해당 상품을 관리할 권한이 없습니다.");
                    return null;
                }
                return product;
            }
        }

        System.out.println("존재하지 않는 상품 번호입니다.");
        return null;
    }

    @Override
    public void searchProductByKeyword() {
        final String keyword = Reader.readString("검색어를 입력하세요: ");

        List<Product> searchedProducts = new ArrayList<>();
        this.productList.stream() // Stream<Product>
                   .filter(product -> product.getName().contains(keyword)) // Stream<Product>
                   .forEach(product -> searchedProducts.add(product)); // void
      

        printSearchedProducts(searchedProducts, sellerList,  reviewList);
    }
    
    private void printSearchedProducts(List<Product> productList, List<Seller> sellerList, List<Review> reviewList) {
      for (Product p : productList) {
        System.out.println("상품 번호: " + p.getProductNumber());
        System.out.println("판매자 명: " + getSellerByProduct(p, sellerList).getName());
        System.out.println("상품 명: " + p.getName());
        System.out.println("상품 가격: " + p. getPrice());
        System.out.println("상품 할인 가격: ");	// FIXME 할인 가격 따로 출력하도록 수정 필요
        System.out.println("상품 재고: " + getStockString(p.getStock()));
        System.out.println("누적 구매 횟수: " + p.getBuyCount());
        System.out.println("별점 평균: " + getAverageRating(getReviewsByProduct(p, reviewList)));
      }
    }
    
    private Seller getSellerByProduct(Product product, List<Seller> sellerList) {
    	return sellerList.stream() // Stream<Seller>
    			.filter(s -> product.getSeller() == s.getSid()) // Stream<Seller>
    			.findFirst() // Optional<Seller>
    			.orElse(null); // Seller
    }
    

    private String getStockString(int stock) {
		return stock <= 0 ? "품절" : stock+"";
	}
    
    private List<Review> getReviewsByProduct(Product product, List<Review> reviewList) {
    	return reviewList.stream() // Stream<Review>
    			.filter(r -> product.getProductNumber() == r.getProductNumber()) // Steam<Review>
    			.toList() // List<Review>
    	;		
    }
    
    private double getAverageRating(List<Review> reviews) {
    	return reviews.stream() // Stream<Review>
    			.mapToDouble(Review::getRating) // DoubleSteam
    			.average() // OptionalDouble
    			.orElse(0);
    }


	@Override
    public void printProductDetailByNumber() {
      Product product = getProductByNumber();
      Seller seller = getSellerByProduct(product, sellerList);
      List<Review> reviews = getReviewsByProduct(product, reviewList);
      List<Inquiry> inquiries = getInquiriesByProduct(product, inquiryList);
      printProductDetail(product, seller, reviews, inquiries, buyerList);
    }
    
    private Product getProductByNumber() {
        long productNumber = 0;
        while (true) {
        	productNumber = Reader.readInt("상세 조회할 상품 번호를 입력하세요: ");
        	for (Product p : this.productList) {
        		if (p.getProductNumber() == productNumber) {
        			return p;
    			}
    		}
    		System.out.println("해당 번호의 상품이 존재하지 않습니다. 다시 입력해주세요.");
    	}
    }
    
    private List<Inquiry> getInquiriesByProduct(Product product, List<Inquiry> inquiryList) {
    	return inquiryList.stream() // Stream<Inquiry>
    			.filter(i -> product.getProductNumber() == i.getProductNumber()) // Stream<Inquiry>
    			.toList() //List<Inquiry>
    			;
    }
    
    private void printProductDetail(Product product, Seller seller, List<Review> reviews, List<Inquiry> inquiries, List<Buyer> buyerList) {
        System.out.println("상품 번호: " + product.getProductNumber());
        System.out.println("상품 명: " + product.getName());
        System.out.println("판매자 명: " + seller.getName());
        System.out.println("사업자 등록 번호: " + seller.getSid());	
        System.out.println("주소: " + seller.getAddress());	
        System.out.println("연락처: " + seller.getPhoneNumber());	
        System.out.println("누적 구매 횟수: " + product.getBuyCount());
        System.out.println("리뷰 수: " + reviews.size());
        System.out.println("별점 평균: " + getAverageRating(reviews));
        System.out.println("문의 수: " + inquiries.size());
        System.out.println("상품 상세 정보: " + product.getDescription());
        System.out.println("리뷰 목록: (" + reviews.size() + ")" + "=".repeat(10));
        printReviews(reviews, buyerList);
        System.out.println("문의 목록: (" + inquiries.size() + ")" + "=".repeat(10));
        printInquiries(inquiries, buyerList);
	}
    
    private void printReviews(List<Review> reviews, List<Buyer> buyerList) {
		for (Review r : reviews) {
			System.out.printf("고객 명: %s, 별점: %f\n", getBuyerNameByBuyerId(r.getBuyer(), buyerList), r.getRating());
			System.out.println("내용: " + r.getContent());
			System.out.println("-".repeat(30));
		}
	}
    
    private void printInquiries(List<Inquiry> inquiries, List<Buyer> buyerList) {
    	for (Inquiry i : inquiries) {
			System.out.println("고객 명: " + getBuyerNameByBuyerId(i.getBuyer(), buyerList));
			System.out.println("문의 제목: " + i.getTitle());
			System.out.println("문의 내용: " + i.getContent());
			System.out.println("-".repeat(30));
			if (i.getAnswer() == null) {
				System.out.println("아직 판매자의 답변이 없습니다.");
			} else {
				System.out.println("답변 시간: " + i.getAnswerDate());
				System.out.println("답변 내용: " + i.getAnswer());
			}
		}
    }
    
    private String getBuyerNameByBuyerId(String buyerId, List<Buyer> buyerList) {
    	return buyerList.stream() // Stream<Buyer>
    			.filter(b -> b.getUserId() == buyerId) // Stream<Buyer>
    			.findFirst() // Optional<Buyer>
    			.orElse(null) // Buyer
    			.getName() // String
    			;
    }
    

}
