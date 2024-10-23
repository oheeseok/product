<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>📜Product List📜</title>
</head>
<body>
    <h2>📜Product List📜</h2>
    <hr>
    <table border="1" cellspacing="1" cellpadding="1">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>manufacturer</th>
            <th>manufacturing date</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.manufacturer}</td>
                <td>${product.manufacturingDate}</td>
                <td><a href="/products?action=update&id=${product.id}">수정</a></td>
                <td><a href="/products?action=delete&id=${product.id}">삭제</a></td>
            </tr>
        </c:forEach>
    </table>
    <button onClick="location.href='/products?action=add'">추가</button>
</body>
</html>