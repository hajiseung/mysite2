package com.cafe24.mysite.vo;

import org.springframework.beans.factory.annotation.Autowired;

import com.cafe24.mysite.service.PageService;

public class PageVo {
	@Autowired
	private PageService pageService;

	private static final int COUNT_PER_BLOCK = 5;
	// 전체 게시글 갯수
	private int totalCount;

	// 전체 게시글/COUNT_PER_BLOCK한 페이지 갯수
	private int totalcountPage;

	// 현재 페이지에 보여질 게시글 갯수
	private int currentPageBoardCount;
	// 현재 페이지
	private int currentPage;

	// 이전페이지 (화살표)
	private int prevPage;

	// 다음 페이지 (화살표)
	private int postPage;

	// 목록의 첫번째 페이지
	private int listFirst;

	// 목록의 마지막 페이지
	private int listLast;

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
		this.totalcountPage = totalcountPage / PageVo.COUNT_PER_BLOCK;
		if ((totalcountPage % PageVo.COUNT_PER_BLOCK) > 0
				&& (totalcountPage % PageVo.COUNT_PER_BLOCK) < PageVo.COUNT_PER_BLOCK) {
			this.totalcountPage++;
		}
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

	public PageService getPageService() {
		return pageService;
	}

	public int getCurrentPageBoardCount() {
		return currentPageBoardCount;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public void setCurrentPageBoardCount(int currentPageBoardCount) {
		this.currentPageBoardCount = currentPageBoardCount;
	}

	public int getListFirst() {
		return listFirst;
	}

	public int getListLast() {
		return listLast;
	}

	public void setListFirst(int listFirst) {
		this.listFirst = listFirst;
	}

	public void setListLast(int listLast) {
		this.listLast = listLast;

	}

	@Override
	public String toString() {
		return "PageVo [pageService=" + pageService + ", totalCount=" + totalCount + ", totalcountPage="
				+ totalcountPage + ", currentPageBoardCount=" + currentPageBoardCount + ", currentPage=" + currentPage
				+ ", prevPage=" + prevPage + ", postPage=" + postPage + ", listFirst=" + listFirst + ", listLast="
				+ listLast + "]";
	}

}
