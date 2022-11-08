package com.example.demolab1;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
  private String message;

  public void init() {
    message = "Hello World!";
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");

    // Hello
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("<h1>" + message + "</h1>");
    out.println("</body></html>");

    out.println("<h2>Dane serwera</h2>");
    out.println("<p>request.getServerName(): " + request.getServerName() +
      "</p>");
    out.println("<p>request.getServerPort(): " + request.getServerPort() +
      "</p>");
    out.println("<p>request.getRemoteHost(): " + request.getRemoteHost() +
      "</p>");
    out.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() +
      "</p>");
    out.println("<h2>Szczegóły żądania</h2>");
    out.println("<p>request.getMethod(): " + request.getMethod() + " </p>");
    out.println("<p>request.getQueryString(): " + request.getQueryString() +
      "</p>");

    out.println("<p>data z processRequest " + new Date() + "</p>");

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date d = new Date();

    out.println("<p>data z processRequest2 " + dateFormat.format(d) + "</p>");

  }

  public void destroy() {
  }
}
