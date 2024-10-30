package web.itmo.lab2.servlets;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import web.itmo.lab2.checker.HitChecker;
import web.itmo.lab2.models.HitDataBean;
import web.itmo.lab2.models.HitDataTableBean;
import web.itmo.lab2.validator.ParameterValidator;
import web.itmo.lab2.validator.ValidParameter;

@WebServlet(name = "areaCheckServlet", value = "/area-check-servlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String xString = (String) request.getAttribute("x");
            String yString = (String) request.getAttribute("y");
            String rString = (String) request.getAttribute("R");


            double start = System.nanoTime();

            response.setCharacterEncoding("UTF-8");
            String message = "";

            ValidParameter checkString = ParameterValidator.checkStrings(xString, yString, rString);

            try {
                if (!checkString.getValid()) {
                    throw new Exception(checkString.getMessage());
                }

                float x = Float.parseFloat(xString);
                float y = Float.parseFloat(yString);
                float R = Float.parseFloat(rString);

                ValidParameter checkRanges = ParameterValidator.checkRanges(x, y, R);

                if (!checkRanges.getValid()) {
                    throw new Exception(checkRanges.getMessage());
                }

                boolean hit = HitChecker.checkHit(x, y, R);

                if (hit) {
                    message = "Point hits the area";
                } else {
                    message = "Point doesn't hit the area";
                }
            } catch (Exception e) {
                message = e.getMessage();
            }

            HitDataBean newBean = new HitDataBean();

            newBean.setX(xString);
            newBean.setY(yString);
            newBean.setR(rString);
            newBean.setHit(message);

            double executionTime = Math.round(((System.nanoTime() - start) * 0.0000001) * 100.0) / 100.0;
            newBean.setExecutionTime(executionTime);

            HttpSession session = request.getSession();
            HitDataTableBean hitTable = (HitDataTableBean) session.getAttribute("resultTable");

            if (hitTable == null) {
                hitTable = new HitDataTableBean();
            }

            ArrayList<HitDataBean> beans = hitTable.getHits();
            beans.add(newBean);
            hitTable.setHits(beans);

            session.setAttribute("resultRow", newBean);
            session.setAttribute("resultTable", hitTable);
        }
        catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}