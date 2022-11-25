package com.ho.mgz.nftGalleryMain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ho.firstWebProjec.home.JoinDAO;
import com.ho.token.generator.hoTokenGenerator;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NftGalleryDAO.getNftgallerydao().clearSearch(request);
		NftGalleryDAO.getNftgallerydao().deleteGallery(request);
		JoinDAO.getJoindao().loginCheck(request);
		hoTokenGenerator.generate(request);
		NftGalleryDAO.getNftgallerydao().showGallery(1, request);
		request.setAttribute("contentPage", "nftGallery/main.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
