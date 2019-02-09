<!DOCTYPE html>
<html lang="fr">
    <head>
   		<%@ include file="../_include/head.jsp" %>
    <link rel="stylesheet" href="assets/styles/login.css">
        <title><s:text name="website.title"/> - <s:text name="login.title"/></title>
    </head>

   	<body class="text-center">
		<s:form action="login" theme="simple" cssClass="form-login">
			<s:if test="hasActionErrors()">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<s:actionerror />
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</s:if>
			<h1 class="h3 mb-3 font-weight-normal">
				<s:text name="login.login" />
			</h1>
			<!-- EMAIL -->
			<s:label for="email" key="login.email" cssClass="sr-only" />
			<s:textfield cssClass="form-control" id="email" name="userEmail" requiredLabel="true" />
			<!-- PASSWORD -->
			<s:label for="password" key="login.password" cssClass="sr-only" />
			<s:password cssClass="form-control" id="password" name="userPassword" requiredLabel="true" />
			<!-- BOUTON -->
			<s:submit cssClass="btn btn-lg btn-primary btn-block" key="login.btn" />
			<br>
			<s:a action="signup">
				<s:text name="login.goSingup"></s:text>
			</s:a>
		</s:form>

   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
		<script>
   			<%-- Afin d'avoir des placeholder --%>
			$("#email").attr("placeholder", "<s:text name="login.email"/>").attr("type", "email");
			$("#password").attr("placeholder", "<s:text name="login.password"/>");
	   		<%-- Afin de rendre visuellement les champs 'required' --%>
			$("form input").attr("required", "required");
		</script>
    </body>
</html>