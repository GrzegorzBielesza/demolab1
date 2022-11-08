package com.example.demolab1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "listServlet", value = "/list-servlet")
public class ListServlet extends HttpServlet {
  private String message;

  public void init() {}

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");

    // Hello
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession(true);
    session.setAttribute("list", "");
    ArrayList<CountryBean> countryList = new ArrayList<>();

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world? serverTimezone=UTC", "root", "");

      Statement st = conn.createStatement();
      String query = "SELECT * FROM Country WHERE Continent = 'Europe'";
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        CountryBean cb = new CountryBean();
        cb.setName(rs.getString("name"));
        cb.setCode(rs.getString("code"));
        cb.setPopulation(rs.getLong("population"));
        countryList.add(cb);

/*        out.print(rs.getString("name")+" ");
        out.print(rs.getString("code")+" ");
        out.print(rs.getString("population"));*/
      }
      session.setAttribute("list2", countryList);
      response.sendRedirect("countrylist.jsp");
    } catch (SQLException | ClassNotFoundException e){
      out.println("Wystąpił problem: "+e.getMessage());
    }


  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");

    // Hello
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession(true);

  }

  public void destroy() {
  }

}
