package kr.or.ddit.attach.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;

@WebServlet("/attachDelete")
public class AttachDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IAttachService attachService;
	
	@Override
	public void init() throws ServletException {
		attachService = new AttachServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String attach_num_str = request.getParameter("attach_num");
		Integer attach_num = Integer.parseInt(attach_num_str);
		
		attachService.deleteAttach(attach_num);
		
		request.getRequestDispatcher("/postingUpdate").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}