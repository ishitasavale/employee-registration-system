<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Employee Registration</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background:#f6f7fb; }
        .container { max-width: 700px; margin: 30px auto; background:white; padding:20px; border-radius:8px; box-shadow:0 6px 18px rgba(0,0,0,0.06); }
        label { display:block; margin-top:10px; font-weight:600; }
        input[type="text"], input[type="date"], input[type="password"], textarea, select { width:100%; padding:8px; box-sizing:border-box; margin-top:5px; }
        textarea { resize:vertical; min-height:80px; }
        .row { display:flex; gap:10px; }
        .row > div { flex:1; }
        .actions { margin-top:15px; }
        .error { color: #b00020; margin-top: 8px; }
        .success { color: green; margin-top: 8px; }
        button { background:#1a73e8; color:white; border:none; padding:8px 12px; border-radius:4px; cursor:pointer; }
    </style>
</head>
<body>
<div class="container">
    <h1>Register Employee</h1>

    <c:if test="${not empty error}">
        <div class="error"><c:out value="${error}" /></div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="success"><c:out value="${success}" /></div>
    </c:if>

    <form method="post" action="<c:url value='/register'/>">
        <label for="name">Full Name *</label>
        <input id="name" name="name" type="text" required />

        <div class="row">
            <div>
                <label for="dob">Date of Birth *</label>
                <input id="dob" name="dob" type="date" required />
            </div>
            <div>
                <label for="gender">Gender</label>
                <select id="gender" name="gender">
                    <option value="">-- Select --</option>
                    <option>Male</option>
                    <option>Female</option>
                    <option>Other</option>
                </select>
            </div>
        </div>

        <label for="address">Address</label>
        <textarea id="address" name="address"></textarea>

        <div class="row">
            <div>
                <label for="city">City</label>
                <input id="city" name="city" type="text" />
            </div>
            <div>
                <label for="state">State</label>
                <input id="state" name="state" type="text" />
            </div>
        </div>

        <label for="loginId">Login ID (username) *</label>
        <input id="loginId" name="loginId" type="text" required />

        <label for="password">Password *</label>
        <input id="password" name="password" type="password" required minlength="6" />

        <div class="actions">
            <button type="submit">Register</button>
            <button type="reset">Reset</button>
        </div>
    </form>

    <p style="margin-top:20px;">
        Already registered? <a href="<c:url value='/login'/>">Login here</a>.
    </p>
</div>
</body>
</html>
