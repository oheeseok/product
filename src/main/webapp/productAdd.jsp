<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🧺Add Product🧺</title>
</head>
<body>
    <h2>Add Product🧺</h2>
    <hr>
    <form action="/products?action=add" method="post">
        name <input type="text" name="name"><br>
        price <input type="text" name="price"><br>
        manufacturer <input type="text" name="manufacturer"><br>
        manufacturing date <input type="date" name="manufacturingDate"><br>
        <input type="submit" value="추가">
    </form>
</body>
</html>