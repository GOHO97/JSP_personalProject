package com.ho.firstWebProjec.home;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class DateManager {
	
	public static void getCurYear(HttpServletRequest req) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String curYear2 = sdf.format(now);
		int curYear = Integer.parseInt(curYear2);
		req.setAttribute("curYear", curYear);
	}
	
	public static void getDate(Date date, HttpServletRequest req) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		req.setAttribute("year", Integer.parseInt(sdf.format(date)));
		
		sdf = new SimpleDateFormat("MM");
		req.setAttribute("month", Integer.parseInt(sdf.format(date)));
		
		sdf = new SimpleDateFormat("dd");
		req.setAttribute("day", Integer.parseInt(sdf.format(date)));
	}
	
}
