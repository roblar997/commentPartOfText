package com.example.demo.servlets;

import com.example.demo.DAO.TidslinjeDAO;
import com.example.demo.entities.Tidslinje;
import com.example.demo.wrapper.*;
import com.example.demo.wrapperServices.WrapperService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@WebServlet(name = "textServlet", value = "/textServlet")
public class textServlet extends HttpServlet {

    @EJB
    private TidslinjeDAO tidslinjeDAO;


    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        if(request.getContentType().equals("application/x-www-form-urlencoded; charset=UTF-8")){
            String remoteMethod = request.getParameter("remoteMethod");


        }
        else if(request.getContentType().equals("application/json; charset=UTF-8")){

            StringBuffer string = new StringBuffer();
            String line = null;
            try(BufferedReader reader = request.getReader()){
                while ((line = reader.readLine()) != null)
                    string.append(line);
            } catch (Exception e) { }


            //Safely find right class to convert to.
            Boolean isTypemethodtexttocommentidWrapper = true;
            methodtexttocommentidWrapper wrappen = null;
            try{
                wrappen = gson.fromJson(string.toString(), methodtexttocommentidWrapper.class);

            }
            catch (Exception ex){
                isTypemethodtexttocommentidWrapper = false;
            }
            if(isTypemethodtexttocommentidWrapper){


              if(wrappen.getRemoteMethod().equals("getInitState")){
                response.setContentType("application/json");
                Type typeInfo = new TypeToken<List<Tidslinje>>() {}.getType();
                List<Tidslinje> tidslinjeListe = null;

                try {
                    tidslinjeListe = tidslinjeDAO.getTidslinjer(wrappen.getTexttocommentid());
                }
                catch (Exception ex){
                    out.println(ex.getMessage());
                    out.close();
                    return;
                }

                String json = gson.toJson(tidslinjeListe, typeInfo);
                out.println(json);
                out.close();
                return;
            }}
            //Safely find right class to convert to.
            Boolean isTypetidslinjeMethodWrapper = true;
            tidslinjeMethodWrapper wrapp = null;
            try{
                wrapp = gson.fromJson(string.toString(),tidslinjeMethodWrapper.class);

            }
            catch (Exception ex){
                isTypetidslinjeMethodWrapper = false;
            }

            if(isTypetidslinjeMethodWrapper){
                try {
                    String remoteMethod = wrapp.getRemoteMethod();
                    Tidslinje tidslinje = wrapp.getTimeline();
                    if(remoteMethod.equals("addTimeLine")){
                        response.setContentType("text/html");
                        tidslinjeDAO.addTidslinje(tidslinje);

                        out.close();
                        return;
                    }

                }
                catch (Exception ex){
                    out.println(ex.getMessage());
                    return;
                }
            }
            Boolean isTypetimestampMethodtexttocommentidWrapper = true;
            timestampMethodtexttocommentidWrapper wrapptimestamp = null;
            response.setContentType("application/json");

            try{

                wrapptimestamp = gson.fromJson(string.toString(),timestampMethodtexttocommentidWrapper.class);

            }
            catch (Exception ex){
                isTypetimestampMethodtexttocommentidWrapper = false;
                out.close();
                return;

            }
            if(isTypetimestampMethodtexttocommentidWrapper){
                String remoteMethod = wrapptimestamp.getRemoteMethod();
                if(remoteMethod.equals("getChanges")){
                    Type typeInfo = new TypeToken<List<tidslinjeCommandWrapper>>() {}.getType();
                    final Long timestampCopy = wrapptimestamp.getTimestamp();
                    //Type typeInfo  = new TypeToken<List<Tidslinje>>() {}.getType();
                    try{//
                       List<tidslinjeCommandWrapper> tidslinjene = tidslinjeDAO.getLatestChangedOrAdded(timestampCopy,wrapptimestamp.getTexttocommentid()).stream().map((x)-> { return WrapperService.assembletidslinjeCommandWrapper(x,timestampCopy);}).collect(Collectors.toList());
                        //List<Tidslinje> tidslinjene = tidslinjeDAO.getLatestChangedOrAdded(timestampCopy);
                        String json = gson.toJson(tidslinjene, typeInfo);
                        out.println(json);
                        out.close();
                        return;

                    }
                    catch (Exception ex){

                        out.println(ex.getMessage());
                        out.close();
                        return;

                    }


                }
            }

            Boolean istidslinjeMethodIdWrapper = true;
            tidslinjeMethodIdWrapper tidslinjeMethodIdWrapper = null;
            try{

                tidslinjeMethodIdWrapper = gson.fromJson(string.toString(),tidslinjeMethodIdWrapper.class);
                if(tidslinjeMethodIdWrapper.getTimeline() == null) throw new Exception("Null timeline");

            }
            catch (Exception ex){
                istidslinjeMethodIdWrapper = false;

            }

            if(istidslinjeMethodIdWrapper){
                try {
                    tidslinjeDAO.changeTidsline(tidslinjeMethodIdWrapper.getTimeline(),tidslinjeMethodIdWrapper.getId());

                }
                catch (Exception ex){
                    out.println("ERROR--" + tidslinjeMethodIdWrapper.getTimeline() + " " + tidslinjeMethodIdWrapper.getId());
                }
                out.close();
                return;
            }

            Boolean isTypemethodIdWrapper = true;
            methodIdChangeDateWrapper methodIdChangeDateWrapper = null;
            try{

                methodIdChangeDateWrapper = gson.fromJson(string.toString(), methodIdChangeDateWrapper.class);

            }
            catch (Exception ex){
                isTypemethodIdWrapper = false;

            }
            if(isTypemethodIdWrapper){
                try{
                    tidslinjeDAO.removeTidsline(methodIdChangeDateWrapper.getId(),methodIdChangeDateWrapper.getTimestampChanged());
                    out.println("OK");
                }

                catch (Exception ex){
                    out.println(ex.getMessage() + " " + methodIdChangeDateWrapper.getId() + " " + methodIdChangeDateWrapper.getTimestampChanged());
                }

                out.close();
                return;
            }

        }
        else {
          out.println(request.getContentType());
        }


        out.close();
    }
}