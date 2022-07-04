package com.example.demo.servlets;

import com.example.demo.DAO.TidslinjeDAO;
import com.example.demo.DAO.newTextDAO;
import com.example.demo.wrapper.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

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
            Boolean isTypeMethodTitleText = true;
            methodTitleTextWrapper wrapper2 = null;
            try{
                wrapper2 = gson.fromJson(string.toString(),methodTitleTextWrapper.class);
                if(wrapper2.getText() == null) throw  new Exception("..");
            }
            catch (Exception ex){
                isTypeMethodTitleText = false;

            }

            if(isTypeMethodTitleText){
                try {
                    newTextDAO.addText(wrapper2.getTitle(),wrapper2.getText());
                    out.println("OK");
                    out.close();
                }
                catch (Exception ex){
                    out.println(ex.getMessage());
                    out.close();
                    return;
                }

            }
            Boolean isTypeMethodTitle = true;

            methodTitleWrapper wrapper = null;
            try{
                wrapper = gson.fromJson(string.toString(),methodTitleWrapper.class);
                if(wrapper.getTitle() == null) throw  new Exception("..");
            }
            catch (Exception ex){
                isTypeMethodTitle = false;

            }
            if(isTypeMethodTitle){

                try {
                    if(wrapper.getRemoteMethod().equals("getText")){
                        out.println(newTextDAO.getText(wrapper.getTitle()));
                        out.close();
                    }

                    return;
                }
                catch (Exception ex){
                    out.println(ex.getMessage());
                    out.close();
                    return;
                }

            }

            Boolean isTypeMethodOnly = true;
            methodOnlyWrapper wrapp = null;
            try{
                wrapp = gson.fromJson(string.toString(),methodOnlyWrapper.class);

            }
            catch (Exception ex){
                isTypeMethodOnly = false;

            }
            if(isTypeMethodOnly){
                try {
                    if(wrapp.getRemoteMethod().equals("getTitles")){
                        response.setContentType("application/json");
                        Type typeInfo = new TypeToken<List<String>>() {}.getType();
                        List<String> titles = newTextDAO.getTitles();
                        String json = gson.toJson(titles, typeInfo);
                        out.println(json);
                        out.close();
                        return;
                    }
                }
                catch (Exception ex){
                    out.println(ex.getMessage());
                    out.close();
                    return;
                }

                out.close();
            }







        }
    }
}
