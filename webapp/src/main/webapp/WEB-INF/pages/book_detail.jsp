<!DOCTYPE html>
<html lang="fr">
    <head>
   		<%@ include file="../_include/head.jsp" %>
        <title><s:text name="website.title"/> - <s:text name="bookDetail.title"/></title>
    </head>

   	<body>
	    <%@ include file="../_include/navbar.jsp" %>
	    <main class="container bg-white p-3">
            <!-- TITLE -->
            <h2><s:property value="book.title"/> _ <span class="font-italic"><s:property value="book.author"/></span></h2>
            <!-- DESCRIPTION -->
            <p>
	    		<s:property value="book.summary"/>
            </p>
            <!-- AVALIBILITY -->
	    	<p>
                <%-- NOMBRE D'EXEMPLAIRE DISPONIBLE --%>
	    		<span><s:text name="bookDetail.availableNumber"/> : <s:property value="book.reservationQueueInfos.availableSamplesNumber"/></span><br>
				
				<%-- LE LIVRE EST DISPONIBLE --%>
				<s:if test="book.reservationQueueInfos.isAvailable">
                    <span><s:text name="bookDetail.available"/></span><br>
				</s:if>
                
				<%-- LE LIVRE EST RESERVABLE --%>
				<s:if test="isBookReservable">
                    <span><s:text name="bookDetail.reservationinfos.canReserve"/></span>
				</s:if>
				<%-- SINON --%>
				<s:else>
                    <span>
                        <s:text name="bookDetail.reservationinfos.cannotReserve"/> (
                        <%-- L'UTILISATEUR L'A DEJA RESERVE --%>
                        <s:if test="doesUserReservingBook">
                            <s:text name="bookDetail.reservationinfos.userReservingBook" />
                        </s:if>
                        <%-- OU IL L'EMPRUNTE ACTUELLEMENT --%>
                        <s:elseif test="doesUserBorrowingBook">
                            <s:text name="bookDetail.reservationinfos.userBorrowingBook" />
                        </s:elseif>
                        <%-- OU LA FILE DE RESERVATION EST PLEINE --%>
                        <s:else>
                            <s:text name="bookDetail.reservationinfos.reservationNb"/> : 
                            <s:property value="book.reservationQueueInfos.reservers.size()"/> / 
                            <s:property value="book.reservationQueueInfos.queueMaxSize"/> <s:text name="bookDetail.reservationinfos.reservationMax"/>
                        </s:else>)
                    </span>
                </s:else>
            </p>
            <s:if test="isBookReservable">
                <s:if test="!book.reservationQueueInfos.isAvailable">
                    <!-- RESERVATION -->
                    <p>
                        <%-- ESTIMATION DE LA DISPONIBILITE --%>
                        <s:text name="bookDetail.reservationinfos.availability.between"/> <s:date name="book.reservationQueueInfos.soonestAvailabilityDate.toGregorianCalendar()" format="dd/MM/yyyy" /> <s:text name="bookDetail.reservationinfos.availability.and"/> <s:date name="book.reservationQueueInfos.latestAvailabilityDate.toGregorianCalendar()" format="dd/MM/yyyy" /> 
                    </p>
                </s:if>
				<s:form action="reservation-create" theme="simple" cssClass="text-right">
					<input type="hidden" name="bookId" value="<s:property value="book.id"/>">
					<s:submit cssClass="btn btn-primary" key="bookDetail.btn.reservation"></s:submit>
				</s:form>
            </s:if>
	    </main>
	    
   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
    </body>
</html>