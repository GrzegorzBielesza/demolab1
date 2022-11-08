<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 18.10.2022
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp"  language="java" %>
<jsp:useBean id="loan" class="com.example.demolab1.LoanBean" scope="session" />
<jsp:setProperty name="loan" property="*" />
<html>
<head>
    <title>Title</title>

</head>
<body>
<h1>Kalkulator rat - Java Bean</h1>
<form method="POST">
    Kwota pożyczki: <input  name='kwota' id='kwota' type="text" value="<%= loan.getKwota() %>"/><br/>
    Procent roczny: <input  name='procent' id='procent' type="text" value="<%= loan.getProcent() %>"/><br/>
    Liczba rat: <input  name='liczbaRat' id='liczbaRat' type="text" value="<%= loan.getLiczbaRat() %>"/><br/><br/>
    <input type="submit"/>
    <h2><%= loan.getRata()%></h2>
</form>

</body>
</html>
