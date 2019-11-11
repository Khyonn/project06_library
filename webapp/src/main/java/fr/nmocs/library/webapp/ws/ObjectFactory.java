
package fr.nmocs.library.webapp.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.nmocs.library.webapp.ws package. 
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

    private final static QName _CreateUser_QNAME = new QName("http://webservice.library.nmocs.fr/", "createUser");
    private final static QName _CreateUserResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "createUserResponse");
    private final static QName _DowngradeAdminToBasicUser_QNAME = new QName("http://webservice.library.nmocs.fr/", "downgradeAdminToBasicUser");
    private final static QName _DowngradeAdminToBasicUserResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "downgradeAdminToBasicUserResponse");
    private final static QName _FindByEmail_QNAME = new QName("http://webservice.library.nmocs.fr/", "findByEmail");
    private final static QName _FindByEmailResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findByEmailResponse");
    private final static QName _FindById_QNAME = new QName("http://webservice.library.nmocs.fr/", "findById");
    private final static QName _FindByIdResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findByIdResponse");
    private final static QName _FindByName_QNAME = new QName("http://webservice.library.nmocs.fr/", "findByName");
    private final static QName _FindByNameResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "findByNameResponse");
    private final static QName _GrantAdminRightsToUser_QNAME = new QName("http://webservice.library.nmocs.fr/", "grantAdminRightsToUser");
    private final static QName _GrantAdminRightsToUserResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "grantAdminRightsToUserResponse");
    private final static QName _UpdateUser_QNAME = new QName("http://webservice.library.nmocs.fr/", "updateUser");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://webservice.library.nmocs.fr/", "updateUserResponse");
    private final static QName _LibraryWebserviceException_QNAME = new QName("http://webservice.library.nmocs.fr/", "LibraryWebserviceException");
    private final static QName _Token_QNAME = new QName("http://webservice.library.nmocs.fr/", "token");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.nmocs.library.webapp.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link DowngradeAdminToBasicUser }
     * 
     */
    public DowngradeAdminToBasicUser createDowngradeAdminToBasicUser() {
        return new DowngradeAdminToBasicUser();
    }

    /**
     * Create an instance of {@link DowngradeAdminToBasicUserResponse }
     * 
     */
    public DowngradeAdminToBasicUserResponse createDowngradeAdminToBasicUserResponse() {
        return new DowngradeAdminToBasicUserResponse();
    }

    /**
     * Create an instance of {@link FindByEmail }
     * 
     */
    public FindByEmail createFindByEmail() {
        return new FindByEmail();
    }

    /**
     * Create an instance of {@link FindByEmailResponse }
     * 
     */
    public FindByEmailResponse createFindByEmailResponse() {
        return new FindByEmailResponse();
    }

    /**
     * Create an instance of {@link FindById }
     * 
     */
    public FindById createFindById() {
        return new FindById();
    }

    /**
     * Create an instance of {@link FindByIdResponse }
     * 
     */
    public FindByIdResponse createFindByIdResponse() {
        return new FindByIdResponse();
    }

    /**
     * Create an instance of {@link FindByName }
     * 
     */
    public FindByName createFindByName() {
        return new FindByName();
    }

    /**
     * Create an instance of {@link FindByNameResponse }
     * 
     */
    public FindByNameResponse createFindByNameResponse() {
        return new FindByNameResponse();
    }

    /**
     * Create an instance of {@link GrantAdminRightsToUser }
     * 
     */
    public GrantAdminRightsToUser createGrantAdminRightsToUser() {
        return new GrantAdminRightsToUser();
    }

    /**
     * Create an instance of {@link GrantAdminRightsToUserResponse }
     * 
     */
    public GrantAdminRightsToUserResponse createGrantAdminRightsToUserResponse() {
        return new GrantAdminRightsToUserResponse();
    }

    /**
     * Create an instance of {@link UpdateUser }
     * 
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link LibraryWebserviceException }
     * 
     */
    public LibraryWebserviceException createLibraryWebserviceException() {
        return new LibraryWebserviceException();
    }

    /**
     * Create an instance of {@link UserDTO }
     * 
     */
    public UserDTO createUserDTO() {
        return new UserDTO();
    }

    /**
     * Create an instance of {@link AdminDTO }
     * 
     */
    public AdminDTO createAdminDTO() {
        return new AdminDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DowngradeAdminToBasicUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "downgradeAdminToBasicUser")
    public JAXBElement<DowngradeAdminToBasicUser> createDowngradeAdminToBasicUser(DowngradeAdminToBasicUser value) {
        return new JAXBElement<DowngradeAdminToBasicUser>(_DowngradeAdminToBasicUser_QNAME, DowngradeAdminToBasicUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DowngradeAdminToBasicUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "downgradeAdminToBasicUserResponse")
    public JAXBElement<DowngradeAdminToBasicUserResponse> createDowngradeAdminToBasicUserResponse(DowngradeAdminToBasicUserResponse value) {
        return new JAXBElement<DowngradeAdminToBasicUserResponse>(_DowngradeAdminToBasicUserResponse_QNAME, DowngradeAdminToBasicUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findByEmail")
    public JAXBElement<FindByEmail> createFindByEmail(FindByEmail value) {
        return new JAXBElement<FindByEmail>(_FindByEmail_QNAME, FindByEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findByEmailResponse")
    public JAXBElement<FindByEmailResponse> createFindByEmailResponse(FindByEmailResponse value) {
        return new JAXBElement<FindByEmailResponse>(_FindByEmailResponse_QNAME, FindByEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findById")
    public JAXBElement<FindById> createFindById(FindById value) {
        return new JAXBElement<FindById>(_FindById_QNAME, FindById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findByIdResponse")
    public JAXBElement<FindByIdResponse> createFindByIdResponse(FindByIdResponse value) {
        return new JAXBElement<FindByIdResponse>(_FindByIdResponse_QNAME, FindByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findByName")
    public JAXBElement<FindByName> createFindByName(FindByName value) {
        return new JAXBElement<FindByName>(_FindByName_QNAME, FindByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "findByNameResponse")
    public JAXBElement<FindByNameResponse> createFindByNameResponse(FindByNameResponse value) {
        return new JAXBElement<FindByNameResponse>(_FindByNameResponse_QNAME, FindByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantAdminRightsToUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "grantAdminRightsToUser")
    public JAXBElement<GrantAdminRightsToUser> createGrantAdminRightsToUser(GrantAdminRightsToUser value) {
        return new JAXBElement<GrantAdminRightsToUser>(_GrantAdminRightsToUser_QNAME, GrantAdminRightsToUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantAdminRightsToUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "grantAdminRightsToUserResponse")
    public JAXBElement<GrantAdminRightsToUserResponse> createGrantAdminRightsToUserResponse(GrantAdminRightsToUserResponse value) {
        return new JAXBElement<GrantAdminRightsToUserResponse>(_GrantAdminRightsToUserResponse_QNAME, GrantAdminRightsToUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LibraryWebserviceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "LibraryWebserviceException")
    public JAXBElement<LibraryWebserviceException> createLibraryWebserviceException(LibraryWebserviceException value) {
        return new JAXBElement<LibraryWebserviceException>(_LibraryWebserviceException_QNAME, LibraryWebserviceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.library.nmocs.fr/", name = "token")
    public JAXBElement<String> createToken(String value) {
        return new JAXBElement<String>(_Token_QNAME, String.class, null, value);
    }

}
