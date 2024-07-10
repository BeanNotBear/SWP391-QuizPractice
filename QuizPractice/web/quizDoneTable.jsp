<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:forEach var="quiz" items="${doneQuizzes}">
    <tr>
        <td>${quiz.id}</td>
        <td>${quiz.subjectName}</td>
        <td>${quiz.quizName}</td>
        <td>${quiz.level}</td>
        <td>${quiz.numberQuestion}</td>
        <td>${quiz.duration}</td>
        <td><fmt:formatNumber value="${quiz.score}" pattern="0.##"/>%</td>
        <td><a href="viewQuiz?quizId=${quiz.id}" class="btn btn-info btn-sm">View</a></td>
    </tr>
</c:forEach>