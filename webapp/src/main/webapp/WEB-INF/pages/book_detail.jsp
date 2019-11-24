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
	    	<span><s:text name="bookDetail.availableNumber"/> : <span><s:property value="book.reservationQueueInfos.AvailableSamplesNumber"/></span>
			<s:if test="isReservable">
				<br><span><s:text name="bookDetail.reservationinfos.canReserve"/></span>
				(<s:text name="bookDetail.reservationinfos.reservationNb"/> : <span><s:property value="book.reservationQueueInfos.reservers.size()"/></span> / <span><s:property value="book.reservationQueueInfos.queueMaxSize"/></span> <s:text name="bookDetail.reservationinfos.reservationMax"/>)<br>
				<s:text name="bookDetail.reservationinfos.availability.between"/> <s:date name="book.reservationQueueInfos.soonestAvailabilityDate.toGregorianCalendar()" format="dd/MM/yyyy" /> <s:text name="bookDetail.reservationinfos.availability.and"/> <s:date name="book.reservationQueueInfos.latestAvailabilityDate.toGregorianCalendar()" format="dd/MM/yyyy" /> 
				<s:a action="reservation-create" cssClass="float-right btn-primary">
					<s:param name="bookId" value="book.id"/>
					<s:text name="bookDetail.btn.reservation" />
				</s:a>
			</s:if>
			<s:if test="book.reservationQueueInfos.isAvailable">
				<s:text name="bookDetail.available"/>
			</s:if>
	    </main>
	    
   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
    </body>
</html>