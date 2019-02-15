package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IBoardService boardService;
	
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시판 전체 조회
		List<BoardVO> boardList = boardService.getAllBoard();
		
		//전체게시판리스트 속성 설정
		request.setAttribute("boardList", boardList);
		
		// localhost/board 요청시 /board/board.jsp로 보냄
		request.getRequestDispatcher("/board/board.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String type = request.getParameter("type");
		
		int result = 0;
		//type이 1이면 등록
		if(type.equals("1")){
			String board_name = request.getParameter("board_name_ins");
			String use_exist = request.getParameter("use_exist_ins");
			
			BoardVO boardVO = new BoardVO();
			boardVO.setBoard_name(board_name);
			boardVO.setUse_exist(use_exist);
			result = boardService.insertBoard(boardVO);
		}
		//type이 2이면 수정
		else{
			String board_num_str = request.getParameter("board_num_upd");
			Integer board_num = board_num_str == null ? null : Integer.parseInt(board_num_str);
			String board_name = request.getParameter("board_name_upd");
			String use_exist = request.getParameter("use_exist_upd");
			
			BoardVO boardVO = boardService.selectBoard(board_num);
			boardVO.setBoard_name(board_name);
			boardVO.setUse_exist(use_exist);
			result = boardService.updateBoard(boardVO);
		}
		
		if(result == 1){
			response.sendRedirect(request.getContextPath() + "/board");
		}else{
			doGet(request, response);
		}
	}

}