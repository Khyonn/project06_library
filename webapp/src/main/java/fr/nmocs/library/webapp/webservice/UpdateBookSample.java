
package fr.nmocs.library.webapp.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour updateBookSample complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="updateBookSample"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bookSample" type="{http://webservice.library.nmocs.fr/}bookSample" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateBookSample", propOrder = {
    "bookSample"
})
public class UpdateBookSample {

    protected BookSample bookSample;

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

}
