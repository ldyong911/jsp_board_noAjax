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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.posting.service.IPostingService;
import kr.or.ddit.posting.service.PostingServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

@WebServlet("/postingUpdate")
@MultipartConfig(maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class PostingUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(PostingUpdateController.class);
       
	private IPostingService postingService;
	private IAttachService attachService;
	
	@Override
	public void init() throws ServletException {
		postingService = new PostingServiceImpl();
		attachService = new AttachServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//타입과 게시글 번호를 파라미터로 받음
		String type = request.getParameter("type");
		String posting_num_str = request.getParameter("posting_num");
		Integer posting_num = Integer.parseInt(posting_num_str);
		
		//파라미터로 받은 게시글 번호로 해당 게시글 조회
		PostingVO postingVO = postingService.selectPosting(posting_num);
		
		//해당 게시글의 첨부파일 조회
		List<AttachVO> attachList = attachService.selectAttachList(posting_num);
		
		int result = 0;
		//타입이 1이면 수정
		if(type.equals("1")){
			//수정시 request 속성에 설정한후 postingUpdate.jsp로 forward
			request.setAttribute("postingVO", postingVO);
			request.setAttribute("attachList", attachList);
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
		int result2 = 0;
		
		//기존의 첨부파일 삭제
		attachService.deleteAttach(posting_num);
		
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
					
					//파일명, uuid(저장경로) 세팅
					AttachVO attachVO = new AttachVO();
					attachVO.setFilename(filename);
					attachVO.setRealfilename(realFilename);
					attachVO.setPosting_num(postingVO.getPosting_num()); //insert된 시퀀스 값가져와야함 
					
					result2 += attachService.insertAttach(attachVO);
				}
				
				//첨부파일을 올리지 않은 경우 filename, realFilename 모두 공백(위에서 초기화한값)
			}
		}
		
		logger.debug("attachResult : {}", result2);
		
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