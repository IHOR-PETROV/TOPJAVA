<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
 <html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<h3>Список приема пищи</h3>
<table>
    <tr>
        <td>ID</td>
        <td>Прием</td>
        <td>Время</td>
        <td>Калории</td>
        <td></td>
        <td></td>
    </tr>

    <c:forEach var="meal" items="${listMealTo}" varStatus="сounter">

        <c:if test="${!meal.excess}">
            <tr>
                <td style="color: black; ">${сounter.count}</td>
                <td>${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy. HH:mm:ss')} </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
            </tr>

        </c:if>
        <c:if test="${meal.excess }">
            <tr style="color: red; " >
                <td>${сounter.count}</td>
                <td> ${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy. HH:mm:ss')}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>

            </tr>

        </c:if>

    </c:forEach>

</table>
<a href="create.jsp">Создать Еду</a>
</body>
</html>
