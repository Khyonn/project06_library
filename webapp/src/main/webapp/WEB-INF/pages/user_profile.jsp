<!DOCTYPE html>
<html lang="fr">
    <head>
   		<%@ include file="../_include/head.jsp" %>
        <title><s:text name="website.title"/> - <s:text name="account.title"/></title>
    </head>

   	<body>
	    <%@ include file="../_include/navbar.jsp" %>
	    <main class="container bg-white p-3">
	    	<s:form action="account-edit" theme="simple">
				<s:if test="hasActionErrors()">
				    <div class="alert alert-danger alert-dismissible fade show" role="alert">		
				    	<s:actionerror/>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</s:if>
				<s:if test="hasActionMessages()">
				    <div class="alert alert-info alert-dismissible fade show" role="alert">		
				    	<s:actionmessage/>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</s:if>
		        <h1 class="h3 mb-3 font-weight-normal"><s:text name="account.account" /></h1>
		        <!-- FIRSTNAME -->
				<div class="row">
					<div class="col-md-6 form-group">
				        <s:label for="firstname" key="account.firstname" />
				        <s:textfield cssClass="form-control" id="firstname" name="user.firstName" requiredLabel="true" />
					</div>
				</div>
		        <!-- LASTNAME -->
				<div class="row">
					<div class="col-md-6 form-group">
				        <s:label for="lastname" key="account.lastname" />
				        <s:textfield cssClass="form-control" id="lastname" name="user.lastName" requiredLabel="true" />
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-md-6 form-group">
				        <!-- EMAIL -->
				        <s:label for="email" key="account.email" />
				        <s:textfield cssClass="form-control" id="email" name="user.email" requiredLabel="true" />
					</div>
					<div class="col-12 col-md-6 form-group">
				        <!-- CONFIRM-EMAIL -->
				        <s:label for="emailconfirm" key="account.confirmEmail" />
				        <s:textfield cssClass="form-control" id="emailconfirm" name="verifEmail" requiredLabel="true" />
					</div>
				</div>
				<div class="row">
					<div class="col-12 col-md-6 form-group">
				        <!-- PASSWORD -->
				        <s:label for="password" key="account.password" />
				        <s:password cssClass="form-control" id="password" name="user.password" requiredLabel="true" />
					</div>
					<div class="col-12 col-md-6 form-group">
				        <!-- CONFIRM-PASSWORD -->
				        <s:label for="passwordconfirm" key="account.confirmPassword" />
				        <s:password cssClass="form-control" id="passwordconfirm" name="verifPassword" requiredLabel="true" />
					</div>
				</div>
		        <!-- BOUTON -->
				<div class="row">
					<div class="offset-md-6 col-md-6 offset-lg-8 col-lg-4">
				        <s:submit cssClass="btn btn-lg btn-brown btn-block" key="account.btn" />
					</div>
				</div>
			</s:form>
	    </main>
	    
   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
		<script>
	   		<%-- Afin de rendre visuellement les champs 'required' --%>
			$("form input").attr("required", "required");
		</script>
    </body>
</html>