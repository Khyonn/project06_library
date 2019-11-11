
package fr.nmocs.library.webapp.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour createBookSample complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="createBookSample"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bookSample" type="{http://webservice.library.nmocs.fr/}bookSampleDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createBookSample", propOrder = {
    "bookSample"
})
public class CreateBookSample {

    protected BookSampleDTO bookSample;

    /**
     * Obtient la valeur de la propriété bookSample.
     * 
     * @return
     *     possible object is
     *     {@link BookSampleDTO }
     *     
     */
    public BookSampleDTO getBookSample() {
        return bookSample;
    }

    /**
     * Définit la valeur de la propriété bookSample.
     * 
     * @param value
     *     allowed object is
     *     {@link BookSampleDTO }
     *     
     */
    public void setBookSample(BookSampleDTO value) {
        this.bookSample = value;
    }

}
