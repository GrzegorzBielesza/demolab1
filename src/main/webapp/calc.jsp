<%--
  Created by IntelliJ IDEA.
  User: g.bielesza
  Date: 18.10.2022
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String date = dateFormat.format(now);
    out.println(date);

%>
<br/>
<h1>Kalkulator rat</h1>
<form method="POST">
    Kwota pożyczki: <input type="text" name="kwota" /><br/>
    Procent roczny: <input type="text" name="procent" /><br/>
    Liczba rat: <input type="text" name="liczba_rat" /><br/><br/>
    <input type="submit" name="wyslij" value="wyslij" />
</form>

<% if (request.getParameter("wyslij")!=null){
    double res = 0;
    DecimalFormat format = new DecimalFormat("#.00");
    try {
        double k = Double.parseDouble(request.getParameter("kwota"));
        double pr = Double.parseDouble(request.getParameter("procent"));
        double lr = Double.parseDouble(request.getParameter("liczba_rat"));
        double p = pr / 1200;
        res = (k * p) / (1 - 1 / Math.pow(1 + p, lr));
    }
    catch (Exception e){
        out.println("Wprowadzono bledne dane");
    }
    out.println("<h2>Rata miesięczna: "+format.format(res)+"</h2>");
}
%>
        </body>
</html>
