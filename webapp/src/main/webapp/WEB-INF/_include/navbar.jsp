<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-brown">
    <s:a cssClass="navbar-brand" action="index"><s:text name="common.nav.brand"></s:text></s:a>
    <!-- BOUTON COLLAPSE -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse"
        aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarCollapse">
        <!-- mr-auto place des marges automatiques à droite -->
        <ul class="navbar-nav mr-auto">
            <li class="nav-item" id="index-link">
            	<s:a cssClass="nav-link" action="index">
            		<s:text name="common.nav.indexLink"/>
           		</s:a>
            </li>
            <li class="nav-item" id="book-link">
            	<s:a cssClass="nav-link" action="book-search">
            		<s:text name="common.nav.booksLink"/>
           		</s:a>
            </li>
        </ul>

        <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a id="account-opt" class="nav-item nav-link dropdown-toggle mr-md-2" href="#" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    <s:if test="isUserConnected">
                    	<s:property value="userName"/>
                    </s:if>
                    <s:else>
                     <s:text name="common.nav.accountLink"/>
                    </s:else>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="account-opt">
					<s:if test="isUserConnected">
			        	<s:a action="account-consult" cssClass="dropdown-item"><s:text name="common.nav.userProfileLink"/></s:a>
			        	<s:a action="loan-consult" cssClass="dropdown-item"><s:text name="common.nav.userLoanLink"/></s:a>
			        	<div class="dropdown-divider"></div>
			        	<s:a action="logout" cssClass="dropdown-item"><s:text name="common.nav.logoutLink"/></s:a>
					</s:if>
					<s:else>
						<s:a action="login" cssClass="dropdown-item"><s:text name="common.nav.loginLink"/></s:a>
						<s:a action="signup" cssClass="dropdown-item"><s:text name="common.nav.signupLink"/></s:a>
					</s:else>
                </div>
            </li>
        </ul>
    </div>
</nav>