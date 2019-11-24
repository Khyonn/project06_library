<!DOCTYPE html>
<html lang="fr">
    <head>
   		<%@ include file="../_include/head.jsp" %>
        <title><s:text name="website.title"/> - <s:text name="reservations.title"/></title>
    </head>

   	<body>
	    <%@ include file="../_include/navbar.jsp" %>
	    <main class="container bg-white p-3">
	    	<h1><s:text name="reservations.title"/></h1>
			<s:if test="reservations.isEmpty()">
		    	<p><s:text name="reservations.notfound"/></p>
			</s:if>
			<s:if test="!reservations.isEmpty()">
				<div class="card border-info mb-2">
					<div class="card-header font-weight-bold">
						<s:text name="reservations.reservationNb" /> : <s:property value="reservations.size()"/>
					</div>
					
					<ul class="list-group list-group-flush text-info">
						<s:iterator value="reservations">	           			
							<li class="list-group-item">
								<span class="font-weight-bold"><s:property value="book.title"/> _ <s:property value="book.author"/></span>
								<s:a action="reservation-delete" cssClass="float-right btn-danger">
									<s:param name="bookId" value="book.id"/>
									<s:text name="reservations.btn.delete" />
								</s:a>
							</li>
						</s:iterator>
					</ul>
				</div>
    		</s:if>
	    </main>
	    
   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
    </body>
</html>