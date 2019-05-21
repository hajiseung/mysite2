package com.cafe24.mysite.vo;

public class PageVo {
	private static final int COUNT_PER_BLOCK = 5;
	// 전체 게시글 갯수
	private int totalCount;

	// 전체 게시글/COUNT_PER_BLOCK한 페이지 갯수
	private int totalcountPage;

	// 현재 페이지
	private int currentPage;

	// 이전페이지 (화살표)
	private int prevPage;

	// 다음 페이지 (화살표)
	private int postPage;

	public static int getCountPerBlock() {
		return COUNT_PER_BLOCK;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalcountPage() {
		return totalcountPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public int getPostPage() {
		return postPage;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setTotalcountPage(int totalcountPage) {
		this.totalcountPage = totalcountPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public void setPostPage(int postPage) {
		this.postPage = postPage;
	}

	@Override
	public String toString() {
		return "PageVo [totalCount=" + totalCount + ", totalcountPage=" + totalcountPage + ", currentPage="
				+ currentPage + ", prevPage=" + prevPage + ", postPage=" + postPage + "]";
	}

}
