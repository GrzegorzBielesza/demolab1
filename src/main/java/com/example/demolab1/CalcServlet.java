package com.example.demolab1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(name = "calcServlet", value = "/calc-servlet")
public class CalcServlet extends HttpServlet {
  private String message;
  List<String> operationsHistory = new ArrayList<String>();

  public void init() {}

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");

    // Hello
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession(true);

    String nazwaCookie = "welcomeCookie";
    Cookie[ ] cookies = request.getCookies();
    if ( cookies == null )
    {
      out.println("<h1>Witaj po raz pierwszy</h1><br/>");
      response.addCookie(new Cookie("welcomeCookie", "welcomeCookie"));
    }
    else if(Arrays.stream(cookies).anyMatch(cookie -> cookie.getName().equals(nazwaCookie)))
    {
      out.println("<h1>Witaj po raz kolejny</h1><br/>");
    }
    else{
      out.println("<h1>Witaj po raz pierwszy</h1><br/>");
      response.addCookie(new Cookie("welcomeCookie", "welcomeCookie"));
    }

      out.println("<h1>Atrybuty sesji:</h1><br/>");
      out.println("<p> add_history: " + session.getAttribute("add_history") + "<p><br/>");
      out.println("<p> sub_history: " + session.getAttribute("sub_history") + "<p><br/>");
      out.println("<p> mul_history: " + session.getAttribute("mul_history") + "<p><br/>");
      out.println("<p> div_history: " + session.getAttribute("div_history") + "<p><br/>");

      out.println("<a href=\"calc.html\">Powrot do formularza</a>");

  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");

    // Hello
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession(true);

    String nazwaCookie = "welcomeCookie";
    Cookie[ ] cookies = request.getCookies();
    if ( cookies == null )
    {
      out.println("<h1>Witaj po raz pierwszy</h1><br/>");
      response.addCookie(new Cookie("welcomeCookie", "welcomeCookie"));
    }
    else if(Arrays.stream(cookies).anyMatch(cookie -> cookie.getName().equals(nazwaCookie)))
      {
        out.println("<h1>Witaj po raz kolejny</h1><br/>");
      }
      else{
        out.println("<h1>Witaj po raz pierwszy</h1><br/>");
        response.addCookie(new Cookie("welcomeCookie", "welcomeCookie"));
    }

    if(request.getParameter("clear")!=null){
      clear(request, session);
      out.println("<h2>Wyczyszczono parametr sesji</h2><br/>");
    }

    else {
      if (session.getAttribute("add_history") == null)
        session.setAttribute("add_history", 0);
      if (session.getAttribute("sub_history") == null)
        session.setAttribute("sub_history", 0);
      if (session.getAttribute("mul_history") == null)
        session.setAttribute("mul_history", 0);
      if (session.getAttribute("div_history") == null)
        session.setAttribute("div_history", 0);

      try {
        out.println("<h2>Wynik to: " + calc(request, session) + "</h2>");
      } catch (IllegalArgumentException e) {
        out.println("<h2>Nie wszystkie pola zostaly poprawnie uzupelnione</h2>");
      } catch (ArithmeticException e) {
        out.println("<h2>Nie mozna dzielic przez 0</h2>");
      }
    }

      out.println("<h1>Atrybuty sesji:</h1><br/>");
      out.println("<p> add_history: " + session.getAttribute("add_history") + "<p><br/>");
      out.println("<p> sub_history: " + session.getAttribute("sub_history") + "<p><br/>");
      out.println("<p> mul_history: " + session.getAttribute("mul_history") + "<p><br/>");
      out.println("<p> div_history: " + session.getAttribute("div_history") + "<p><br/>");

      out.println("<a href=\"calc.html\">Powrot do formularza</a>");

  }

  public void destroy() {
  }

  private void clear(HttpServletRequest request, HttpSession session) {
    switch(request.getParameter("operation_delete")){
      case "clear_add":
        session.setAttribute("add_history", 0);
        break;
      case "clear_sub":
        session.setAttribute("sub_history", 0);
        break;
      case "clear_mul":
        session.setAttribute("mul_history", 0);
        break;
      case "clear_div":
        session.setAttribute("div_history", 0);
        break;
    }
  }

  private double calc(HttpServletRequest request, HttpSession session) {
    double arg1;
    double arg2;
    double result;
    try {
      arg1 = Double.parseDouble(request.getParameter("n1"));
      arg2 = Double.parseDouble(request.getParameter("n2"));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException();
    }
    switch(request.getParameter("operation")){
      case "add":
        result = arg1 + arg2;
        this.operationsHistory = new ArrayList<>(Arrays.asList(session.getAttribute("add_history").toString().split(",")));
        this.operationsHistory.add(String.valueOf(result));
        session.setAttribute("add_history", this.operationsHistory);
        return result;
      case "sub":
        result = arg1 - arg2;
        this.operationsHistory = new ArrayList<>(Arrays.asList(session.getAttribute("sub_history").toString().split(",")));
        this.operationsHistory.add(String.valueOf(result));
        session.setAttribute("sub_history", this.operationsHistory);
        return result;
      case "mul":
        result = arg1 * arg2;
        this.operationsHistory = new ArrayList<>(Arrays.asList(session.getAttribute("mul_history").toString().split(",")));
        this.operationsHistory.add(String.valueOf(result));
        session.setAttribute("mul_history", this.operationsHistory);
        return result;
      case "div":
        if(Double.parseDouble(request.getParameter("n2")) == 0) {
        throw new ArithmeticException();
        }
        result = arg1 / arg2;
        this.operationsHistory = new ArrayList<>(Arrays.asList(session.getAttribute("div_history").toString().split(",")));
        this.operationsHistory.add(String.valueOf(result));
        session.setAttribute("div_history", this.operationsHistory);
        return result;
      default:
        return 0;
    }
  }
}
