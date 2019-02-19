package kr.or.ddit.posting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.posting.service.IPostingService;
import kr.or.ddit.posting.service.PostingServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

@WebServlet("/posting")
public class PostingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(PostingController.class);
	
	private IPostingService postingService;
	
	@Override
	public void init() throws ServletException {
		postingService = new PostingServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//해당 게시판 번호를 파라미터로 받기
		String board_num_str = request.getParameter("board_num");
		Integer board_num = Integer.parseInt(board_num_str);

		//해당 게시판번호를 requset 속성에 설정
		request.setAttribute("board_num", board_num);
		
		//계층형 게시글을 조회한후 posting_level을 update해줘야함(페이징처리된 계층형 쿼리를 조회하려면 필수)
		//검색한 level로 posting_level을 세팅
		List<PostingVO> postingHierar = postingService.selectHierar(board_num);
		
		int resultUpdate = 0;
		for(PostingVO postingVO : postingHierar){
			String posting_level = postingVO.getPosting_level();
			
			postingVO.setPosting_level(posting_level);
			
			resultUpdate += postingService.updateLevel(postingVO);
		}
		logger.debug("resultUpdate : {}", resultUpdate);
		
		//page, pageSize에 해당하는 파라미터 받기 ==> pageVO
		//단, 파라미터가 없을경우 page : 1, pageSize : 10
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize");
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pageSize = pageSizeStr == null ? 10 : Integer.parseInt(pageSizeStr);
		PageVO pageVO = new PageVO(page, pageSize);
		
		//postingService 객체를 이용 postingPaging 조회
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_num", board_num);
		map.put("page", pageVO.getPage());
		map.put("pageSize", pageVO.getPageSize());
		
		//해당 게시판에 해당하는 게시글을 계층형으로 페이징 조회
		Map<String, Object> resultMap = postingService.selectPostingPaging(map, board_num);
		
		//게시글 리스트
		List<PostingVO> postingList = (List<PostingVO>) resultMap.get("postingList");
		//게시글 수
		int postingCnt = (int)resultMap.get("postingCnt");
		//마지막페이지 구하기
		int lastPage = postingCnt == 0 ? 1 : (int)Math.ceil((postingCnt*1.0)/pageSize);
		
		//request객체에 조회된 결과를 속성으로 설정
		request.setAttribute("postingList", postingList);
		request.setAttribute("postingCnt", postingCnt);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("page", page);
		request.setAttribute("lastPage", lastPage);
		
		// posting/posting.jsp로 forward
		request.getRequestDispatcher("/posting/posting.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}