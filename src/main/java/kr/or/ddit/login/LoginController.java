package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.util.KISA_SHA256;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IUserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	//로그 출력 변수
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// localhost/login_ 요청시 /login/login.jsp로 보냄
		request.getRequestDispatcher("/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		logger.debug("userId : {}", userId);
		logger.debug("pass : {}", pass);
		
		//디비에 사용자가 존재하는지 조회
		UserVO userVO = userService.selectUser(userId);
		logger.debug("userVO : {}", userVO);
		
		//db정보와 사용자 파라미터 아이디와 패스워드가 일치하는경우 -> main.jsp
		if(userVO != null && userVO.getUserId().equals(userId) && userVO.getPass().equals(KISA_SHA256.encrypt(pass))){
			//사용자 정보를 session에 저장한다
			//userVO 객체는 session이 유지될 동안 다른 페이지에서 사용
			HttpSession session = request.getSession();
			session.setAttribute("userVO", userVO);
			
			// localhost/main.jsp으로 보냄
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		//db정보와 사용자 파라미터 아이디와 패스워드가 일치하지않는경우 -> login.jsp
		else{
			doGet(request, response);
		}
	}

}