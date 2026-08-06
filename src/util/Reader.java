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
		return reader.nextLine();
	}
	
	public static String readString(String prompt) {
		System.out.print(prompt);
		return readString();
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
}
