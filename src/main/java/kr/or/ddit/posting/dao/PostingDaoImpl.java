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

	/**
	 * Method : selectHierar
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 해당 게시판 전체 게시글 계층형으로 조회
	 */
	@Override
	public List<PostingVO> selectHierar(SqlSession sqlSession, Integer board_num) {
		List<PostingVO> postingList = sqlSession.selectList("posting.selectHierar", board_num);
		return postingList;
	}

	/**
	 * Method : insertPosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 등록
	 */
	@Override
	public int insertPosting(SqlSession sqlSession, PostingVO postingVO) {
		int result = sqlSession.insert("posting.insertPosting", postingVO);
		return result;
	}

	/**
	 * Method : updatePosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	@Override
	public int updatePosting(SqlSession sqlSession, PostingVO postingVO) {
		int result = sqlSession.update("posting.updatePosting", postingVO);
		return result;
	}
	
}