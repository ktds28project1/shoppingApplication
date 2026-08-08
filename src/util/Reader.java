package util;

import java.util.Scanner;

public final class Reader {

	private static final Scanner reader;
	
	static {
		reader = new Scanner(System.in);
	}
	
	private Reader() {
	}
	
	public static String readString() {
		return reader.nextLine().trim();
	}
	
	public static String readString(String prompt) {
		System.out.print(prompt);
		return readString();
	}
	
	public static String validateInput(String message) {
		String input = null;
		while (true) {
			input = readString(message).trim();
			if (input.isBlank()) {
				System.out.println(message + "가 입력되지 않았습니다. \n다시 시도해주십시오.");
				continue;
			}
			return input;
		}
	}
	
	public static int readInt() {
		return readInt("");
	}
	
	public static int readInt(String prompt) {
		while(true) {
			try {
				return Integer.parseInt(readString(prompt));
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력");
			}
		}
	}
	
	public static int readInt(String prompt, int min, int max) {
		
		if (min > max) {
			throw new IllegalArgumentException("최소값 최대값 오류");
		}
		
		while(true) {
			int answer = readInt(prompt);
			
			if (answer < min || answer > max) {
				System.out.println("%d ~ %d 사이로 입력하세요.".formatted(min,max));
			} else {
				return answer;
			}
		}
	}
	
	public static String readPhoneNumber(String prompt) {
		String phoneNumber = null;
		while(true) {
			phoneNumber = readString(prompt);
			if(phoneNumber.matches("^01\\d{1}-?\\d{3,4}-?\\d{4}$")) {
				return phoneNumber;
			}
			System.out.println("전화번호 형식에 맞지않습니다.");
			System.out.println("000-0000-0000");
			continue;
		}
	}
}
