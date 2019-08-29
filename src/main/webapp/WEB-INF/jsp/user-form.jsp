<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Users</a>

    </h2>
</center>
<div align="center">
    <c:choose>
        <c:when test="${user != null}">
            <form action="update" method="post">
        </c:when>
        <c:otherwise>
            <form action="insert" method="post">
        </c:otherwise>
    </c:choose>
              <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Edit User
                        </c:if>
                        <c:if test="${user == null}">
                            Add New User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                </c:if>
                <tr>
                    <th>User Name:</th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value="${user.name}" />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>User Password:</th>
                    <td>
                        <input type="password" name="password" size="45"
                               value="<c:out value="${user.password}" />"
                        />
                    </td>
                </tr>
                <tr>
                <tr>
                    <th>Roles:</th>
                    <td>
                        <select name="Role">
                            <c:forEach items="${allRoles}" var="role">
                                <option value="${role}">
                                        ${role}
                                </option>
                            </c:forEach>

                        </select>
                    </td>

                </tr>
                <tr>
                    <th>User Email:</th>
                    <td>
                        <input type="text" name="email" size="45"
                               value="<c:out value="${user.email}" />"
                        />
                    </td>
                </tr>
                  <tr>
                      <th>Country:</th>
                      <td>
                          <select name="Country">
                              <c:forEach items="${countriesList}" var="country">
                                  <option value="${country}">
                                          ${country}
                                  </option>
                              </c:forEach>
                              <option selected value="${countriesList.get(1)}">${countriesList.get(1)}</option>
                          </select>
                      </td>
                  </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button type="submit">Save</button>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>