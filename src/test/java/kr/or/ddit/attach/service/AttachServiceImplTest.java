package kr.or.ddit.attach.service;

import static org.junit.Assert.assertEquals;
import kr.or.ddit.attach.model.AttachVO;

import org.junit.Before;
import org.junit.Test;

public class AttachServiceImplTest {
	private IAttachService attachService;
	
	@Before
	public void setup(){
		attachService = new AttachServiceImpl();
	}
	
	@Test
	public void testInsertAttach() {
		/***Given***/
		AttachVO attachVO = new AttachVO();
		attachVO.setFilename("brown.png");
		attachVO.setRealfilename("D:\\attach\\brown.png");
		attachVO.setPosting_num(3);
		
		/***When***/
		int result = attachService.insertAttach(attachVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdateAttach() {
		/***Given***/
		AttachVO attachVO = new AttachVO();
		attachVO.setFilename("cony.png");
		attachVO.setRealfilename("D:\\attach\\brown.png");
		attachVO.setAttach_num(1);
		
		/***When***/
		int result = attachService.updateAttach(attachVO);
		
		/***Then***/
		assertEquals(1, result);
	}

}
