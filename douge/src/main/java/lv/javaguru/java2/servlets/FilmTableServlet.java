package lv.javaguru.java2.servlets;

/**
 * Created by Radchuk on 11/3/2014.
 */
import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.TableDataFactory;
import lv.javaguru.java2.database.DBException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FilmTableServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
        resp.setContentType("text/html");
        HttpSession httpSession = req.getSession();
        TableData filmTableData = TableDataFactory.getInstance().getFilmTableData();
        try {
            filmTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }
        ArrayList<Map<String, String>> tableData = filmTableData.getTableData();
        /*for (int i = 0; i < tableData.size() ; i++) {
            Map<String, String> map = tableData.get(i);
            map.
        }*/



        // Prepare output html
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + "Films servlet running!" + "</h1>");

    }
}
