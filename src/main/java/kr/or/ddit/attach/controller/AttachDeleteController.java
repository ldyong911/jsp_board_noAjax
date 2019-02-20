package kr.or.ddit.attach.controller;

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

@WebServlet("/attachDelete")
public class AttachDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IAttachService attachService;
	private IPostingService postingService;
	
	@Override
	public void init() throws ServletException {
		attachService = new AttachServiceImpl();
		postingService = new PostingServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 번호와 첨부파일번호를 파라미터로 받음
		String posting_num_str = request.getParameter("posting_num");
		Integer posting_num = Integer.parseInt(posting_num_str);
		String attach_num_str = request.getParameter("attach_num");
		Integer attach_num = Integer.parseInt(attach_num_str);
		
		//기존의 첨부파일 삭제
		attachService.deleteAttach(attach_num);
		
		//해당 게시글의 첨부파일 조회
		List<AttachVO> attachList = attachService.selectAttachList(posting_num);
		
		//파라미터로 받은 게시글 번호로 해당 게시글 조회
		PostingVO postingVO = postingService.selectPosting(posting_num);
		
		//수정시 request 속성에 설정한후 postingUpdate.jsp로 forward
		request.setAttribute("postingVO", postingVO);
		request.setAttribute("attachList", attachList);
		request.getRequestDispatcher("/posting/postingUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}