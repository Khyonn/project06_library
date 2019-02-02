
package fr.nmocs.library.webapp.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.nmocs.library.webapp.webservice package. 
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

    private final static QName _CreateBook_QNAME = new QName("http://webservice.library.nmocs.fr/", "createBook");
    private final static QName _CreateBookResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "createBookResponse");
    private final static QName _CreateBookSample_QNAME = new QName("http://webservice.library.nmocs.fr/", "createBookSample");
    private final static QName _CreateBookSampleResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "createBookSampleResponse");
    private final static QName _FindBookByAuthor_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookByAuthor");
    private final static QName _FindBookByAuthorResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookByAuthorResponse");
    private final static QName _FindBookById_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookById");
    private final static QName _FindBookByIdResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookByIdResponse");
    private final static QName _FindBookByTitle_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookByTitle");
    private final static QName _FindBookByTitleResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookByTitleResponse");
    private final static QName _FindBookSampleByBookId_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookSampleByBookId");
    private final static QName _FindBookSampleByBookIdResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookSampleByBookIdResponse");
    private final static QName _FindBookSampleById_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookSampleById");
    private final static QName _FindBookSampleByIdResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findBookSampleByIdResponse");
    private final static QName _FindNotBorrowedBookSampleByBookId_QNAME = new QName("http://webservice.library.nmocs.fr/", "findNotBorrowedBookSampleByBookId");
    private final static QName _FindNotBorrowedBookSampleByBookIdResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findNotBorrowedBookSampleByBookIdResponse");
    private final static QName _UpdateBook_QNAME = new QName("http://webservice.library.nmocs.fr/", "updateBook");
    private final static QName _UpdateBookResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "updateBookResponse");
    private final static QName _UpdateBookSample_QNAME = new QName("http://webservice.library.nmocs.fr/", "updateBookSample");
    private final static QName _UpdateBookSampleResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "updateBookSampleResponse");
    private final static QName _LibraryWebserviceException_QNAME = new QName("http://webservice.library.nmocs.fr/", "LibraryWebserviceException");
    private final static QName _Token_QNAME = new QName("http://webservice.library.nmocs.fr/", "token");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.nmocs.library.webapp.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateBook }
     * 
     */
    public CreateBook createCreateBook() {
        return new CreateBook();
    }

    /**
     * Create an instance of {@link CreateBookResponse }
     * 
     */
    public CreateBookResponse createCreateBookResponse() {
        return new CreateBookResponse();
    }

    /**
     * Create an instance of {@link CreateBookSample }
     * 
     */
    public CreateBookSample createCreateBookSample() {
        return new CreateBookSample();
    }

    /**
     * Create an instance of {@link CreateBookSampleResponse }
     * 
     */
    public CreateBookSampleResponse createCreateBookSampleResponse() {
        return new CreateBookSampleResponse();
    }

    /**
     * Create an instance of {@link FindBookByAuthor }
     * 
     */
    public FindBookByAuthor createFindBookByAuthor() {
        return new FindBookByAuthor();
    }

    /**
     * Create an instance of {@link FindBookByAuthorResponse }
     * 
     */
    public FindBookByAuthorResponse createFindBookByAuthorResponse() {
        return new FindBookByAuthorResponse();
    }

    /**
     * Create an instance of {@link FindBookById }
     * 
     */
    public FindBookById createFindBookById() {
        return new FindBookById();
    }

    /**
     * Create an instance of {@link FindBookByIdResponse }
     * 
     */
    public FindBookByIdResponse createFindBookByIdResponse() {
        return new FindBookByIdResponse();
    }

    /**
     * Create an instance of {@link FindBookByTitle }
     * 
     */
    public FindBookByTitle createFindBookByTitle() {
        return new FindBookByTitle();
    }

    /**
     * Create an instance of {@link FindBookByTitleResponse }
     * 
     */
    public FindBookByTitleResponse createFindBookByTitleResponse() {
        return new FindBookByTitleResponse();
    }

    /**
     * Create an instance of {@link FindBookSampleByBookId }
     * 
     */
    public FindBookSampleByBookId createFindBookSampleByBookId() {
        return new FindBookSampleByBookId();
    }

    /**
     * Create an instance of {@link FindBookSampleByBookIdResponse }
     * 
     */
    public FindBookSampleByBookIdResponse createFindBookSampleByBookIdResponse() {
        return new FindBookSampleByBookIdResponse();
    }

    /**
     * Create an instance of {@link FindBookSampleById }
     * 
     */
    public FindBookSampleById createFindBookSampleById() {
        return new FindBookSampleById();
    }

    /**
     * Create an instance of {@link FindBookSampleByIdResponse }
     * 
     */
    public FindBookSampleByIdResponse createFindBookSampleByIdResponse() {
        return new FindBookSampleByIdResponse();
    }

    /**
     * Create an instance of {@link FindNotBorrowedBookSampleByBookId }
     * 
     */
    public FindNotBorrowedBookSampleByBookId createFindNotBorrowedBookSampleByBookId() {
        return new FindNotBorrowedBookSampleByBookId();
    }

    /**
     * Create an instance of {@link FindNotBorrowedBookSampleByBookIdResponse }
     * 
     */
    public FindNotBorrowedBookSampleByBookIdResponse createFindNotBorrowedBookSampleByBookIdResponse() {
        return new FindNotBorrowedBookSampleByBookIdResponse();
    }

    /**
     * Create an instance of {@link UpdateBook }
     * 
     */
    public UpdateBook createUpdateBook() {
        return new UpdateBook();
    }

    /**
     * Create an instance of {@link UpdateBookResponse }
     * 
     */
    public UpdateBookResponse createUpdateBookResponse() {
        return new UpdateBookResponse();
    }

    /**
     * Create an instance of {@link UpdateBookSample }
     * 
     */
    public UpdateBookSample createUpdateBookSample() {
        return new UpdateBookSample();
    }

    /**
     * Create an instance of {@link UpdateBookSampleResponse }
     * 
     */
    public UpdateBookSampleResponse createUpdateBookSampleResponse() {
        return new UpdateBookSampleResponse();
    }

    /**
     * Create an instance of {@link LibraryWebserviceException }
     * 
     */
    public LibraryWebserviceException createLibraryWebserviceException() {
        return new LibraryWebserviceException();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link BookSample }
     * 
     */
    public BookSample createBookSample() {
        return new BookSample();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "createBook")
    public JAXBElement<CreateBook> createCreateBook(CreateBook value) {
        return new JAXBElement<CreateBook>(_CreateBook_QNAME, CreateBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "createBookResponse")
    public JAXBElement<CreateBookResponse> createCreateBookResponse(CreateBookResponse value) {
        return new JAXBElement<CreateBookResponse>(_CreateBookResponse_QNAME, CreateBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBookSample }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateBookSample }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "createBookSample")
    public JAXBElement<CreateBookSample> createCreateBookSample(CreateBookSample value) {
        return new JAXBElement<CreateBookSample>(_CreateBookSample_QNAME, CreateBookSample.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBookSampleResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateBookSampleResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "createBookSampleResponse")
    public JAXBElement<CreateBookSampleResponse> createCreateBookSampleResponse(CreateBookSampleResponse value) {
        return new JAXBElement<CreateBookSampleResponse>(_CreateBookSampleResponse_QNAME, CreateBookSampleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookByAuthor }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookByAuthor }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookByAuthor")
    public JAXBElement<FindBookByAuthor> createFindBookByAuthor(FindBookByAuthor value) {
        return new JAXBElement<FindBookByAuthor>(_FindBookByAuthor_QNAME, FindBookByAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookByAuthorResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookByAuthorResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookByAuthorResponse")
    public JAXBElement<FindBookByAuthorResponse> createFindBookByAuthorResponse(FindBookByAuthorResponse value) {
        return new JAXBElement<FindBookByAuthorResponse>(_FindBookByAuthorResponse_QNAME, FindBookByAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookById }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookById")
    public JAXBElement<FindBookById> createFindBookById(FindBookById value) {
        return new JAXBElement<FindBookById>(_FindBookById_QNAME, FindBookById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookByIdResponse")
    public JAXBElement<FindBookByIdResponse> createFindBookByIdResponse(FindBookByIdResponse value) {
        return new JAXBElement<FindBookByIdResponse>(_FindBookByIdResponse_QNAME, FindBookByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookByTitle }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookByTitle }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookByTitle")
    public JAXBElement<FindBookByTitle> createFindBookByTitle(FindBookByTitle value) {
        return new JAXBElement<FindBookByTitle>(_FindBookByTitle_QNAME, FindBookByTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookByTitleResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookByTitleResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookByTitleResponse")
    public JAXBElement<FindBookByTitleResponse> createFindBookByTitleResponse(FindBookByTitleResponse value) {
        return new JAXBElement<FindBookByTitleResponse>(_FindBookByTitleResponse_QNAME, FindBookByTitleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookSampleByBookId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookSampleByBookId }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookSampleByBookId")
    public JAXBElement<FindBookSampleByBookId> createFindBookSampleByBookId(FindBookSampleByBookId value) {
        return new JAXBElement<FindBookSampleByBookId>(_FindBookSampleByBookId_QNAME, FindBookSampleByBookId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookSampleByBookIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookSampleByBookIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookSampleByBookIdResponse")
    public JAXBElement<FindBookSampleByBookIdResponse> createFindBookSampleByBookIdResponse(FindBookSampleByBookIdResponse value) {
        return new JAXBElement<FindBookSampleByBookIdResponse>(_FindBookSampleByBookIdResponse_QNAME, FindBookSampleByBookIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookSampleById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookSampleById }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookSampleById")
    public JAXBElement<FindBookSampleById> createFindBookSampleById(FindBookSampleById value) {
        return new JAXBElement<FindBookSampleById>(_FindBookSampleById_QNAME, FindBookSampleById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBookSampleByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindBookSampleByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findBookSampleByIdResponse")
    public JAXBElement<FindBookSampleByIdResponse> createFindBookSampleByIdResponse(FindBookSampleByIdResponse value) {
        return new JAXBElement<FindBookSampleByIdResponse>(_FindBookSampleByIdResponse_QNAME, FindBookSampleByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindNotBorrowedBookSampleByBookId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindNotBorrowedBookSampleByBookId }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findNotBorrowedBookSampleByBookId")
    public JAXBElement<FindNotBorrowedBookSampleByBookId> createFindNotBorrowedBookSampleByBookId(FindNotBorrowedBookSampleByBookId value) {
        return new JAXBElement<FindNotBorrowedBookSampleByBookId>(_FindNotBorrowedBookSampleByBookId_QNAME, FindNotBorrowedBookSampleByBookId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindNotBorrowedBookSampleByBookIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindNotBorrowedBookSampleByBookIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findNotBorrowedBookSampleByBookIdResponse")
    public JAXBElement<FindNotBorrowedBookSampleByBookIdResponse> createFindNotBorrowedBookSampleByBookIdResponse(FindNotBorrowedBookSampleByBookIdResponse value) {
        return new JAXBElement<FindNotBorrowedBookSampleByBookIdResponse>(_FindNotBorrowedBookSampleByBookIdResponse_QNAME, FindNotBorrowedBookSampleByBookIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "updateBook")
    public JAXBElement<UpdateBook> createUpdateBook(UpdateBook value) {
        return new JAXBElement<UpdateBook>(_UpdateBook_QNAME, UpdateBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "updateBookResponse")
    public JAXBElement<UpdateBookResponse> createUpdateBookResponse(UpdateBookResponse value) {
        return new JAXBElement<UpdateBookResponse>(_UpdateBookResponse_QNAME, UpdateBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBookSample }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateBookSample }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "updateBookSample")
    public JAXBElement<UpdateBookSample> createUpdateBookSample(UpdateBookSample value) {
        return new JAXBElement<UpdateBookSample>(_UpdateBookSample_QNAME, UpdateBookSample.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBookSampleResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateBookSampleResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "updateBookSampleResponse")
    public JAXBElement<UpdateBookSampleResponse> createUpdateBookSampleResponse(UpdateBookSampleResponse value) {
        return new JAXBElement<UpdateBookSampleResponse>(_UpdateBookSampleResponse_QNAME, UpdateBookSampleResponse.class, null, value);
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
