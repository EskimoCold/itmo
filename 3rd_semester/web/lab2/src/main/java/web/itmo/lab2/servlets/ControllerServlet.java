package web.itmo.lab2.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import web.itmo.lab2.models.HitDataTableBean;
import web.itmo.lab2.retriever.JsonRetriever;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "controllerServet", value = "/controller")
public class ControllerServlet extends HttpServlet{
    private HitDataTableBean beans;

    public ControllerServlet() {
        this.beans = new HitDataTableBean();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("resultTable", this.beans);
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("resultTable", this.beans);
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException{
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            BufferedReader reader = request.getReader();
            JsonObject requestJson = JsonRetriever.getJson(reader);

            String x = requestJson.get("x").getAsString();
            String y = requestJson.get("y").getAsString();
            String R = requestJson.get("r").getAsString();

            request.setAttribute("x", x);
            request.setAttribute("y", y);
            request.setAttribute("R", R);

            boolean hasCoordinates = (x != null) && (y != null) && (R != null);

            if (hasCoordinates) {
                request.getRequestDispatcher("/area-check-servlet").forward(request, response);
            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception e){
            getServletContext().setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}