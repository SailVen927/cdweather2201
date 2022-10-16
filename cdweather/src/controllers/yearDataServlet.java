package controllers;

import entity.percent;
import entity.weather;
import service.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "yearDataServlet")
public class yearDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service s=new service();
        List<weather> w= null;
        List<percent> p =null;
        int year = Integer.parseInt(request.getParameter("data"));
        try {
            w = s.getWeather(year);
            //p= s.countPercent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("weathers",w);
        //request.setAttribute("percents",p);

        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
