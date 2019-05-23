//package com.cafe24.mysite.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.cafe24.mysite.service.PageService;
//import com.cafe24.mysite.vo.PageVo;
//
//@Controller
//@RequestMapping("/count")
//public class PageController {
//	@Autowired
//	private PageService pageService;
//
////	전체 게시글 수 계산
//	@RequestMapping("/totalCount")
//	public void totalCount() {
//		PageVo pageVo = new PageVo();
//		
//		// 전체 게시글 수
//		int totalCount = pageService.TotalCount();
//
//		// 전체 페이지 수 계산
//		int totalCountPage = (totalCount / PageVo.getCountPerBlock());
//		int totalCountPageLeft = totalCountPage % PageVo.getCountPerBlock();
//		if (0 < totalCountPageLeft && totalCountPageLeft < PageVo.getCountPerBlock()) {
//			totalCountPage++;
//		}
//		
//		pageVo.setTotalcountPage(totalCountPage);
//		System.out.println("totalCount:"+totalCount);
//		System.out.println("totalCountPage:"+totalCountPage);
//		
//		
//	}
//}
