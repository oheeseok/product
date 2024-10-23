<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🎨Product Update🎨</title>
</head>
<body>
    <h2>🎨Product Update🎨</h2>
    <hr>
    <form action="/products?action=update&id=${product.id}" method="post">
        name <input type="text" name="name" value=${product.name} readonly><br>
        price <input type="text" name="price" value=${product.price}><br>
        manufacturer <input type="text" name="manufacturer" value=${product.manufacturer}><br>
        manufacturing date <input type="date" name="manufacturingDate" value=${product.manufacturingDate}><br>
        <input type="submit" value="수정">
    </form>
</body>
</html>