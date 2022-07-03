package com.example.demo.servlets;

import com.example.demo.DAO.TidslinjeDAO;
import com.example.demo.DAO.newTextDAO;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "newTextServlet", value = "/newTextServlet")
public class newTextServlet extends HttpServlet {
    @EJB
    private newTextDAO newTextDAO;

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        if(request.getContentType().equals("application/x-www-form-urlencoded; charset=UTF-8")){

        }
        else if(request.getContentType().equals("application/json; charset=UTF-8")){
            StringBuffer string = new StringBuffer();
            String line = null;
            try(BufferedReader reader = request.getReader()){
                while ((line = reader.readLine()) != null)
                    string.append(line);
            } catch (Exception e) { }


        }
    }
}
