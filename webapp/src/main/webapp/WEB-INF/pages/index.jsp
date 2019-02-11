<!DOCTYPE html>
<html lang="fr">
    <head>
   		<%@ include file="../_include/head.jsp" %>
        <title><s:text name="website.title"/> - <s:text name="index.title"/></title>
    </head>

   	<body>
	    <%@ include file="../_include/navbar.jsp" %>
	    <main class="container bg-white p-3">
	    	<h2>Vous vous trouvez sur la page d'accueil</h2>
	    	<p class="text-justify">
	    		Ce site vous permet d'éditer votre profil<s:if test="isUserConnected"> (<s:a action="account-consult">lien</s:a>)</s:if>,
	    		de consulter vos prêts (passés, en cours, en retard et de les prolonger<s:if test="isUserConnected"> <s:a action="loan-consult">lien</s:a></s:if>)
	    		ou de chercher vos prochaines lectures (<s:a action="book-search">lien</s:a>)
	    	</p>
	    	<hr>
	    	<h4>Une fois n'est pas coutume...</h4>
	    	<p class="text-justify">
	    		Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec fermentum eros et lobortis gravida.
	    		Phasellus consequat placerat eros non blandit. Quisque eget mi nulla. Vivamus aliquam est sed aliquet congue.
	    		Suspendisse lorem dolor, euismod eget felis non, gravida consequat massa. Pellentesque efficitur mauris non sagittis tincidunt.
	    		Ut nec tortor sodales nisl blandit gravida. In nec justo in libero congue feugiat.
	    		Donec pulvinar odio at orci auctor, eget venenatis orci pellentesque. Proin a mi a lacus aliquet elementum fringilla ut sapien.
	    		Fusce venenatis lorem non massa efficitur, et vehicula orci porttitor.
	    		Sed arcu ex, luctus sit amet ultrices et, convallis nec lorem.
	    		Sed augue lorem, pellentesque a volutpat vitae, facilisis ut tellus.
	    		Etiam diam elit, commodo eu ipsum et, pretium efficitur augue.
	    		Donec mattis pulvinar rutrum. Vestibulum non quam convallis, euismod enim a, fringilla nulla.
	    	</p>
	    	<p class="text-justify">
	    		Aliquam erat volutpat. Nunc laoreet purus quis metus tempor, id sagittis dui condimentum.
	    		Maecenas vehicula lacinia ex, pharetra accumsan nulla efficitur ac. Nunc a lorem suscipit, interdum dui eu, ornare turpis.
	    		Praesent tincidunt, lectus ac volutpat pharetra, eros nisl porttitor arcu, a imperdiet odio sem nec ipsum.
	    		Sed tincidunt diam ac mi fringilla, eu venenatis risus condimentum. In egestas ullamcorper diam sed rhoncus. Donec porttitor vitae ex sed rhoncus.
	    		Praesent eu dictum libero. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eget ipsum scelerisque, euismod sapien vel, vestibulum ligula.
	    	</p>
	    	<p class="text-justify">
	    		Nullam eu condimentum massa. Etiam placerat enim eget molestie facilisis. Vestibulum sapien ligula, sodales ac tincidunt ac, mollis in enim.
	    		Suspendisse lacinia metus nec quam congue volutpat. Pellentesque est erat, pretium sit amet lorem non, euismod auctor lacus.
	    		Sed rutrum sit amet turpis non vehicula. Mauris mauris augue, pulvinar vel ante non, fringilla auctor tortor.
	    		Quisque vel dui vestibulum nisi sollicitudin sagittis. Nullam purus nisi, consequat sit amet arcu eget, elementum tincidunt nisl.
	    		Aliquam in fringilla mi. Suspendisse ultricies erat laoreet, varius libero sit amet, hendrerit leo.
	    		Vivamus hendrerit ligula id ligula pulvinar, at ullamcorper eros venenatis. Praesent vehicula eget ipsum eu ultrices.
	    	</p>
	    	<p class="text-justify">
	    		Proin vitae laoreet sapien. Aliquam varius venenatis nisl non semper. Duis viverra tellus ut purus bibendum, ac bibendum nunc lobortis.
	    		Vivamus varius, dui vel semper ultricies, felis libero sagittis ligula, at hendrerit nisl est quis tellus. Praesent quis neque diam.
	    		Morbi tempor diam vel mi vehicula, et placerat felis lacinia. Aenean leo dolor, scelerisque sed ipsum sit amet, fermentum fringilla tortor.
	    		Quisque augue eros, lacinia tempus placerat sed, placerat in arcu. Nam posuere facilisis turpis, id lobortis ligula.
	    		In ornare, dui eu vulputate pharetra, ante lorem dapibus neque, in bibendum dolor velit vitae felis. Morbi at feugiat lectus, eu rhoncus nunc.
	    		Phasellus massa diam, sodales eget magna quis, pulvinar iaculis enim. Praesent quam velit, tempus at interdum quis, imperdiet vel libero.
	    		Integer nec rhoncus nibh. Vestibulum leo risus, fermentum sit amet sagittis vitae, sagittis molestie elit.
	    		Ut sed nunc vel risus laoreet consectetur vel eu lectus.
	    	</p>
	    </main>
	    
   		<%@ include file="../_include/bootstrap_scripts.jsp" %>
    </body>
</html>