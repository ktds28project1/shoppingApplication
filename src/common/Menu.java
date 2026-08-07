package common;

/**
 * 메뉴...?
 */
public interface Menu <T> {
	
	String getDescription();
	
	void order(ShoppingData data, T target);
}
