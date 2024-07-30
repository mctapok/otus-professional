package ru.gavrilov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "CalculatorServlet", urlPatterns = "/calculator/*")
public class CalculatorServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(CalculatorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getPathInfo();
        logger.info("{} - method: {} executed ", new Date(), url.substring(1));
        double firstParam = Double.parseDouble(req.getParameter("a"));
        double secondParam = Double.parseDouble(req.getParameter("b"));
        double result;
        switch (url) {
            case "/add":
                result = firstParam + secondParam;
                break;
            case "/subtract":
                result = firstParam - secondParam;
                break;
            case "/divide":
                result = firstParam / secondParam;
                if (secondParam == 0) {
                    throw new ArithmeticException("divide by zero is forbidden");
                }
                break;
            case "/multiply":
                result = firstParam * secondParam;
                break;
            default:
                resp.getWriter().println("Invalid operation.");
                return;
        }
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println("<html><body><h1> result: " + result + "</h1></body></html>");
        out.close();
    }
}