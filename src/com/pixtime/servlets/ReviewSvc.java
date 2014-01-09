package com.pixtime.servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pixtime.utils.PixTimeConstants;
import com.pixtime.vo.Movie;
import com.pixtime.vo.Review;

public class ReviewSvc extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		String pixtimeStatusDesc = "Review Added";
		String pixtimeStatus = "SUCCESS";
		Gson gson = new Gson();

		try {
			Reader reader = new InputStreamReader(req.getInputStream());
			String title=req.getParameter("fc");
			String movieid=req.getParameter("movieid");
			String taip=req.getParameter("ta");
			String userid=req.getParameter("t1");
			System.out.println("title  taip  "+title+"  "+taip+"     "+userid);
	                    System.out.println("movieid res"+ movieid.trim());
			Review review = gson.fromJson(reader, Review.class);
		
		String comment=title+" "+taip;
		
		
			ReviewDao rvDao = new ReviewDao();
			rvDao.insertReview(movieid,comment,userid);
			resp.setStatus(201);
			
		}catch(Exception e){
			e.printStackTrace();
			pixtimeStatusDesc = "internal Error Occured. Please check with us Later";
			pixtimeStatus = "FAILED";
			resp.setStatus(500);
			LOG.info("Review Add Exception Occured" + e.toString());

		
		}
		resp.setContentType(PixTimeConstants.CONTENT_TYPE);
		resp.addHeader(PixTimeConstants.PIXTIME_STATUS, pixtimeStatus);
		resp.addHeader(PixTimeConstants.PIXTIME_DESC, pixtimeStatusDesc);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
}
