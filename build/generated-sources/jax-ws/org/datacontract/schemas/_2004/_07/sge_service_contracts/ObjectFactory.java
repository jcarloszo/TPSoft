
package org.datacontract.schemas._2004._07.sge_service_contracts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.sge_service_contracts package. 
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

    private final static QName _Autorizacion_QNAME = new QName("http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", "Autorizacion");
    private final static QName _AutorizacionSign_QNAME = new QName("http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", "Sign");
    private final static QName _AutorizacionToken_QNAME = new QName("http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", "Token");
    private final static QName _AutorizacionError_QNAME = new QName("http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", "Error");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.sge_service_contracts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Autorizacion }
     * 
     */
    public Autorizacion createAutorizacion() {
        return new Autorizacion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Autorizacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", name = "Autorizacion")
    public JAXBElement<Autorizacion> createAutorizacion(Autorizacion value) {
        return new JAXBElement<Autorizacion>(_Autorizacion_QNAME, Autorizacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", name = "Sign", scope = Autorizacion.class)
    public JAXBElement<String> createAutorizacionSign(String value) {
        return new JAXBElement<String>(_AutorizacionSign_QNAME, String.class, Autorizacion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", name = "Token", scope = Autorizacion.class)
    public JAXBElement<String> createAutorizacionToken(String value) {
        return new JAXBElement<String>(_AutorizacionToken_QNAME, String.class, Autorizacion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", name = "Error", scope = Autorizacion.class)
    public JAXBElement<String> createAutorizacionError(String value) {
        return new JAXBElement<String>(_AutorizacionError_QNAME, String.class, Autorizacion.class, value);
    }

}
