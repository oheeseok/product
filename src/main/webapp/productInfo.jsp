<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>📃Product Info📃</title>
</head>
<body>
    <h2>📃Product Info📃</h2>
    <hr>
    <ul>
        <li>id: ${product.id}</li>
        <li>name: ${product.name}</li>
        <li>price: ${product.price}</li>
        <li>manufacturer: ${product.manufacturer}</li>
        <li>manufacturing date: ${product.manufacturingDate}</li>
    </ul>
</h2>
</body>
</html>