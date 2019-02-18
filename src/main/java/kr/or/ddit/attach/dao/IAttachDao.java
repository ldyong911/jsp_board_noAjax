package kr.or.ddit.attach.dao;

import kr.or.ddit.attach.model.AttachVO;

import org.apache.ibatis.session.SqlSession;

public interface IAttachDao {
	/**
	 * Method : insertAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	int insertAttach(SqlSession sqlSession, AttachVO attachVO);
	
	/**
	 * Method : updateAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 수정
	 */
	int updateAttach(SqlSession sqlSession, AttachVO attachVO);
}