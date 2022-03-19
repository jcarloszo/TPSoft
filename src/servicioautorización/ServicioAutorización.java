/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioautorización;

import fev1.dif.afip.gov.ar.AlicIva;
import fev1.dif.afip.gov.ar.ArrayOfAlicIva;
import fev1.dif.afip.gov.ar.ArrayOfCbteAsoc;
import fev1.dif.afip.gov.ar.ArrayOfFECAEDetRequest;
import fev1.dif.afip.gov.ar.ArrayOfTributo;
import fev1.dif.afip.gov.ar.CbteAsoc;
import fev1.dif.afip.gov.ar.FEAuthRequest;
import fev1.dif.afip.gov.ar.FECAEAGetResponse;
import fev1.dif.afip.gov.ar.FECAECabRequest;
import fev1.dif.afip.gov.ar.FECAEDetRequest;
import fev1.dif.afip.gov.ar.FECAERequest;
import fev1.dif.afip.gov.ar.FECAEResponse;
import fev1.dif.afip.gov.ar.FEParamGetTiposMonedas;
import fev1.dif.afip.gov.ar.FERecuperaLastCbteResponse;
import fev1.dif.afip.gov.ar.Tributo;
import org.datacontract.schemas._2004._07.sge_service_contracts.Autorizacion;

/**
 *
 * @author ZURITA
 */
public class ServicioAutorización {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //LLAMO AL SERVICIO DE AUTORIZACION
        Autorizacion autorizacion = solicitarAutorizacion("5947C49C-B34E-4845-A6B8-C3E3637A5332");
        System.out.println("CUIT: "+autorizacion.getCuit());
        System.out.println("Sign: "+autorizacion.getSign().getValue());
        System.out.println("Token: "+autorizacion.getToken().getValue());
        
        //LLAMO AL SERVICIO DE AFIP Y PIDO EL ULTIMO COMPROBANTE AUTORIZADO
        FEAuthRequest auth = new FEAuthRequest();
        auth.setCuit(autorizacion.getCuit());
        auth.setSign(autorizacion.getSign().getValue());
        auth.setToken(autorizacion.getToken().getValue());
        
        FERecuperaLastCbteResponse ultimoComp = feCompUltimoAutorizado(auth, 12, 1);
        System.out.print("cbte numero: " + ultimoComp.getCbteNro());
        System.out.print("\ncbte tipo: " + ultimoComp.getCbteTipo());
        
        //LLAMO AL SERVICIO DE AFIP PARA AUTORIZAR UNA VENTA
        FECAERequest request = new FECAERequest();
        FECAECabRequest feCabReq = new FECAECabRequest();
        feCabReq.setPtoVta(12);
        feCabReq.setCbteTipo(1);
        feCabReq.setCantReg(1);
        request.setFeCabReq(feCabReq);
        
        FECAEDetRequest feDetReq = new FECAEDetRequest();
        feDetReq.setConcepto(1);
        feDetReq.setDocTipo(80);
        feDetReq.setDocNro(20111111112L);
        feDetReq.setCbteDesde(ultimoComp.getCbteNro()+1);
        feDetReq.setCbteHasta(ultimoComp.getCbteNro()+1);
        feDetReq.setCbteFch("20220318");
        feDetReq.setImpTotal(184.05);
        /*
        184.05 =
        150 de impuesto neto
        7.8 de impuesto tributario
        26.5 de iva total
        */
        feDetReq.setImpTotConc(0);
        feDetReq.setImpNeto(150);
        feDetReq.setImpOpEx(0);
        feDetReq.setImpIVA(26.25);
        feDetReq.setImpTrib(7.8);
        feDetReq.setMonId("PES");
        feDetReq.setMonCotiz(1);
        
        //DETALLE DE IMP TRIBUTARIO
        Tributo tributo = new Tributo();
        short idTrib = 99;
        tributo.setId(idTrib);
        tributo.setDesc("Desc de prueba");
        tributo.setBaseImp(150);
        tributo.setAlic(5.2);
        tributo.setImporte(7.8);
        
        ArrayOfTributo arrayOfTributo = new ArrayOfTributo();
        arrayOfTributo.getTributo().add(tributo);
        feDetReq.setTributos(arrayOfTributo);
        
        //DETALLE DE ALICUOTAS IVA
        AlicIva alicuota1 = new AlicIva();
        alicuota1.setId(5);
        alicuota1.setBaseImp(100);
        alicuota1.setImporte(21);
        AlicIva alicuota2 = new AlicIva();
        alicuota2.setId(4);
        alicuota2.setBaseImp(50);
        alicuota2.setImporte(5.25);
        
        ArrayOfAlicIva arrayOfAlicIva = new ArrayOfAlicIva();
        arrayOfAlicIva.getAlicIva().add(alicuota1);
        arrayOfAlicIva.getAlicIva().add(alicuota2);
        feDetReq.setIva(arrayOfAlicIva);
        
        //COMPROBANTES RELACIONADOS
        /*
        ArrayOfCbteAsoc arrayOfCbteAsoc = new ArrayOfCbteAsoc();
        
        CbteAsoc cbteAsoc = new CbteAsoc();
        cbteAsoc.setTipo(1);
        cbteAsoc.setPtoVta(12);
        cbteAsoc.setNro(1L);
        
        arrayOfCbteAsoc.getCbteAsoc().add(cbteAsoc);
        feDetReq.setCbtesAsoc(arrayOfCbteAsoc);
        */

        
        //AGREGO EL DETALLE DE COMPROBANTE AL LOTE
        ArrayOfFECAEDetRequest arrayOfFeDetReq = new ArrayOfFECAEDetRequest();
        arrayOfFeDetReq.getFECAEDetRequest().add(feDetReq);
        request.setFeDetReq(arrayOfFeDetReq);
        
        
        
        //LLAMO A FECAESOLICITAR
        FECAEResponse fecaeResponse = fecaeSolicitar(auth, request);
        //System.out.println("\n"+fecaeResponse.getErrors());
        System.out.println("\n"+fecaeResponse.getFeCabResp().getResultado());
        System.out.println("\n"+fecaeResponse.getFeDetResp().getFECAEDetResponse().get(0).getObservaciones().getObs().get(0).getMsg());
        
    }

    private static Autorizacion solicitarAutorizacion(java.lang.String codigo) {
        org.tempuri.LoginService service = new org.tempuri.LoginService();
        org.tempuri.ILoginService port = service.getSGEAuthService();
        return port.solicitarAutorizacion(codigo);
    }

    private static FERecuperaLastCbteResponse feCompUltimoAutorizado(fev1.dif.afip.gov.ar.FEAuthRequest auth, int ptoVta, int cbteTipo) {
        fev1.dif.afip.gov.ar.Service service = new fev1.dif.afip.gov.ar.Service();
        fev1.dif.afip.gov.ar.ServiceSoap port = service.getServiceSoap();
        return port.feCompUltimoAutorizado(auth, ptoVta, cbteTipo);
    }

    private static FECAEResponse fecaeSolicitar(fev1.dif.afip.gov.ar.FEAuthRequest auth, fev1.dif.afip.gov.ar.FECAERequest feCAEReq) {
        fev1.dif.afip.gov.ar.Service service = new fev1.dif.afip.gov.ar.Service();
        fev1.dif.afip.gov.ar.ServiceSoap port = service.getServiceSoap();
        return port.fecaeSolicitar(auth, feCAEReq);
    }
        
}
