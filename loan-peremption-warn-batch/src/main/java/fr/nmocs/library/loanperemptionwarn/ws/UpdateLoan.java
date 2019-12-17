
package fr.nmocs.library.loanperemptionwarn.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour updateLoan complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="updateLoan"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="loan" type="{http://webservice.library.nmocs.fr/}loanDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateLoan", propOrder = {
    "loan"
})
public class UpdateLoan {

    protected LoanDTO loan;

    /**
     * Obtient la valeur de la propriété loan.
     * 
     * @return
     *     possible object is
     *     {@link LoanDTO }
     *     
     */
    public LoanDTO getLoan() {
        return loan;
    }

    /**
     * Définit la valeur de la propriété loan.
     * 
     * @param value
     *     allowed object is
     *     {@link LoanDTO }
     *     
     */
    public void setLoan(LoanDTO value) {
        this.loan = value;
    }

}
