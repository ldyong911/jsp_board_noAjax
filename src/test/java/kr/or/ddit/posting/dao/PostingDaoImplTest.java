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
	
	@Test
	public void testSelectHierar() {
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		List<PostingVO> postingList = postingDao.selectHierar(sqlSession, board_num);
		
		/***Then***/
		assertEquals(2, postingList.size());
	}
	
	@Test
	public void testInsertPosting(){
		/***Given***/
		PostingVO postingVO = new PostingVO();
		postingVO.setBoard_num(1);
		postingVO.setPosting_title("테스트1");
		postingVO.setPosting_content("테스트1");
		postingVO.setPosting_userid("brown");
		
		/***When***/
		int result = postingDao.insertPosting(sqlSession, postingVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdatePosting(){
		/***Given***/
		PostingVO postingVO = new PostingVO();
		postingVO.setPosting_num(3);
		postingVO.setPosting_title("테스트2");
		postingVO.setPosting_content("테스트2");
		postingVO.setDelete_exist("N");
		
		/***When***/
		int result = postingDao.updatePosting(sqlSession, postingVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
}