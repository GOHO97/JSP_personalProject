package com.ho.firstWebProjec.home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberDeleteController")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(JoinDAO.getJoindao().loginCheck(request)){
			JoinDAO.getJoindao().infoDelete(request);
			JoinDAO.getJoindao().logout(request);
			JoinDAO.getJoindao().loginCheck(request);
		}
		request.setAttribute("contentPage", "home/home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
