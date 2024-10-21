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
        <title>Znane miejsca</title>
        <style>
        table, th, td {
          border: 1px solid black;
        }
        </style>
    </head>
    <body>
        <table>
            <tr>
                <th>Miasto</th>
                <th>Ulica</th>
            </tr>
            <c:forEach var="place" items="${dataBean.allPlaces}">
            <tr>
                <th>${place.city}</th>
                <th>${place.street}</th>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
