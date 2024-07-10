<c:forEach var="question" items="${questions}">
    <tr>
        <td>${question.id}</td>
        <td>${question.detail}</td>
        <td>${question.suggestion}</td>
        <td>${question.subjectName}</td>
        <td>${question.lessonName}</td>
        <td class="${question.status == 1 ? 'status-active' : 'status-deactive'}">
            ${question.status == 1 ? 'Active' : 'Deactive'}
        </td>
        <td>
            <button class="btn btn-${question.status == 1 ? 'danger' : 'success'} toggle-status" data-id="${question.id}" data-action="${question.status == 1 ? 'deactivate' : 'activate'}">
                ${question.status == 1 ? 'Deactivate' : 'Activate'}
            </button>
        </td>
        <td>
            <button class="btn btn-info edit-question" data-id="${question.id}">Edit</button>
        </td>
    </tr>
</c:forEach>