package kr.or.ddit.attach.dao;

import kr.or.ddit.attach.model.AttachVO;

import org.apache.ibatis.session.SqlSession;

public class AttachDaoImpl implements IAttachDao{
	/**
	 * Method : insertAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	@Override
	public int insertAttach(SqlSession sqlSession, AttachVO attachVO) {
		int result = sqlSession.insert("attach.insertAttach", attachVO);
		return result;
	}

	/**
	 * Method : updateAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 수정
	 */
	@Override
	public int updateAttach(SqlSession sqlSession, AttachVO attachVO) {
		int result = sqlSession.update("attach.updateAttach", attachVO);
		return result;
	}

}