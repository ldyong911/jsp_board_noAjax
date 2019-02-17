package kr.or.ddit.posting.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.posting.model.PostingVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

public class PostingServiceImplTest {
	private SqlSession sqlSession;
	private IPostingService postingService;
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		postingService = new PostingServiceImpl();
	}
	
	@Test
	public void testGetAllPosting() {
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		List<PostingVO> postingList = postingService.getAllPosting(board_num);
		
		/***Then***/
		assertEquals(1, postingList.size());

	}

}