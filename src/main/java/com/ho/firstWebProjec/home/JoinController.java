package com.ho.firstWebProjec.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JoinController")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateManager.getCurYear(request);
		request.getRequestDispatcher("jsp/member/join.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JoinDAO.getJoindao().join(request);
		request.setAttribute("loginPage", "member/login.jsp");
		request.setAttribute("contentPage", "home/home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
