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
import kr.or.ddit.user.model.UserVO;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IReplyService replyService;
       
	@Override
	public void init() throws ServletException {
		replyService = new ReplyServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//타입과 게시글번호, 게시글내용을 파라미터로 받고, session에서 userVO 객체를 가져옴
		String type = request.getParameter("type");
		String posting_num_str = request.getParameter("posting_num");
		Integer posting_num = Integer.parseInt(posting_num_str);
		String reply_content = request.getParameter("reply_content");
		UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
		
		int result = 0;
		//타입이 1이면 등록
		if(type.equals("1")){
			ReplyVO replyVO = new ReplyVO();
			replyVO.setReply_content(reply_content);
			replyVO.setPosting_num(posting_num);
			replyVO.setReply_userid(userVO.getUserId());
			replyVO.setReply_admin(userVO.getUserId());
			result = replyService.insertReply(replyVO);
		}
		//타입이 2이면 삭제
		else if(type.equals("2")){
			//댓글번호를 파라미터로 받아서 댓글 조회
			String reply_num_str = request.getParameter("reply_num");
			Integer reply_num = Integer.parseInt(reply_num_str);
			ReplyVO replyVO = replyService.selectReply(reply_num);
			
			replyVO.setDelete_exist("Y");
			result = replyService.updateReply(replyVO);
		}
		
		//result 결과가 성공이든 실패든 해당 게시글 상세화면으로 넘어감
		//정상입력(성공) - 해당 게시글 상세조회로 넘어감
		response.sendRedirect(request.getContextPath() + "/postingDetail?posting_num=" + posting_num);
	}

}