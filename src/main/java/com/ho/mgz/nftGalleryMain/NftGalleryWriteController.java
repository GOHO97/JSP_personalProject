package com.ho.mgz.nftGalleryMain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ho.firstWebProjec.home.JoinDAO;
import com.ho.token.generator.hoTokenGenerator;

@WebServlet("/NftGalleryWriteController")
public class NftGalleryWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NftGalleryWriteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NftGalleryDAO.getNftgallerydao().clearSearch(request);
		JoinDAO.getJoindao().loginCheck(request);
		hoTokenGenerator.generate(request);
		NftGalleryDAO.getNftgallerydao().selectGallery(request);
		String pageNum = (request.getParameter("pageNum"));
		if(pageNum != null) {
			int pageNum2 = Integer.parseInt(pageNum);
			NftGalleryDAO.getNftgallerydao().showGallery(pageNum2, request);
		} else {
			NftGalleryDAO.getNftgallerydao().showGallery(1, request);
		}
		request.setAttribute("contentPage", "nftGallery/main.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(JoinDAO.getJoindao().loginCheck(request)) {
			hoTokenGenerator.generate(request);
			NftGalleryDAO.getNftgallerydao().clearSearch(request);
			NftGalleryDAO.getNftgallerydao().writeGallery(request);
			NftGalleryDAO.getNftgallerydao().showGallery(1, request);
			request.setAttribute("contentPage", "nftGallery/main.jsp");
		} else {
			request.setAttribute("result", "로그인 후 이용 해주세요");
			request.setAttribute("contentPage", "home/home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
