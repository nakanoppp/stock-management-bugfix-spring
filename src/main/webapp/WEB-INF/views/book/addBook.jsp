<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"  %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<body>
<div class="container">
	<security:authentication property="principal.member.name" />さん　こんにちは！<br>
	<a href="${pageContext.request.contextPath}/logout/sessionInvalidate">ログアウト</a>
	<h3>書籍情報登録</h3>
	<div class="span8">
		<div class="row">
			<table class="table table-striped">
			<form:form modelAttribute="addBookForm" action="${pageContext.request.contextPath}/book/add" enctype="multipart/form-data" method="post">
		  <tr>
			    <th>
			      書籍名
			    </th>
			    <td>
			      <form:input path="name"/>
			      <form:errors path="name" cssStyle="color:red" element="div"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      著者
			    </th>
			    <td>
			      <form:input path="author"/>
			      <form:errors path="author" cssStyle="color:red" element="div"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      出版社
			    </th>
			    <td>
			      <form:input path="publisher"/>
			      <form:errors path="publisher" cssStyle="color:red" element="div"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      価格
			    </th>
			    <td>
			      <form:input path="price" pattern="^[0-9]"/>円
			      <form:errors path="price" cssStyle="color:red" element="div"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			   ISBNコード
			    </th>
			    <td>
			      <form:input path="isbncode"/>
			      <form:errors path="isbncode" cssStyle="color:red" element="div"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      発売日
			    </th>
			    <td>
			      <form:input path="saledate" pattern="[0-9]{8}" />
			      <form:errors path="saledate" cssStyle="color:red" element="div"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      説明
			    </th>
			    <td>
			      <form:input path="explanation"/>
			      <form:errors path="explanation" cssStyle="color:red" element="div"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      画像
			    </th>
			    <td>
			      <input type="file" name="imgFile">
			      <div><c:out value="${imgFileError}"/></div>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      在庫数
			    </th>
			    <td>
			      <form:input path="stock"/>
			      <form:errors path="stock" cssStyle="color:red" element="div"/>
			    </td>
			  </tr>
			  <tr>
			  	<td colspan="2">
			  		<input type="submit" value="登録">
			  	</td>
			  </tr>
			  </form:form>
			  <tr>
			  	<td colspan="2">
			  		<a href="${pageContext.request.contextPath}/book/list">書籍一覧に戻る</a>
			  	</td>
			  </tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>