package com.cafe24.mysite.vo;

public class BoardVo {
	private long no;
	private String title;
	private String content;
	private String regDate;
	private long userNo;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", regDate=" + regDate + ", userNo="
				+ userNo + "]";
	}

}
