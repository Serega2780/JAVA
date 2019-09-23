function myJsList() {
<c:forEach items="${listUser}" var="user">
    <tr>
    <td><c:out value="${user.id}"></c:out></td>

    <td>
    <span th:each="role : ${user.getGrantedAuthorities()}">
        <span th:text="${role.getRole()}">

        </span>
        </span>

        </td>
        <td th:text="${user.name}"></td>
        <td th:text="${user.password}"></td>
        <td th:text="${user.email}"></td>
        <td th:text="${user.country}"></td>
        <td>
        <a th:href="@{'edit?id=' + ${user.id}}" class="btn btn-info"
    data-toggle="modal" data-target="#myModal"
    th:attrappend="data-target=${user.id}"
    role="button">Edit</a>

        <form class="form-horizontal" action="#" th:action="@{insert}"
    method="post">
        <fieldset>
        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog"
    th:attrappend="id=${user.id}">
        <div class="modal-dialog modal-lg">
        <div class="modal-content">
        <div class="modal-header">
        <button type="button" class="close"
    data-dismiss="modal">×
</button>
    <h4 class="modal-title">Modal Header</h4>
    </div>
    <div class="modal-body">
        <p>This is a large modal.</p>
    <table class="table table-striped">
        <tr>
        <th>Id:</th>
    <td><input type="text" readonly
    name="id"
    th:value="${user.id}"/></td>
        </tr>

        <tr>
        <th>Name:</th>
    <td><input type="text" name="name"
    th:value="${user.name}"/>
        </td>
        </tr>

        <tr>
        <th>Password:</th>
    <td><input type="password"
    name="password"
    th:value="${user.password}"/>
        </td>
        </tr>

        <tr>
        <th>Email:</th>
    <td><input type="text" name="email"
    th:value="${user.email}"/>
        </td>
        </tr>
        <tr>
        <th>Country:</th>
    <td>
    <select id="countriesList"
    name="country"
    th:value="${user.country}">
        <option value=""> --</option>
        <option th:each="country : ${countriesList}"
    th:value="${country}"
    th:utext="${country}"/>
        </select>
        </td>
        </tr>

        <tr>
        <th>Roles:</th>
    <td>
    <select id="roles" name="roles"
    multiple="multiple">
        <option th:each="authority : ${user.getGrantedAuthorities()}"
    th:value="${authority.getAuthority()}"
    selected="selected"
    style="font-weight: bold;"
    th:utext="${authority.getAuthority()}"/>
        <option value=""> --</option>
        <option th:each="role : ${roles}"
    th:value="${role.getAuthority()}"
    th:utext="${role.getAuthority()}"/>

        </select>
        </td>
        </tr>
        <tr>
        <td colspan="2" align="center">
        <button type="submit">Save</button>
        </td>
        </tr>
        </table>
        </div>
        </div>
        </div>
        </div>

        </fieldset>
        </form>
        </td>
        <td>
        <a th:href="@{'delete?id=' + ${user.id}}" class="btn btn-info"
    role="button">Delete</a>

        </td>
        </tr>
        </c:forEach>
}