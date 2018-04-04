<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login successfull</title>
</head>
<body>
	<div>
		<%
			String user = null;
			String sessionID = null;
			String userName = null;

			/*
			if(session.getAttribute("userSession") == null){
				response.sendRedirect("login.html");
			} else {
				user = (String) session.getAttribute("userSession");
			}
			*/

			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("userCookie")) userName = cookie.getValue();
					if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
				}
			}else{
				sessionID = session.getId();
			}
		%>
		<div>
			<h3>Hi <%=userName %>, Login successful. Your Session ID=<%=sessionID %></h3>
		</div>

		<div>
			User=<%=user %><br>
			<!-- need to encode all the URLs where we want session information to be passed -->
			<a href="<%=response.encodeURL("CheckoutPage.jsp") %>">Checkout page!</a>
			<form action="/biglog/LogoutServlet" method="post">
				<input type="submit" value="Logout" />
			</form>
		</div>
	</div>
</body>
</html>