package kr.or.ddit.posting.dao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.posting.model.PostingVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

public class PostingDaoImplTest {
	private SqlSession sqlSession;
	private IPostingDao postingDao;
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		postingDao = new PostingDaoImpl();
	}
	
	@Test
	public void testGetAllPosting() {
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		List<PostingVO> postingList = postingDao.getAllPosting(sqlSession, board_num);
		
		/***Then***/
		assertEquals(1, postingList.size());
	}
	
}