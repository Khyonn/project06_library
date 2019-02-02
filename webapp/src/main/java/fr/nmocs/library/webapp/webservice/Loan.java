
package fr.nmocs.library.webapp.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour loan complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="loan"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bookSample" type="{http://webservice.library.nmocs.fr/}bookSample" minOccurs="0"/&gt;
 *         &lt;element name="borrower" type="{http://webservice.library.nmocs.fr/}user" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="prolongationNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="returnDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loan", propOrder = {
    "bookSample",
    "borrower",
    "id",
    "prolongationNumber",
    "returnDate",
    "startDate"
})
public class Loan {

    protected BookSample bookSample;
    protected User borrower;
    protected Integer id;
    protected Integer prolongationNumber;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar returnDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;

    /**
     * Obtient la valeur de la propriété bookSample.
     * 
     * @return
     *     possible object is
     *     {@link BookSample }
     *     
     */
    public BookSample getBookSample() {
        return bookSample;
    }

    /**
     * Définit la valeur de la propriété bookSample.
     * 
     * @param value
     *     allowed object is
     *     {@link BookSample }
     *     
     */
    public void setBookSample(BookSample value) {
        this.bookSample = value;
    }

    /**
     * Obtient la valeur de la propriété borrower.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getBorrower() {
        return borrower;
    }

    /**
     * Définit la valeur de la propriété borrower.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setBorrower(User value) {
        this.borrower = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété prolongationNumber.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProlongationNumber() {
        return prolongationNumber;
    }

    /**
     * Définit la valeur de la propriété prolongationNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProlongationNumber(Integer value) {
        this.prolongationNumber = value;
    }

    /**
     * Obtient la valeur de la propriété returnDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReturnDate() {
        return returnDate;
    }

    /**
     * Définit la valeur de la propriété returnDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReturnDate(XMLGregorianCalendar value) {
        this.returnDate = value;
    }

    /**
     * Obtient la valeur de la propriété startDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Définit la valeur de la propriété startDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

}
