package serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import domain.Seller;
import service.SellerService;

public class SellerServiceImpl implements SellerService{
	private List<Seller> sellerList;


    public SellerServiceImpl(){
        sellerList = new ArrayList<>();
    }

    
    public boolean isEmpty(String str){
    	str = str.trim();
        Predicate<String> Value = (s) -> s == (null) || s.isBlank();
        boolean isEmpty = Value.test(str);
        if (isEmpty){
            System.out.println("이름을 입력해주세요 ");
            return true;
        }
        return false;
    }

    @Override
    public void addSeller() {
        Scanner scanner = new Scanner(System.in);

        String name = null;
        String BusinessNumber= null;
        String RepresentativeName= null;
        String RepresentativeNumber= null;
        String SellerAddress = null;
        String SellerPassWord = null;

        System.out.print("이름 : ");
        name = scanner.nextLine().trim();

        System.out.print("사업자 등록번호 : ");
        BusinessNumber = scanner.nextLine().trim();

        System.out.print("대표자 명 : ");
        RepresentativeName = scanner.nextLine().trim();

        System.out.print("대표자 연락처 : ");
        RepresentativeNumber = scanner.nextLine().trim();

        System.out.print("사업장 주소 : ");
        SellerAddress = scanner.nextLine().trim();

        System.out.print("판매자 비밀번호 : ");
        SellerPassWord = scanner.nextLine().trim();

        if(isEmpty(name) || isEmpty(BusinessNumber) || isEmpty(RepresentativeName) || isEmpty(RepresentativeNumber)
                || isEmpty(SellerAddress) || isEmpty(SellerPassWord)){
            return;
        }

        for (int i = 0; i < sellerList.size(); i++){

            if (sellerList.get(i).getUserId().equals(BusinessNumber)){

                System.out.println("중복된 사업자 등록번호입니다");
                return;
            }
        }


        this.sellerList.add(new Seller(name, BusinessNumber, RepresentativeName,
                            RepresentativeNumber, SellerAddress ,SellerPassWord));
        
    }

    @Override
    public void SellerLogin() {
        Scanner scanner = new Scanner(System.in);

        String name = null;

        boolean equals = false;

        int count = 0;


        while (count < 5){

            System.out.println("businessNumber : ");
            String businessNumber = scanner.nextLine();

            System.out.println("password : ");
            String password = scanner.nextLine();

            password = password.trim();
            businessNumber = businessNumber.trim();

            for (int i = 0; i < sellerList.size(); i++){

                if (sellerList.get(i).getUserId().equals(businessNumber)
                    && sellerList.get(i).getPassword().equals(password)){
                    equals = true;
                    name = sellerList.get(i).getName();
                }


            }


            if(equals){
                System.out.println(name + "님 환영합니다.");
                break;
            }else {
                count++;
            }

        }
        
    }
		
}
