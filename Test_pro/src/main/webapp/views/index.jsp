<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/views/include/loginheader.jsp" %>
		<div class="simple-page-form animated flipInY" id="login-form">
	<h4 class="form-title m-b-xl text-center">Sign In With Your Infinity Account</h4>
	<form method="post" action="loginForm.jsp">
		<div class="form-group">
			<input id="id" name="id" type="text" class="form-control" placeholder="ID">
		</div>

		<div class="form-group">
			<input id="pw" name="pw" type="password" class="form-control" placeholder="Password">
		</div>

		<div class="form-group m-b-xl">
			<div class="checkbox checkbox-primary">
				<input type="checkbox" id="keep_me_logged_in"/>
				<label for="keep_me_logged_in">Keep me signed in</label>
			</div>
		</div>
		<input type="submit" class="btn btn-primary" value="로그인">
	</form>
</div><!-- #login-form -->
<div class="simple-page-footer">
	<p><a href="password-forget.html">FORGOT YOUR PASSWORD ?</a></p>
	<p>
		<small>계정이 없으신가요?</small>
		<a href="signup.jsp">계정 만들기</a>
	</p>
</div><!-- .simple-page-footer -->
<%@include file="/views/include/loginfooter.jsp" %>
<script>

	
</script>