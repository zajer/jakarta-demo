<%-- 
    Document   : people
    Created on : 21 Oct 2024, 04:41:17
    Author     : pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Znane osoby</title>
        <style>
        table, th, td {
          border: 1px solid black;
        }
        </style>
    </head>
    <body>
        <table>
            <tr>
                <th>Imie</th>
                <th>Nazwisko</th> 
                <th>PESEL</th>
                <th>Znajomi (PESELE)</th>
                <th>Miasto</th>
                <th>Ulica</th>
            </tr>
        <c:forEach var="person" items="${dataBean.allPeople}">
            <tr>
                <th>${person.firstName}</th>
                <th>${person.lastName}</th>
                <th>${person.identificationNumber}</th>
                <th>
                    <c:forEach var="friend" items="${person.friends}">
                        ${friend.identificationNumber};
                    </c:forEach>
                </th>
                <th>${person.location.city}</th>
                <th>${person.location.street}</th>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
