<!DOCTYPE html>
<html lang="fr">
    <head>
   		<%@ include file="../_include/head.jsp" %>
        <title><s:text name="website.title"/> - <s:text name="loans.title"/></title>
    </head>

   	<body>
	    <%@ include file="../_include/navbar.jsp" %>
	    <main class="container bg-white p-3">
	    	<h1><s:text name="loans.loans"/></h1>
			<s:if test="areLoansEmpty()">
		    	<p><s:text name="loans.noLoans"/></p>
			</s:if>
	    	<%-- Late loans --%>
			<s:if test="isLateLoansNotEmpty()">
				<div class="card border-danger mb-2">
					<div class="card-header font-weight-bold">
						<s:text name="loans.late" /> (<s:property value="lateLoans.size()"/>)
					</div>
					
					<ul class="list-group list-group-flush text-danger">
						<s:iterator value="lateLoans" var="loan">	           			
							<li class="list-group-item">
								<span class="font-weight-bold">[<s:text name="loan.startDate"/> : <s:date name="startDate.toGregorianCalendar()" format="dd/MM/yyyy" />]</span>
								<s:property value="bookSample.book.title"/> _ <span class="font-italic"><s:property value="bookSample.book.author"/></span>
								<s:if test="isLoanExtendable(#loan)">
									<s:a action="loan-extend" cssClass="float-right">
										<s:param name="loanToExtendId" value="id" />
										<s:text name="loans.extend"/>
									</s:a>
								</s:if>
								<s:else>
									<br>
									<span class="font-weight-bold float-right">
										!! <s:text name="loans.cannotExtend"/> !!
										[<s:text name="loans.loanNumber"/> #<s:property value="id"/>]
									</span>
								</s:else>
							</li>
						</s:iterator>
					</ul>
				</div>
    		</s:if>
	    	<%-- In progress loans --%>
			<s:if test="isInProgressLoansNotEmpty()">
				<div class="card border-info mb-2">
					<div class="card-header font-weight-bold">
						<s:text name="loans.pending" /> (<s:property value="inProgressLoans.size()"/>)
					</div>
					
					<ul class="list-group list-group-flush text-info">
						<s:iterator value="inProgressLoans" var="loan">	           			
							<li class="list-group-item">
								<span class="font-weight-bold">[<s:text name="loan.startDate"/> : <s:date name="startDate.toGregorianCalendar()" format="dd/MM/yyyy" />]</span>
								<s:property value="bookSample.book.title"/> _ <span class="font-italic"><s:property value="bookSample.book.author"/></span>
								<s:if test="isLoanExtendable(#loan)">
									<s:a action="loan-extend" cssClass="float-right">
										<s:param name="loanToExtendId" value="id"/>
										<s:text name="loans.extend" />
									</s:a>
								</s:if>
							</li>
						</s:iterator>
					</ul>
				</div>
    		</s:if>
	    	<%-- Returned loans --%>
			<s:if test="isReturnedLoansNotEmpty()">
				<div class="card border-success mb-2">
					<div class="card-header font-weight-bold">
						<s:text name="loans.returned" /> (<s:property value="returnedLoans.size()"/>)
					</div>
					
					<ul class="list-group list-group-flush text-success">
						<s:iterator value="returnedLoans">	           			
							<li class="list-group-item">
								<span class="font-weight-bold">
									[<s:text name="loan.startDate"/> : <s:date name="startDate.toGregorianCalendar()" format="dd/MM/yyyy" /> -
									<s:text name="loan.returnDate"/> : <s:date name="returnDate.toGregorianCalendar()" format="dd/MM/yyyy" />]</span>
								<s:property value="bookSample.book.title"/> _ <span class="font-italic"><s:property value="bookSample.book.author"/></span>
							</li>
						</s:iterator>
					</ul>
				</div>
    		</s:if>
	    </main>
	    
   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
    </body>
</html>