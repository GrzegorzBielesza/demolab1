<%@ page import="com.example.demolab1.CountryBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp"  language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dane z bazy danych - Java Bean</h1>
<% ArrayList<CountryBean> list = (ArrayList<CountryBean>) session.getAttribute("list2");

    CountryBean countryItem = list.get(Integer.parseInt(request.getParameter("index")));

    out.println("Details of " + countryItem.getName() + "</br>Country code: " + countryItem.getCode() + "</br>Population: " + countryItem.getPopulation() + "</br>Surface area: " + countryItem.getPopulation() + " </br><a href=\"list-servlet\">Country List</a>");
%>
</body>
</html>

