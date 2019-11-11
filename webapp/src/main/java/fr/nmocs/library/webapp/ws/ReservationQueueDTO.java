
package fr.nmocs.library.webapp.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="isReservable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="latestAvailabilityDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="queueSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="reservable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="reservationNb" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
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
    "isReservable",
    "latestAvailabilityDate",
    "queueSize",
    "reservable",
    "reservationNb",
    "soonestAvailabilityDate"
})
public class ReservationQueueDTO {

    protected boolean isReservable;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar latestAvailabilityDate;
    protected Integer queueSize;
    protected boolean reservable;
    protected Integer reservationNb;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar soonestAvailabilityDate;

    /**
     * Obtient la valeur de la propriété isReservable.
     * 
     */
    public boolean isIsReservable() {
        return isReservable;
    }

    /**
     * Définit la valeur de la propriété isReservable.
     * 
     */
    public void setIsReservable(boolean value) {
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
     * Obtient la valeur de la propriété queueSize.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQueueSize() {
        return queueSize;
    }

    /**
     * Définit la valeur de la propriété queueSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQueueSize(Integer value) {
        this.queueSize = value;
    }

    /**
     * Obtient la valeur de la propriété reservable.
     * 
     */
    public boolean isReservable() {
        return reservable;
    }

    /**
     * Définit la valeur de la propriété reservable.
     * 
     */
    public void setReservable(boolean value) {
        this.reservable = value;
    }

    /**
     * Obtient la valeur de la propriété reservationNb.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReservationNb() {
        return reservationNb;
    }

    /**
     * Définit la valeur de la propriété reservationNb.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReservationNb(Integer value) {
        this.reservationNb = value;
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
