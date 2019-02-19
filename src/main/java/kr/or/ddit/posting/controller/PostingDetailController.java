package kr.or.ddit.posting.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.posting.service.IPostingService;
import kr.or.ddit.posting.service.PostingServiceImpl;

@WebServlet("/postingDetail")
public class PostingDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IPostingService postingService;
	private IAttachService attachService;
	
	@Override
	public void init() throws ServletException {
		postingService = new PostingServiceImpl();
		attachService = new AttachServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//새글 등록한후 상세화면으로 넘어올때 posting_num을 파라미터로 받아서 해당 게시글 조회
		String posting_num_str = request.getParameter("posting_num");
		Integer posting_num = Integer.parseInt(posting_num_str);
		
		//해당 게시글 조회한후 request 속성에 설정
		PostingVO postingVO = postingService.selectPosting(posting_num);
		request.setAttribute("postingVO", postingVO);
		
		//해당 게시글의 첨부파일 조회한후 있으면 속성에 설정
		List<AttachVO> attachList = attachService.selectAttachList(posting_num);
		if(attachList != null){
			request.setAttribute("attachList", attachList);
		}
		
		request.getRequestDispatcher("/posting/postingDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}