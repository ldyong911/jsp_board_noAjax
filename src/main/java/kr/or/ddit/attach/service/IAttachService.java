package kr.or.ddit.attach.service;

import kr.or.ddit.attach.model.AttachVO;

public interface IAttachService {
	/**
	 * Method : insertAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	int insertAttach(AttachVO attachVO);
	
	/**
	 * Method : updateAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 수정
	 */
	int updateAttach(AttachVO attachVO);
}