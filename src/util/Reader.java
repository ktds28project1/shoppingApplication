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
	
	public static int readInt() {
		while(true) {
			try {
				return Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력");
			}
		}
	}
	
	public static String readString(String prompt) {
		System.out.print(prompt);
		return readString();
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
}
