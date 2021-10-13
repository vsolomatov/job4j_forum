<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Edit accident</title>
</head>

<body>
    <div class="container pt-3">
        <div class="row">
            <div class="card" style="width: 100%">
                <div class="card-header">
                    Тема #${post.id}: ${post.name}
                </div>
                <div class="card-body">
                    <table id="myTable" class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Автор</th>
                            <th scope="col">Дата создания</th>
                            <th scope="col">Текст</th>
                        </tr>
                        </thead>
                        <tbody id="myTableBody">
                        <c:forEach items="${post.comments}" var="comment">
                            <tr>
                                <td>${comment.id}</td>
                                <td>${comment.username}</td>
                                <td>$${comment.created}</td>
                                <td>${comment.desc}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer">
                    <div>
                        <a href="<c:url value='/comments/add?id=${post.id}'/>">Добавить комментарий</a>
                    </div>
                    <div align="right">
                        <a href="<c:url value='/'/>">Назад</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>