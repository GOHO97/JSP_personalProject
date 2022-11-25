package com.ho.mgz.nftGalleryMain;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ho.firstWebProjec.home.JoinDAO;
import com.ho.token.generator.hoTokenGenerator;

@WebServlet("/CorrectController")
public class CorrectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CorrectController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(JoinDAO.getJoindao().loginCheck(request)) {
			hoTokenGenerator.generate(request);
			NftGalleryDAO.getNftgallerydao().getReWriteInfo(request);
			NftGalleryDAO.getNftgallerydao().selectGallery(request);
			NftGalleryDAO.getNftgallerydao().showGallery(1, request);
			request.setAttribute("contentPage", "nftGallery/main.jsp");
		} else {
			request.setAttribute("result", "수정은 로그인 후 이용해주세요");
			request.setAttribute("contentPage", "home/home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(JoinDAO.getJoindao().loginCheck(request)) {
			NftGalleryDAO.getNftgallerydao().reWriteGallery(request);
			NftGalleryDAO.getNftgallerydao().showGallery(1, request);
			request.setAttribute("contentPage", "nftGallery/main.jsp");
		} else {
			request.setAttribute("result", "로그인 후 이용 해주세요");
			request.setAttribute("contentPage", "home/home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		
	}

}
