package lv.javaguru.java2.servlets.mvc.old_servlets;

/**
 * Created by Radchuk on 11/3/2014.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FilmTableServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<body>");
        TableData filmTableData = TableDataFactory.getInstance().getFilmTableData();
        try {
            filmTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }
        TableDataToWEBTableConverter tableDataToWEBTableConverter = new TableDataToWEBTableConverter();
        out.println(tableDataToWEBTableConverter.convertToWebTable(filmTableData));

        out.println("</body>");*/
    }
}
