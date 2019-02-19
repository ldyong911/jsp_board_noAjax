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

@WebServlet("/postingUpdate")
public class PostingUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IPostingService postingService;
	
	@Override
	public void init() throws ServletException {
		postingService = new PostingServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String posting_num_str = request.getParameter("posting_num");
		Integer posting_num = Integer.parseInt(posting_num_str);
		
		PostingVO postingVO = postingService.selectPosting(posting_num);
		
		int result = 0;
		//타입이 1이면 수정
		if(type.equals("1")){
			
		}
		//타입이 2이면 삭제
		else if(type.equals("2")){
			PostingVO posting = new PostingVO();
			posting.setPosting_title(postingVO.getPosting_title());
			posting.setPosting_content(postingVO.getPosting_content());
			posting.setDelete_exist("Y");
			posting.setPosting_num(postingVO.getPosting_num());
			
			result = postingService.updatePosting(posting);
			
			//정상입력(성공)
			if(result == 1){
				response.sendRedirect(request.getContextPath() + "/posting?board_num=" + postingVO.getBoard_num());
			}
			//실패
			else{
				request.getRequestDispatcher("/posting/postingDetail?posting_num="+ posting_num).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}