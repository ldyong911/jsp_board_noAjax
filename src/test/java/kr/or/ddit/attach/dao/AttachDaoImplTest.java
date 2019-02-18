package kr.or.ddit.attach.dao;

import static org.junit.Assert.assertEquals;
import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

public class AttachDaoImplTest {
	private SqlSession sqlSession;
	private IAttachDao attachDao;
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		attachDao = new AttachDaoImpl();
	}
	
	@Test
	public void testInsertAttach() {
		/***Given***/
		AttachVO attachVO = new AttachVO();
		attachVO.setFilename("brown.png");
		attachVO.setRealfilename("D:\\attach\\brown.png");
		attachVO.setPosting_num(3);
		
		/***When***/
		int result = attachDao.insertAttach(sqlSession, attachVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdateAttach() {
		/***Given***/
		AttachVO attachVO = new AttachVO();
		attachVO.setFilename("cony.png");
		attachVO.setRealfilename("D:\\attach\\brown.png");
		attachVO.setAttach_num(1);
		
		/***When***/
		int result = attachDao.updateAttach(sqlSession, attachVO);
		
		/***Then***/
		assertEquals(1, result);
	}

}
