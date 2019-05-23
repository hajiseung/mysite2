package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PageDao {
	@Autowired
	private SqlSession sqlSession;

	public int TotalCount() {
		int tmp = sqlSession.selectOne("page.totalCount");
		System.out.println(tmp);
		return sqlSession.selectOne("page.totalCount");
	}
}
