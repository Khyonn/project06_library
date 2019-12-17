
package fr.nmocs.library.loanperemptionwarn.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.nmocs.library.loanperemptionwarn.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetLoginToken_QNAME = new QName("http://webservice.library.nmocs.fr/", "getLoginToken");
    private final static QName _GetLoginTokenResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "getLoginTokenResponse");
    private final static QName _LibraryWebserviceException_QNAME = new QName("http://webservice.library.nmocs.fr/", "LibraryWebserviceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.nmocs.library.loanperemptionwarn.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLoginToken }
     * 
     */
    public GetLoginToken createGetLoginToken() {
        return new GetLoginToken();
    }

    /**
     * Create an instance of {@link GetLoginTokenResponse }
     * 
     */
    public GetLoginTokenResponse createGetLoginTokenResponse() {
        return new GetLoginTokenResponse();
    }

    /**
     * Create an instance of {@link LibraryWebserviceException }
     * 
     */
    public LibraryWebserviceException createLibraryWebserviceException() {
        return new LibraryWebserviceException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLoginToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "getLoginToken")
    public JAXBElement<GetLoginToken> createGetLoginToken(GetLoginToken value) {
        return new JAXBElement<GetLoginToken>(_GetLoginToken_QNAME, GetLoginToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLoginTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "getLoginTokenResponse")
    public JAXBElement<GetLoginTokenResponse> createGetLoginTokenResponse(GetLoginTokenResponse value) {
        return new JAXBElement<GetLoginTokenResponse>(_GetLoginTokenResponse_QNAME, GetLoginTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LibraryWebserviceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "LibraryWebserviceException")
    public JAXBElement<LibraryWebserviceException> createLibraryWebserviceException(LibraryWebserviceException value) {
        return new JAXBElement<LibraryWebserviceException>(_LibraryWebserviceException_QNAME, LibraryWebserviceException.class, null, value);
    }

}
