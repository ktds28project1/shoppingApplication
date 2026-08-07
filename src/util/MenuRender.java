package util;

import common.Menu;

public final class MenuRender {
	
	private MenuRender() {
	}
	
	public static <T, S, E extends Enum<E> & Menu<T,S>> void render(String title, String cancel, E[] menus
																, S service, T target) {
		int cancelMenu = menus.length + 1;
		int answer = -1;
		
		while(true) {
			System.out.println(title);
			
			for (E menu : menus) {
				System.out.printf("%d. %s%n", (menu.ordinal() + 1), menu.getDescription());
			}
			System.out.printf("%d. %s%n", cancelMenu, cancel);
			
			answer = Reader.readInt("번호를 선택: ", 1, cancelMenu);
			
			if (answer == cancelMenu) {
				break;
			} else if (answer >= 1 && answer < cancelMenu) {
				menus[answer-1].order(service, target);
			} else {
				System.out.println("다시 입력하세요.");
			}
		}
	}
}
