package com.example.demo.servlets;

import com.example.demo.DAO.FeatureDAO;
import com.example.demo.DAO.InitFenwickDAO;
import com.example.demo.DAO.TidslinjeDAO;
import com.example.demo.entities.Feature;
import com.example.demo.entities.InitFenwick;
import com.example.demo.entities.Tidslinje;
import com.example.demo.wrapper.*;
import com.example.demo.wrapperServices.WrapperService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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


@WebServlet(name = "textServlet", value = "/textServlet")
public class textServlet extends HttpServlet {

    @EJB
    private TidslinjeDAO tidslinjeDAO;

    @EJB
    private InitFenwickDAO initFenwickDAO;

    @EJB
    private FeatureDAO featureDAO;

    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        if(request.getContentType().equals("application/x-www-form-urlencoded; charset=UTF-8")){
            String remoteMethod = request.getParameter("remoteMethod");
             if(remoteMethod.equals("getInitState")){
                response.setContentType("application/json");
                Type typeInfo = new TypeToken<InitFenwickTidslinjeFeatureWrapper>() {}.getType();
                 List<Tidslinje> tidslinjeListe = null;
                 List<Feature>   features       = null;
                 try {
                    tidslinjeListe = tidslinjeDAO.getTidslinjer();
                    features       = featureDAO.getFeatures();
                }
                 catch (Exception ex){
                     out.println(ex.getMessage());
                     out.close();
                     return;
                 }


                InitFenwick initFenwick = initFenwickDAO.getFenwick();
                InitFenwickTidslinjeFeatureWrapper wrapped = WrapperService.assembleInitFenwickTidslinjeWrapper(initFenwick,tidslinjeListe,features);

                String json = gson.toJson(wrapped, typeInfo);
                out.println(json);
                out.close();
                return;
            }
            else {
                out.println("elseStatement");
            }
        }
        else if(request.getContentType().equals("application/json; charset=UTF-8")){

            StringBuffer string = new StringBuffer();
            String line = null;
            try(BufferedReader reader = request.getReader()){
                while ((line = reader.readLine()) != null)
                    string.append(line);
            } catch (Exception e) { }


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
            Boolean isTypetimestampMethodWrapper = true;
            timestampMethodWrapper wrapptimestamp = null;
            response.setContentType("application/json");

            try{

                wrapptimestamp = gson.fromJson(string.toString(),timestampMethodWrapper.class);

            }
            catch (Exception ex){
                isTypetimestampMethodWrapper = false;
                out.println("ERROR 1");
                out.close();
                return;

            }
            if(isTypetimestampMethodWrapper){
                String remoteMethod = wrapptimestamp.getRemoteMethod();
                if(remoteMethod.equals("getChanges")){
                    Type typeInfo = new TypeToken<List<tidslinjeCommandWrapper>>() {}.getType();
                    final Long timestampCopy = wrapptimestamp.getTimestamp();
                    //Type typeInfo  = new TypeToken<List<Tidslinje>>() {}.getType();
                    try{
                       List<tidslinjeCommandWrapper> tidslinjene = tidslinjeDAO.getLatestChangedOrAdded(timestampCopy).stream().map((x)-> { return WrapperService.assembletidslinjeCommandWrapper(x,timestampCopy);}).collect(Collectors.toList());
                        //List<Tidslinje> tidslinjene = tidslinjeDAO.getLatestChangedOrAdded(timestampCopy);
                        String json = gson.toJson(tidslinjene, typeInfo);
                        out.println(json);
                        out.close();
                        return;

                    }
                    catch (Exception ex){

                        out.println("ERROR 2");
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