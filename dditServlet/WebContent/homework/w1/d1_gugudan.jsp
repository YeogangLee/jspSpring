<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 숙제 - 2021.06.28(d1)</title>
</head>
<body>
	<table>
		<thead>
			<tr>
<!-- 			<th>i단</th> -->
				<th>
				<%  for(int i=2; i<10; i++) { %>
						<%=i %>단 ------&emsp;
				<%	}  %>
				</th>
			</tr>
		</thead>
		<tbody>
			<%  for(int i=2; i<10; i++) { %>
			<tr>
<!-- 			<td>i * j = i*j</td> -->
				<td>
				<%  for(int j=2; j<10; j++) { %>
					<%=j %> x <%=i %> = <%=j*i %>
					<%  if(j*i < 10) { %>
							&emsp;	
					<%  }else if(j*i < 100) { %>
							&nbsp;
					<%  } %>
				<%  } %>
				</td>
			</tr>
			<%  } %>
		</tbody>
	</table>
</body>
</html>