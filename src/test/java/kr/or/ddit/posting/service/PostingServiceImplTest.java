package kr.or.ddit.posting.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.posting.model.PostingVO;

import org.junit.Before;
import org.junit.Test;

public class PostingServiceImplTest {
	private IPostingService postingService;
	
	@Before
	public void setup(){
		postingService = new PostingServiceImpl();
	}
	
	@Test
	public void testGetAllPosting() {
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		List<PostingVO> postingList = postingService.getAllPosting(board_num);
		
		/***Then***/
		assertEquals(1, postingList.size());
	}
	
	@Test
	public void testSelectHierar() {
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		List<PostingVO> postingList = postingService.selectHierar(board_num);
		
		/***Then***/
		assertEquals(2, postingList.size());
	}
	
	@Test
	public void testInsertPosting(){
		/***Given***/
		PostingVO postingVO = new PostingVO();
		postingVO.setBoard_num(1);
		postingVO.setPosting_title("테스트1");
		postingVO.setPosting_content("테스트1");
		postingVO.setPosting_userid("brown");
		
		/***When***/
		int result = postingService.insertPosting(postingVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdatePosting(){
		/***Given***/
		PostingVO postingVO = new PostingVO();
		postingVO.setPosting_num(3);
		postingVO.setPosting_title("테스트2");
		postingVO.setPosting_content("테스트2");
		postingVO.setDelete_exist("N");
		
		/***When***/
		int result = postingService.updatePosting(postingVO);
		
		/***Then***/
		assertEquals(1, result);
	}

}