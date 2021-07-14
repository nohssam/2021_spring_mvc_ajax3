package com.ict.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.vo.VO;

@Repository("myDAOImpl")
public class MyDAOImpl implements MyDAO{
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<VO> selectAll() throws Exception {
		return sqlSessionTemplate.selectList("guestbook.list");
	}
	@Override
	public VO selectOne(String idx) throws Exception {
		return sqlSessionTemplate.selectOne("guestbook.onelist", idx);
	}
	
	@Override
	public int insert(VO vo) throws Exception {
		return sqlSessionTemplate.insert("guestbook.insert", vo);
	}
	
	@Override
	public int deleteOne(String idx) throws Exception {
		return sqlSessionTemplate.delete("guestbook.delete",idx);
	}
	
	@Override
	public int updateOne(VO vo) throws Exception {
		return sqlSessionTemplate.update("guestbook.update", vo);
	}
}
