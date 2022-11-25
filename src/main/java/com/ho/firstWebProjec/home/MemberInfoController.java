package com.ho.firstWebProjec.home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberInfoController")
public class MemberInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInfoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(JoinDAO.getJoindao().loginCheck(request)) {
			DateManager.getCurYear(request);
			JoinDAO.getJoindao().getInfo(request);
			request.getRequestDispatcher("jsp/member/memberInfo.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		}
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(JoinDAO.getJoindao().loginCheck(request)) {
			DateManager.getCurYear(request);
			JoinDAO.getJoindao().getInfo(request);
			JoinDAO.getJoindao().infoModify(request);
			request.getRequestDispatcher("jsp/member/memberInfo.jsp").forward(request, response);
		} else {
			request.setAttribute("contentPage", "home/home.jsp");
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		}
	}

}
