package kr.or.ddit.posting.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.posting.dao.IPostingDao;
import kr.or.ddit.posting.dao.PostingDaoImpl;
import kr.or.ddit.posting.model.PostingVO;

public class PostingServiceImpl implements IPostingService{
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	private IPostingDao postingDao;
	
	public PostingServiceImpl() {
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		postingDao = new PostingDaoImpl();
	}
	

	/**
	 * Method : getAllPosting
	 * 작성자 : lee
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 : 해당 게시판 전체 게시글 조회
	 */
	@Override
	public List<PostingVO> getAllPosting(Integer board_num) {
		sqlSession = sqlSessionFactory.openSession();
		List<PostingVO> postingList = postingDao.getAllPosting(sqlSession, board_num);
		sqlSession.close();
		
		return postingList;
	}

}