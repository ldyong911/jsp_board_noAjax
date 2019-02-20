package kr.or.ddit.reply.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;

@WebServlet("/replyUpdate")
public class ReplyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IReplyService replyService;
	
	@Override
	public void init() throws ServletException {
		replyService = new ReplyServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글번호를 파라미터로 받아서 댓글 조회
		String reply_num_str = request.getParameter("reply_num");
		Integer reply_num = Integer.parseInt(reply_num_str);
		ReplyVO replyVO = replyService.selectReply(reply_num);
		
		//댓글을 request 속성에 설정
		request.setAttribute("replyVO", replyVO);
		
		request.getRequestDispatcher("/reply/replyUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}