package serviceimpl;

import domain.Product;
import domain.Seller;
import service.ProductService;
import util.Reader;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private List<Product> productList;
    private long productSeq = 1000L; // 상품번호 생성

    public ProductServiceImpl() {
        this.productList = new ArrayList<>();
    }

    @Override
    public void manageProducts(Seller seller) {
        if (seller == null) {
            System.out.println("로그인이 필요한 기능입니다.");
            return;
        }

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
    }

    // 1. 상품 정보 등록(입고)
    private void addProduct(Seller seller) {
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

    // 2. 상품 재 입고(품절 후 재 입고)
    private void restockProduct(Seller seller) {
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

    // 3. 상품 정보 삭제 (판매 중단 처리)
    private void deleteProduct(Seller seller) {
        System.out.println("\n--- 상품 삭제 ---");
        long productNum = Reader.readInt("삭제할 상품 번호 : ");
        Product product = findMyProduct(seller, productNum);

        if (product == null) return;

        product.setActive(false);

        System.out.println("상품 번호 " + productNum + " 번 상품이 삭제(판매 중단)되었습니다.");
    }

    // 4. 상품 정보 수정 (상품명, 가격, 상품 설명)
    private void updateProduct(Seller seller) {
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
    private void printMyProducts(Seller seller) {
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

}
