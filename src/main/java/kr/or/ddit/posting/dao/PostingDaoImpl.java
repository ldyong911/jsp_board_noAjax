package kr.or.ddit.posting.dao;

import java.util.List;

import kr.or.ddit.posting.model.PostingVO;

import org.apache.ibatis.session.SqlSession;

public class PostingDaoImpl implements IPostingDao{
	/**
	 * Method : getAllPosting
	 * 작성자 : lee
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 전체 게시글 조회
	 */
	@Override
	public List<PostingVO> getAllPosting(SqlSession sqlSession, Integer board_num) {
		List<PostingVO> postingList = sqlSession.selectList("posting.getAllPosting", board_num);
		return postingList;
	}
	
}