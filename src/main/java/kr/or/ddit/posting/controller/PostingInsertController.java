package kr.or.ddit.posting.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.posting.service.IPostingService;
import kr.or.ddit.posting.service.PostingServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/postingInsert")
@MultipartConfig(maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class PostingInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(PostingInsertController.class);
	
	private IPostingService postingService;
	private IAttachService attachService;
    
	@Override
	public void init() throws ServletException {
		postingService = new PostingServiceImpl();
		attachService = new AttachServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_num = request.getParameter("board_num");
		
		request.setAttribute("board_num", board_num);
		
		System.out.println(request.getAttribute("board_num"));
		
		//게시글 등록페이지로 forward
		request.getRequestDispatcher("/posting/postingInsert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//새글인지 답글인지 타입으로 판별하여 부모글 번호 세팅
		String type = request.getParameter("type");
		Integer parentposting_num;
		
		//타입이 1이면 새글등록
		if(type.equals("1")){
			parentposting_num = null;
		}
		//타입이 2이면 답글등록
		else{
			parentposting_num = Integer.parseInt(request.getParameter("parentposting_num"));
		}
		
		//게시글 세팅
		String board_num_str = request.getParameter("board_num");
		Integer board_num = Integer.parseInt(board_num_str);
		String posting_title = request.getParameter("posting_title");
		String posting_content = request.getParameter("posting_content");
		UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
		String posting_userid = userVO.getUserId();
		PostingVO postingVO = new PostingVO(board_num, posting_title, posting_content, posting_userid, parentposting_num);
		
		int result1 = postingService.insertPosting(postingVO);
		int result2 = 0;
		
		//파일이름과 저장경로 초기화
		String filename = "";
		String realFilename = "";
		
		//첨부파일
		Collection<Part> parts = request.getParts();
		
		for(Part part : parts){
			if(part.getName().equals("attach")){
				logger.debug("Content-Disposition : {}", part.getHeader("Content-Disposition"));
				
				//첨부파일을 등록한 경우
				if(part.getSize() > 0){
					String contentDisposition = part.getHeader("Content-Disposition");
					filename = PartUtil.getFileNameFromPart(contentDisposition);
					realFilename = "d:\\attach\\" + UUID.randomUUID().toString();
					
					//디스크에 기록(d:\attachfile\ + realFileName)
					part.write(realFilename);
					part.delete();
				}
				
				//파일명, uuid(저장경로) 세팅
				AttachVO attachVO = new AttachVO();
				attachVO.setFilename(filename);
				attachVO.setRealfilename(realFilename);
				attachVO.setPosting_num(postingVO.getPosting_num()); //insert된 시퀀스 값가져와야함 
				
				result2 += attachService.insertAttach(attachVO);
				
				
				//첨부파일을 올리지 않은 경우 filename, realFilename 모두 공백(위에서 초기화한값)
			}
		}
		
		logger.debug("attachResult : {}", result2);
		
		//정상 입력(성공)
		if(result1 == 1){
			//db에서 데이터를 조작하는 로직을 처리할때는 forward가 아니라 redirect를 사용해야함(새로고침시 최초요청 url로 다시 이동하기때문에)
			//redirect는 ContextPath를 써줘야하며 redirect는 get방식임
			//request.getRequestDispatcher("/userPagingList").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/postingDetail");
		}
		//정상 입력(실패)
		else{
			doGet(request, response);
		}
	}

}