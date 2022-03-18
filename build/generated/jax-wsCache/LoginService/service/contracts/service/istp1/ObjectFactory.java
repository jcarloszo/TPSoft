
package service.contracts.service.istp1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.datacontract.schemas._2004._07.sge_service_contracts.Autorizacion;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service.contracts.service.istp1 package. 
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

    private final static QName _SolicitarAutorizacionResponseSolicitarAutorizacionResult_QNAME = new QName("http://ISTP1.Service.Contracts.Service", "SolicitarAutorizacionResult");
    private final static QName _SolicitarAutorizacionCodigo_QNAME = new QName("http://ISTP1.Service.Contracts.Service", "codigo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service.contracts.service.istp1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SolicitarAutorizacion }
     * 
     */
    public SolicitarAutorizacion createSolicitarAutorizacion() {
        return new SolicitarAutorizacion();
    }

    /**
     * Create an instance of {@link SolicitarAutorizacionResponse }
     * 
     */
    public SolicitarAutorizacionResponse createSolicitarAutorizacionResponse() {
        return new SolicitarAutorizacionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Autorizacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ISTP1.Service.Contracts.Service", name = "SolicitarAutorizacionResult", scope = SolicitarAutorizacionResponse.class)
    public JAXBElement<Autorizacion> createSolicitarAutorizacionResponseSolicitarAutorizacionResult(Autorizacion value) {
        return new JAXBElement<Autorizacion>(_SolicitarAutorizacionResponseSolicitarAutorizacionResult_QNAME, Autorizacion.class, SolicitarAutorizacionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ISTP1.Service.Contracts.Service", name = "codigo", scope = SolicitarAutorizacion.class)
    public JAXBElement<String> createSolicitarAutorizacionCodigo(String value) {
        return new JAXBElement<String>(_SolicitarAutorizacionCodigo_QNAME, String.class, SolicitarAutorizacion.class, value);
    }

}
