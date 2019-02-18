package kr.or.ddit.attach.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.attach.dao.AttachDaoImpl;
import kr.or.ddit.attach.dao.IAttachDao;
import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

public class AttachServiceImpl implements IAttachService{
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	private IAttachDao attachDao;
	
	public AttachServiceImpl() {
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		attachDao = new AttachDaoImpl();
	}

	/**
	 * Method : insertAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	@Override
	public int insertAttach(AttachVO attachVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = attachDao.insertAttach(sqlSession, attachVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : updateAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 수정
	 */
	@Override
	public int updateAttach(AttachVO attachVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = attachDao.updateAttach(sqlSession, attachVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}
	
}