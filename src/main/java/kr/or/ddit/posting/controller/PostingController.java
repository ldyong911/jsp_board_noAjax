package kr.or.ddit.posting.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.posting.service.IPostingService;
import kr.or.ddit.posting.service.PostingServiceImpl;

@WebServlet("/posting")
public class PostingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IPostingService postingService;
	
	@Override
	public void init() throws ServletException {
		postingService = new PostingServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//해당 게시판 번호를 파라미터로 받기
		String board_num_str = request.getParameter("board_num");
		Integer board_num = Integer.parseInt(board_num_str);
		
		//해당 게시판 번호로 전체 게시글 계층형으로 조회
		List<PostingVO> postingList = postingService.selectHierar(board_num);
		
		//전체 게시글 리스트를 request 속성에 설정
		request.setAttribute("postingList", postingList);
		
		//해당 게시판번호를 requset 속성에 설정
		request.setAttribute("board_num", board_num);
		
		// posting/posting.jsp로 forward
		request.getRequestDispatcher("/posting/posting.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}