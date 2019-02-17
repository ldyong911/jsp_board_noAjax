package kr.or.ddit.posting.service;

import java.util.List;

import kr.or.ddit.posting.model.PostingVO;

public interface IPostingService {

	/**
	 * Method : getAllPosting
	 * 작성자 : lee
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 : 해당 게시판 전체 게시글 조회
	 */
	List<PostingVO> getAllPosting(Integer board_num);
}