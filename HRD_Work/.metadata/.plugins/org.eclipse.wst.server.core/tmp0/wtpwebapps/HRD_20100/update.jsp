<%@page import="member.MemberVO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="./header.jsp" %>
	<%
		int custno = Integer.parseInt(request.getParameter("custno"));
		out.print("@@"+custno);
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.selectMember(custno);
	%>
	<form action="updateProc.jsp">
		<h2>홈쇼핑 회원정보 수정</h2>
		<table border="1px">
			<tr>
				<th>회원번호</th>
				<td><input type="text" name="custno" value="<%= vo.getCustno() %>"></td>
			</tr>
			<tr>
				<th>회원 성명</th>
				<td><input type="text" name="custno" value="<%= vo.getCustname() %>"></td>
			</tr>
			<tr>
				<td>통신사[SK,KT,LG]</td>
				<td>
				<%
					if(vo.getAddress().equals("SK")){
				%>
				
					<input type="radio" name="address" value="SK" checked> SK
					<input type="radio" name="address" value="KT"> KT
					<input type="radio" name="address" value="LG"> LG
				<%
					} else if (vo.getAddress().equals("KT")){
				%>
					<input type="radio" name="address" value="SK"> SK
					<input type="radio" name="address" value="KT" checked> KT
					<input type="radio" name="address" value="LG"> LG
					
				<%
					} else {
				%>
					<input type="radio" name="address" value="SK"> SK
					<input type="radio" name="address" value="KT"> KT
					<input type="radio" name="address" value="LG" checked> LG
					
				<%
					}
				%>
				</td>
			</tr>
			<tr>
				<th>회원주소</th>
				<td><input type="text" name="custno" value="<%= vo.getAddress() %>"></td>
			</tr>
			<tr>
				<th>가입일자</th>
				<td><input type="text" name="custno" value="<%= vo.getJoindate() %>"></td>
			</tr>
			<tr>
				<td>고객등급[A:VIP B:일반 C:직원]</td>
				<td>
					<select name="grade">
						<%
							if(vo.getCity().equals("A")) {
						%>
							<option value="A" selected>A
							<option value="B">B
							<option value="C">C
						<%
							} else {		
						%>
							<option value="A">A
							<option value="B" selected>B
							<option value="C">C
						<%
							}		
						%>
							<option value="A">A
							<option value="B">B
							<option value="C" selected>C
					</select>
				</td>
			</tr>
			<tr>
				<th>도시코드</th>
				<td><input type="text" name="city" value="<%= vo.getCity() %>"></td>
			</tr>
			<tr>
				<td><input type="submit" value="수정"></td>
				<td><a href="#">조회</a></td>
			</tr>
		</table>
	</form>
	
	<%@ include file="./footer.jsp" %>
</body>
</html>