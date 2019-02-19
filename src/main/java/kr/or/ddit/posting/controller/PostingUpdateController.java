package kr.or.ddit.posting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.posting.service.IPostingService;
import kr.or.ddit.posting.service.PostingServiceImpl;
import kr.or.ddit.user.model.UserVO;

@WebServlet("/postingUpdate")
@MultipartConfig(maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
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
			//수정시 request 속성에 설정한후 postingUpdate.jsp로 forward
			request.setAttribute("postingVO", postingVO);
			request.getRequestDispatcher("/posting/postingUpdate.jsp").forward(request, response);
		}
		//타입이 2이면 삭제
		else if(type.equals("2")){
			PostingVO posting = new PostingVO();
			posting.setPosting_title(postingVO.getPosting_title());
			posting.setPosting_content(postingVO.getPosting_content());
			posting.setDelete_exist("Y");
			posting.setPosting_num(postingVO.getPosting_num());
			
			result = postingService.updatePosting(posting);
			
			//정상입력(성공) - 리다이렉트는 get방식으로 게시판번호를 넘겨줘야함
			if(result == 1){
				response.sendRedirect(request.getContextPath() + "/posting?board_num=" + postingVO.getBoard_num());
			}
			//실패 - 다시 상세조회를 가야하므로 게시글번호를 넘겨줘야함
			else{
				request.getRequestDispatcher("/posting/postingDetail?posting_num="+ posting_num).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String posting_num_str = request.getParameter("posting_num");
		Integer posting_num = Integer.parseInt(posting_num_str);
		String posting_title = request.getParameter("posting_title");
		String posting_content = request.getParameter("posting_content");
		
		//해당 게시글번호로 게시글 조회
		PostingVO postingVO = postingService.selectPosting(posting_num);
		
		//게시글 세팅
		postingVO.setPosting_title(posting_title);
		postingVO.setPosting_content(posting_content);
		
		int result = postingService.updatePosting(postingVO);
		
		//정상입력(성공) - 해당 게시글 상세조회로 넘어감
		if(result == 1){
			response.sendRedirect(request.getContextPath() + "/postingDetail?posting_num=" + postingVO.getPosting_num());
		}
		//실패 - 원래 화면을 다시 보여줌
		else{
			request.setAttribute("postingVO", postingVO);
			request.getRequestDispatcher("/posting/postingUpdate.jsp").forward(request, response);
		}
	}

}