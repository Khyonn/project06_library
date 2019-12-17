
package fr.nmocs.library.loanperemptionwarn.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour reservationQueueDTO complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="reservationQueueDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="availableSamplesNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="borrowers" type="{http://webservice.library.nmocs.fr/}userDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="isAvailable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isReservable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="latestAvailabilityDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="queueMaxSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="reservers" type="{http://webservice.library.nmocs.fr/}userDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="soonestAvailabilityDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservationQueueDTO", propOrder = {
    "availableSamplesNumber",
    "borrowers",
    "isAvailable",
    "isReservable",
    "latestAvailabilityDate",
    "queueMaxSize",
    "reservers",
    "soonestAvailabilityDate"
})
public class ReservationQueueDTO {

    protected Integer availableSamplesNumber;
    @XmlElement(nillable = true)
    protected List<UserDTO> borrowers;
    protected Boolean isAvailable;
    protected Boolean isReservable;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar latestAvailabilityDate;
    protected Integer queueMaxSize;
    @XmlElement(nillable = true)
    protected List<UserDTO> reservers;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar soonestAvailabilityDate;

    /**
     * Obtient la valeur de la propriété availableSamplesNumber.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAvailableSamplesNumber() {
        return availableSamplesNumber;
    }

    /**
     * Définit la valeur de la propriété availableSamplesNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAvailableSamplesNumber(Integer value) {
        this.availableSamplesNumber = value;
    }

    /**
     * Gets the value of the borrowers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBorrowers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserDTO }
     * 
     * 
     */
    public List<UserDTO> getBorrowers() {
        if (borrowers == null) {
            borrowers = new ArrayList<UserDTO>();
        }
        return this.borrowers;
    }

    /**
     * Obtient la valeur de la propriété isAvailable.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAvailable() {
        return isAvailable;
    }

    /**
     * Définit la valeur de la propriété isAvailable.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAvailable(Boolean value) {
        this.isAvailable = value;
    }

    /**
     * Obtient la valeur de la propriété isReservable.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReservable() {
        return isReservable;
    }

    /**
     * Définit la valeur de la propriété isReservable.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReservable(Boolean value) {
        this.isReservable = value;
    }

    /**
     * Obtient la valeur de la propriété latestAvailabilityDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestAvailabilityDate() {
        return latestAvailabilityDate;
    }

    /**
     * Définit la valeur de la propriété latestAvailabilityDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestAvailabilityDate(XMLGregorianCalendar value) {
        this.latestAvailabilityDate = value;
    }

    /**
     * Obtient la valeur de la propriété queueMaxSize.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQueueMaxSize() {
        return queueMaxSize;
    }

    /**
     * Définit la valeur de la propriété queueMaxSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQueueMaxSize(Integer value) {
        this.queueMaxSize = value;
    }

    /**
     * Gets the value of the reservers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserDTO }
     * 
     * 
     */
    public List<UserDTO> getReservers() {
        if (reservers == null) {
            reservers = new ArrayList<UserDTO>();
        }
        return this.reservers;
    }

    /**
     * Obtient la valeur de la propriété soonestAvailabilityDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSoonestAvailabilityDate() {
        return soonestAvailabilityDate;
    }

    /**
     * Définit la valeur de la propriété soonestAvailabilityDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSoonestAvailabilityDate(XMLGregorianCalendar value) {
        this.soonestAvailabilityDate = value;
    }

}
