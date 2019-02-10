<!DOCTYPE html>
<html lang="fr">
    <head>
   		<%@ include file="../_include/head.jsp" %>
        <title><s:text name="website.title"/> - <s:text name="bookSearch.title"/></title>
    </head>

   	<body>
	    <%@ include file="../_include/navbar.jsp" %>
	    <main class="container bg-white p-3">
	    	<s:form action="book-search" theme="simple">
		        <h1 class="h3 mb-3 font-weight-normal"><s:text name="bookSearch.bookSearch" /></h1>
		        <!-- TITLE -->
				<div class="row">
					<div class="col-md-6 form-group">
				        <s:label for="title" key="bookSearch.titleFilter" />
				        <s:textfield cssClass="form-control" id="title" name="titleFilter" />
					</div>
				</div>
		        <!-- AUTHOR -->
				<div class="row">
					<div class="col-md-6 form-group">
				        <s:label for="author" key="bookSearch.authorFilter" />
				        <s:textfield cssClass="form-control" id="author" name="authorFilter" />
					</div>
				</div>
		        <!-- BOUTON -->
				<div class="row">
					<div class="offset-md-6 col-md-6 offset-lg-8 col-lg-4">
				        <s:submit cssClass="btn btn-lg btn-brown btn-block" key="bookSearch.btn" />
					</div>
				</div>
			</s:form>
			<s:if test="isBookListNotEmpty()">
				<h3 class="mt-3"><s:text name="bookSearch.result"/> (<s:property value="bookList.size()"/>)</h3>
				<ul class="list-group list-group-flush">
					<s:iterator value="bookList">
					  <li class="list-group-item">
						<s:a action="book-detail">
							<s:param name="bookId" value="id"/>
							<s:property value="title"/> - <s:property value="author"/>
						</s:a>
					  </li>
					</s:iterator>
				</ul>
			</s:if>
	    </main>
	    
   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
    </body>
</html>