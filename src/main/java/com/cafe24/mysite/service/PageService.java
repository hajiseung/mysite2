package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.PageDao;

@Service
public class PageService {
	
	@Autowired
	private PageDao pageDao;
	
	//전체 게시물 수 구현
	public int TotalCount() {
		return pageDao.TotalCount();
		
	}
}
