
package fr.nmocs.library.batch.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.nmocs.library.batch.webservice package. 
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

    private final static QName _CreateLoan_QNAME = new QName("http://webservice.library.nmocs.fr/", "createLoan");
    private final static QName _CreateLoanResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "createLoanResponse");
    private final static QName _FindByUserId_QNAME = new QName("http://webservice.library.nmocs.fr/", "findByUserId");
    private final static QName _FindByUserIdResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findByUserIdResponse");
    private final static QName _FindLoanById_QNAME = new QName("http://webservice.library.nmocs.fr/", "findLoanById");
    private final static QName _FindLoanByIdResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findLoanByIdResponse");
    private final static QName _FindNotReturned_QNAME = new QName("http://webservice.library.nmocs.fr/", "findNotReturned");
    private final static QName _FindNotReturnedResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findNotReturnedResponse");
    private final static QName _UpdateLoan_QNAME = new QName("http://webservice.library.nmocs.fr/", "updateLoan");
    private final static QName _UpdateLoanResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "updateLoanResponse");
    private final static QName _LibraryWebserviceException_QNAME = new QName("http://webservice.library.nmocs.fr/", "LibraryWebserviceException");
    private final static QName _Token_QNAME = new QName("http://webservice.library.nmocs.fr/", "token");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.nmocs.library.batch.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateLoan }
     * 
     */
    public CreateLoan createCreateLoan() {
        return new CreateLoan();
    }

    /**
     * Create an instance of {@link CreateLoanResponse }
     * 
     */
    public CreateLoanResponse createCreateLoanResponse() {
        return new CreateLoanResponse();
    }

    /**
     * Create an instance of {@link FindByUserId }
     * 
     */
    public FindByUserId createFindByUserId() {
        return new FindByUserId();
    }

    /**
     * Create an instance of {@link FindByUserIdResponse }
     * 
     */
    public FindByUserIdResponse createFindByUserIdResponse() {
        return new FindByUserIdResponse();
    }

    /**
     * Create an instance of {@link FindLoanById }
     * 
     */
    public FindLoanById createFindLoanById() {
        return new FindLoanById();
    }

    /**
     * Create an instance of {@link FindLoanByIdResponse }
     * 
     */
    public FindLoanByIdResponse createFindLoanByIdResponse() {
        return new FindLoanByIdResponse();
    }

    /**
     * Create an instance of {@link FindNotReturned }
     * 
     */
    public FindNotReturned createFindNotReturned() {
        return new FindNotReturned();
    }

    /**
     * Create an instance of {@link FindNotReturnedResponse }
     * 
     */
    public FindNotReturnedResponse createFindNotReturnedResponse() {
        return new FindNotReturnedResponse();
    }

    /**
     * Create an instance of {@link UpdateLoan }
     * 
     */
    public UpdateLoan createUpdateLoan() {
        return new UpdateLoan();
    }

    /**
     * Create an instance of {@link UpdateLoanResponse }
     * 
     */
    public UpdateLoanResponse createUpdateLoanResponse() {
        return new UpdateLoanResponse();
    }

    /**
     * Create an instance of {@link LibraryWebserviceException }
     * 
     */
    public LibraryWebserviceException createLibraryWebserviceException() {
        return new LibraryWebserviceException();
    }

    /**
     * Create an instance of {@link Loan }
     * 
     */
    public Loan createLoan() {
        return new Loan();
    }

    /**
     * Create an instance of {@link BookSample }
     * 
     */
    public BookSample createBookSample() {
        return new BookSample();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateLoan }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateLoan }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "createLoan")
    public JAXBElement<CreateLoan> createCreateLoan(CreateLoan value) {
        return new JAXBElement<CreateLoan>(_CreateLoan_QNAME, CreateLoan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateLoanResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateLoanResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "createLoanResponse")
    public JAXBElement<CreateLoanResponse> createCreateLoanResponse(CreateLoanResponse value) {
        return new JAXBElement<CreateLoanResponse>(_CreateLoanResponse_QNAME, CreateLoanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByUserId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindByUserId }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findByUserId")
    public JAXBElement<FindByUserId> createFindByUserId(FindByUserId value) {
        return new JAXBElement<FindByUserId>(_FindByUserId_QNAME, FindByUserId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByUserIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindByUserIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findByUserIdResponse")
    public JAXBElement<FindByUserIdResponse> createFindByUserIdResponse(FindByUserIdResponse value) {
        return new JAXBElement<FindByUserIdResponse>(_FindByUserIdResponse_QNAME, FindByUserIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindLoanById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindLoanById }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findLoanById")
    public JAXBElement<FindLoanById> createFindLoanById(FindLoanById value) {
        return new JAXBElement<FindLoanById>(_FindLoanById_QNAME, FindLoanById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindLoanByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindLoanByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findLoanByIdResponse")
    public JAXBElement<FindLoanByIdResponse> createFindLoanByIdResponse(FindLoanByIdResponse value) {
        return new JAXBElement<FindLoanByIdResponse>(_FindLoanByIdResponse_QNAME, FindLoanByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindNotReturned }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindNotReturned }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findNotReturned")
    public JAXBElement<FindNotReturned> createFindNotReturned(FindNotReturned value) {
        return new JAXBElement<FindNotReturned>(_FindNotReturned_QNAME, FindNotReturned.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindNotReturnedResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindNotReturnedResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findNotReturnedResponse")
    public JAXBElement<FindNotReturnedResponse> createFindNotReturnedResponse(FindNotReturnedResponse value) {
        return new JAXBElement<FindNotReturnedResponse>(_FindNotReturnedResponse_QNAME, FindNotReturnedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateLoan }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateLoan }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "updateLoan")
    public JAXBElement<UpdateLoan> createUpdateLoan(UpdateLoan value) {
        return new JAXBElement<UpdateLoan>(_UpdateLoan_QNAME, UpdateLoan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateLoanResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateLoanResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "updateLoanResponse")
    public JAXBElement<UpdateLoanResponse> createUpdateLoanResponse(UpdateLoanResponse value) {
        return new JAXBElement<UpdateLoanResponse>(_UpdateLoanResponse_QNAME, UpdateLoanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LibraryWebserviceException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LibraryWebserviceException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "LibraryWebserviceException")
    public JAXBElement<LibraryWebserviceException> createLibraryWebserviceException(LibraryWebserviceException value) {
        return new JAXBElement<LibraryWebserviceException>(_LibraryWebserviceException_QNAME, LibraryWebserviceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "token")
    public JAXBElement<String> createToken(String value) {
        return new JAXBElement<String>(_Token_QNAME, String.class, null, value);
    }

}
