<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background:#f6f7fb; }
        .container { max-width: 500px; margin: 40px auto; background:white; padding:24px; border-radius:8px; box-shadow:0 6px 18px rgba(0,0,0,0.08); }
        label { display:block; margin-top:10px; font-weight:600; }
        input[type="text"], input[type="password"] { width:100%; padding:8px; box-sizing:border-box; margin-top:5px; }
        .actions { margin-top:15px; }
        .error { color: #b00020; margin-top: 8px; }
        a { color:#1a73e8; }
        button { background:#1a73e8; color:white; border:none; padding:8px 12px; border-radius:4px; cursor:pointer; }
    </style>
</head>
<body>
<div class="container">
    <h1>Login</h1>

		<c:if test="${not empty error}">
			<div class="error">
				<c:out value="${error}" />
			</div>
		</c:if>

		<c:if test="${not empty error}">
			<script>
				(function() {
					var msg = '<c:out value="${fn:escapeXml(error)}" />';
					msg = msg.replace(/&amp;/g, '&'); 
					alert(msg);
					var e = document.getElementById('loginId');
					if (e)
						e.focus();
				})();
			</script>
		</c:if>


		<form method="post" action="<c:url value='/login'/>">
        <label for="loginId">Login ID</label>
        <input id="loginId" name="loginId" type="text" required />

        <label for="password">Password</label>
        <input id="password" name="password" type="password" required />

        <div class="actions">
            <button type="submit">Login</button>
        </div>
    </form>

    <p style="margin-top:20px;">
        Not registered? <a href="<c:url value='/registration'/>">Register here</a>.
    </p>
</div>
</body>
</html>
