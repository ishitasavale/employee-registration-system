<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Welcome</title>
  <style>
    body{font-family:Arial;margin:20px;background:#f6f7fb}
    .panel{background:white;padding:20px;border-radius:8px;box-shadow:0 6px 18px rgba(0,0,0,0.06)}
    table{border-collapse:collapse;width:100%; background:white}
    th,td{border:1px solid #e0e0e0;padding:10px;text-align:left}
    th{background:#fafafa}
    a { color:#1a73e8; }
  </style>
</head>
<body>
  <div class="panel">
    <h1>Hello, <c:out value="${sessionScope.userName != null ? sessionScope.userName : userName}" /></h1>
    <p><a href="<c:url value='/logout'/>">Logout</a></p>

    <h2>All Registered Users</h2>

    <c:choose>
      <c:when test="${empty users}">
        <p>No users found.</p>
      </c:when>
      <c:otherwise>
        <table>
          <thead>
            <tr><th>ID</th><th>Name</th><th>Login ID</th><th>City</th><th>State</th><th>DOB</th><th>Gender</th></tr>
          </thead>
          <tbody>
            <c:forEach items="${users}" var="u">
              <tr>
                <td><c:out value="${u.id}"/></td>
                <td><c:out value="${u.name}"/></td>
                <td><c:out value="${u.loginId}"/></td>
                <td><c:out value="${u.city}"/></td>
                <td><c:out value="${u.state}"/></td>
                <td>
                  <c:choose>
                    <c:when test="${not empty u.dob}">
                      <fmt:formatDate value="${u.dob}" pattern="yyyy-MM-dd"/>
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                  </c:choose>
                </td>
                <td><c:out value="${u.gender}"/></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:otherwise>
    </c:choose>

  </div>
</body>
</html>
