<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
    <div class="container">
        <br>
        <table class="table">
            <caption>Your todos:</caption>
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Target date</th>
                    <th>Is it done</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                   <tr>
                        <td>${todo.desc}</td>
                        <td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td> <!mogu dodati pattern formata za datum koji zelim>
                        <td>${todo.done}</td>
                        <td><a tyle="button" class="btn btn-success" href="/update-todo?id=${todo.id}">Update</a></td>
                        <td><a tyle="button" class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a></td>
                    </tr> 
                </c:forEach>
                
            </tbody>
        </table>
        <a href="/add-todo" class="button">Add a Todo</a> 
    </div>
<%@ include file="common/footer.jspf"%>