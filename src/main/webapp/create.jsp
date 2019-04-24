<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head> <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<h3>Создание  Еды</h3>
<table>
    <tr>
        <td>ID</td>
        <td>Прием</td>
        <td>Время</td>
        <td>Калории</td>
    </tr>
    <form method="POST" action='meals' name="frmEditMeal">
        <input type="hidden" name="action" value="crate">


        Time : <input
            type="date" name="time"
            value="Указать дату"/> <br/>
        Прием пищи: <input
            type="text" name="description"
            value="<c:out value="Прием пищи" />"/> <br/>
        Калории: <input
            type="text" name="calories"
            value="<c:out value="Каллории" />"/> <br/>
        <input
                type="submit" value="Сохранить"/>
    </form>


</body>
</html>
