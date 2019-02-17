package kr.or.ddit.posting.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.posting.model.PostingVO;

public interface IPostingDao {
	/**
	 * Method : getAllPosting
	 * 작성자 : lee
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 전체 게시글 조회
	 */
	List<PostingVO> getAllPosting(SqlSession sqlSession, Integer board_num);
}