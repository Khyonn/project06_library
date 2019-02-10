<!DOCTYPE html>
<html lang="fr">
    <head>
   		<%@ include file="../_include/head.jsp" %>
    	<link rel="stylesheet" href="assets/styles/signup.css">
        <title><s:text name="website.title"/> - <s:text name="signup.title"/></title>
    </head>

   	<body>
	    <%@ include file="../_include/navbar.jsp" %>
	    <div class="container text-center">
			<s:form action="signup" theme="simple" cssClass="form-signup">
				<s:if test="hasActionErrors()">
				    <div class="alert alert-danger alert-dismissible fade show" role="alert">		
				    	<s:actionerror/>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</s:if>	
		        <h1 class="h3 mb-3 font-weight-normal"><s:text name="signup.signup" /></h1>
		        <!-- FIRSTNAME -->
		        <s:label for="firstname" key="signup.firstname" cssClass="sr-only" />
		        <s:textfield cssClass="form-control" id="firstname" name="user.firstName" requiredLabel="true" />
		        <!-- LASTNAME -->
		        <s:label for="lastname" key="signup.lastname" cssClass="sr-only" />
		        <s:textfield cssClass="form-control" id="lastname" name="user.lastName" requiredLabel="true" />
		        <!-- EMAIL -->
		        <s:label for="email" key="signup.email" cssClass="sr-only" />
		        <s:textfield cssClass="form-control" id="email" name="user.email" requiredLabel="true" />
		        <!-- CONFIRM-EMAIL -->
		        <s:label for="emailconfirm" key="signup.confirmEmail" cssClass="sr-only" />
		        <s:textfield cssClass="form-control" id="emailconfirm" name="verifEmail" requiredLabel="true" />
		        <!-- PASSWORD -->
		        <s:label for="password" key="signup.password"  cssClass="sr-only" />
		        <s:password cssClass="form-control" id="password" name="user.password" requiredLabel="true" />
		        <!-- CONFIRM-PASSWORD -->
		        <s:label for="passwordconfirm" key="signup.confirmPassword"  cssClass="sr-only" />
		        <s:password cssClass="form-control" id="passwordconfirm" name="verifPassword" requiredLabel="true" />
		        <!-- BOUTON -->
		        <s:submit cssClass="btn btn-lg btn-brown btn-block" key="signup.btn" />
		        <br>
				<s:a action="login"><s:text name="signup.goLogin"></s:text></s:a>
			</s:form>
	    </div>

   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
		<script>
			<%-- Afin d'avoir des placeholder sur les inputs--%>
			$("#firstname").attr("placeholder", "<s:text name="signup.firstname"/>");
			$("#lastname").attr("placeholder", "<s:text name="signup.lastname"/>");
			$("#email").attr("placeholder", "<s:text name="signup.email"/>").attr("type", "email");
			$("#password").attr("placeholder", "<s:text name="signup.password"/>");
			$("#emailconfirm").attr("placeholder", "<s:text name="signup.confirmEmail"/>").attr("type", "email");
			$("#passwordconfirm").attr("placeholder", "<s:text name="signup.confirmPassword"/>");
			<%-- Afin d'avoir de rendre les champs 'required'--%>
			$("form input").attr("required", "required");
		</script>
    </body>
</html>