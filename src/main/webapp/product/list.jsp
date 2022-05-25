<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/24/2022
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh sách sản phẩm</h1>
<a href="/Products?action=create">Tạo mới sản phẩm</a>
<c:forEach items="${danhSach}" var="sp"><h3>${sp.id},${sp.name},${sp.price} <a href="/Products?action=edit&id=${sp.id}">Edit</a>  <a href="/Products?action=delete&id=${sp.id}">Delete</a></h3></c:forEach><br/>

</body>
</html>
