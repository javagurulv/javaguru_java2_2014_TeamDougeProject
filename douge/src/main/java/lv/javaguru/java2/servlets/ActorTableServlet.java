package lv.javaguru.java2.servlets;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.TableDataFactory;
import lv.javaguru.java2.Controller.View.TableDataToWEBTableConverter;
import lv.javaguru.java2.database.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Juris on 05.11.2014.
 */
public class ActorTableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        HttpSession httpSession = req.getSession();

        PrintWriter out = resp.getWriter();
        out.println("<body>");
        TableData actorTableData = TableDataFactory.getInstance().getActorTableData();
        try {
            actorTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }
        TableDataToWEBTableConverter tableDataToWEBTableConverter = new TableDataToWEBTableConverter();
        out.println(tableDataToWEBTableConverter.convertToWebTable(actorTableData));

        out.println("</body>");

    }
}
