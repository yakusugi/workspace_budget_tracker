<%@ page language="java" contentType="text/html;charset=Windows-31J"%>
<%@page import="java.sql.*"%>

<html>
<head>
<title></title>
</head>
<body>
	<br>

	<%
		ResultSet rset = (ResultSet) request.getAttribute("kekka");
	%>
	<table border="1">
		<tr bgcolor="#cccccc">
			<td><b>ID</b></td>
			<td><b>���O</b></td>
			<td><b>����</b></td>
			<td><b>���ДN</b></td>
			<td><b>�Z��</b></td>
		</tr>
		<%
			while (rset.next()) {
		%>
		<tr>
			<td><%=rset.getString(1)%></td>
			<td><%=rset.getString(2)%></td>
			<td><%=rset.getString(3)%></td>
			<td><%=rset.getString(4)%></td>
			<td><%=rset.getString(5)%></td>
		</tr>
		<%
			}
		%>
	</table>

	<br>

	<a href="./index.jsp">�g�b�v�ɖ߂�</a>

</body>
</html>
