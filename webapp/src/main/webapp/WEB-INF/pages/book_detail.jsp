<!DOCTYPE html>
<html lang="fr">
    <head>
   		<%@ include file="../_include/head.jsp" %>
        <title><s:text name="website.title"/> - <s:text name="bookDetail.title"/></title>
    </head>

   	<body>
	    <%@ include file="../_include/navbar.jsp" %>
	    <main class="container bg-white p-3">
	    	<h2><s:property value="book.title"/> _ <span class="font-italic"><s:property value="book.author"/></span></h2>
	    	<p>
	    		<s:property value="book.summary"/>
	    	</p>
	    	<span><s:text name="bookDetail.availableNumber"/> : <s:property value="sampleNumber"/></span>
	    </main>
	    
   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
    </body>
</html>