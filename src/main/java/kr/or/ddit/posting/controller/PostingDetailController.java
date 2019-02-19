package kr.or.ddit.posting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.posting.service.IPostingService;
import kr.or.ddit.posting.service.PostingServiceImpl;

@WebServlet("/postingDetail")
public class PostingDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IPostingService postingService;
	
	@Override
	public void init() throws ServletException {
		postingService = new PostingServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//새글 등록한후 상세화면으로 넘어올때 posting_num을 파라미터로 받아서 해당 게시글 조회
		String posting_num_str = request.getParameter("posting_num");
		Integer posting_num = Integer.parseInt(posting_num_str);
		
		PostingVO postingVO = postingService.selectPosting(posting_num);
		
		//게시글을 request 속성에 설정
		request.setAttribute("postingVO", postingVO);
		
		request.getRequestDispatcher("/posting/postingDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}