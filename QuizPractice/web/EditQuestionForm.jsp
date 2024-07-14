<form id="editQuestionForm">
    <input type="hidden" name="id" value="${question.id}">
    <div class="form-group">
        <label for="detail">Detail:</label>
        <input type="text" class="form-control" id="detail" name="detail" value="${question.detail}" required>
    </div>
    <div class="form-group">
        <label for="suggestion">Suggestion:</label>
        <input type="text" class="form-control" id="suggestion" name="suggestion" value="${question.suggestion}" required>
    </div>
    <div class="form-group">
        <label for="status">Status:</label>
        <select class="form-control" id="status" name="status">
            <option value="1" ${question.status == 1 ? 'selected' : ''}>Active</option>
            <option value="0" ${question.status == 0 ? 'selected' : ''}>Deactive</option>
        </select>
    </div>
    <div class="form-group">
        <label for="media">Media:</label>
        <input type="text" class="form-control" id="media" name="media" value="${question.media}">
    </div>
</form>