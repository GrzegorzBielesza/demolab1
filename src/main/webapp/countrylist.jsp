<%@ page import="com.example.demolab1.CountryBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp"  language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dane z bazy danych - Java Bean</h1>
<% ArrayList<CountryBean>
        list = (ArrayList<CountryBean>) session.getAttribute("list2");
    for (CountryBean countryItem : list) {
        out.println(countryItem.getName() + " " + countryItem.getCode() + " " + countryItem.getPopulation());
        out.println(list.indexOf(countryItem));
%>
    <a href="details.jsp?index=<%= list.indexOf(countryItem)%>"> Details </a> <br/>
<% } %>
</body>
</html>

