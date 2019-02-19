package kr.or.ddit.attach.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;

@WebServlet("/attach")
public class AttachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IAttachService attachService;
	
	@Override
	public void init() throws ServletException {
		attachService = new AttachServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//첨부파일 다운로드 받기위해선 설정해줘야함 - 필수!!!
		response.setHeader("Content-Disposition", "attachment; filename=profile.png");
		response.setContentType("application/octet-stream");
//		response.setContentType("image");
		
		//1.첨부파일번호 파라미터 확인
		String attach_num_str = request.getParameter("attach_num");
		Integer attach_num = Integer.parseInt(attach_num_str);
		
		//2.해당 첨부파일 번호로 첨부파일 정보 조회
		AttachVO attachVO = attachService.selectAttach(attach_num);
		
		//첨부파일 다운로드 받기위해서 해당파일이름으로 설정
		response.setHeader("Content-Disposition", "attachment; filename=" + attachVO.getFilename());
		
		//3-1.realFilename이 존재할 경우
		//3-1-1.해당 경로의 파일을 FileInputStream으로 읽는다
		FileInputStream fis;
		if(attachVO != null && attachVO.getRealfilename() != null){
			fis = new FileInputStream(new File(attachVO.getRealfilename()));
		}
		
		//3-2.realFilename이 존재하지 않을 경우
		//3-2-1. /upload/noimg.png (application.getRealPath())
		else{
			ServletContext application = getServletContext();
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		//4.FileInputStream을 response객체의 outputStream 객체에 write
		ServletOutputStream sos = response.getOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fis.read(buff)) > -1){
			sos.write(buff);
		}
		
		sos.close();
		fis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}