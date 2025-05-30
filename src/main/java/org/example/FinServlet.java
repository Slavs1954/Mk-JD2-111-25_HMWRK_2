package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/fin")
public class FinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String[] rows = req.getParameterValues("rows");
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<table border='1'>");
        long buySum = 0;
        long quantity = 0;
        long medPrice = 0;
        for (String row : rows) {
            String[] cells = row.split(",");
            writer.write("<tr>");
            for (String cell : cells) {
                writer.write("<td>" + cell + "</td>");
            }
            long result = Long.parseLong(cells[0]) * Long.parseLong(cells[1]);
            buySum += result;
            quantity += Long.parseLong(cells[1]);

            writer.write("<td>" + result + "</td>");
            writer.write("</tr>");
        }
        medPrice = buySum / quantity;
        writer.write("</table>");
        writer.write("<br>Общая цена покупки: " + buySum + "</br>");
        writer.write("<br>Общее количество: " + quantity + "</br>");
        writer.write("<br>Средняя цена: " + medPrice + "</br>");
    }
}
