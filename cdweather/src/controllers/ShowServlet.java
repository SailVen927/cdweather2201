package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import entity.percent;
import entity.weather;
import service.service;

@WebServlet(name = "ShowServlet")
public class ShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service s=new service();
        List<weather> w= null;
        List<percent> p =null;
        try {
            w = s.getWeather(2020);
            //p= s.countPercent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("weathers",w);
        //request.setAttribute("percents",p);

        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
