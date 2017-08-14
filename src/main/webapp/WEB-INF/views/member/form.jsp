<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"  %>
<body>
<div class="container">
	<h3>メンバー登録画面</h3>
	<div class="span8">
		<div class="row">
		<form:form modelAttribute="memberForm" action="${pageContext.request.contextPath}/member/create">
			<table class="table table-striped">
			  <tr>
			    <th>
			     	 氏名
			    </th>
			    <td>
			    	<form:input path="name"  placeholder="Name"/><form:errors path="name" cssStyle="color:red" element="span"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      	メールアドレス
			    </th>
			    <td>
			    	<form:input path="mailAddress" placeholder="Email"/><form:errors path="mailAddress" cssStyle="color:red" element="span"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 パスワード
			    </th>
			    <td>
			    	<form:password path="password" placeholder="Password"/><form:errors path="password" cssStyle="color:red" element="span"/>
			    	<span style="color:red"><c:out value="${duplicateMailAddress}"/></span>
			    </td>
			  </tr>
			  <tr>
			  	<th>
			  		パスワード再入力
			  	</th>
			  	<td>
			  		<form:password path="rePassword" placeholder="Passwordを再入力してください"/><form:errors path="rePassword" cssStyle="color:red" element="span"/>
			  	</td>
			  </tr>
			  <tr>
			  	<td></td>
			    <td>
					<input class="btn" type="submit" value="登録">
			    </td>
			  </tr>
			</table>
		  </form:form>
		</div>
	</div>
</div>
</body>
</html>