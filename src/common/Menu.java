package common;

/**
 * 메뉴...?
 */
public interface Menu <T, S> {
	
	String getDescription();
	
	void order(S service, T target);
}
