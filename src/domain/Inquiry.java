package domain;

import java.time.LocalDateTime;

/**
 * 문의
 */
public class Inquiry {
	
	/** 문의 번호 */
	private final long inquiryNumber;
	
	/** 제품 번호 */
	private final long productNumber;
	
	/** 문의자 아이디 */
	private final String buyer;
	
	/** 문의 제목 */
	private final String title;
	
	/** 문의 내용 */
	private final String content;
	
	/** 문의 시간 */
	private final LocalDateTime inquiryDate;
	
	/** 문의 답변 */
	private String answer;
	
	/** 답변 시간 */
	private LocalDateTime answerDate;
	
	public Inquiry(long inquiryNumber, long productNubmer, String buyer, String title, String content) {
		this.inquiryNumber = inquiryNumber;
		this.productNumber = productNubmer;
		this.buyer = buyer;
		this.title = title;
		this.content = content;
		this.inquiryDate = LocalDateTime.now();
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
		this.answerDate = LocalDateTime.now();
	}

	public LocalDateTime getAnswerDate() {
		return this.answerDate;
	}

	public long getInquiryNumber() {
		return this.inquiryNumber;
	}

	public long getProductNumber() {
		return this.productNumber;
	}

	public String getBuyer() {
		return this.buyer;
	}

	public String getTitle() {
		return this.title;
	}

	public String getContent() {
		return this.content;
	}

	public LocalDateTime getInquiryDate() {
		return this.inquiryDate;
	}
	@Override
	public String toString() {
		return "문의 번호: " + this.getInquiryNumber() + "\n문의 제목: " + this.getTitle() + "\n문의자 이름: " 
							+ this.getBuyer() + "\n문의 등록 날짜: " + this.getInquiryDate() + "\n문의 내용" + this.getContent()
							+ "\n답변 내용: " + this.getAnswer(); 
	}
}
