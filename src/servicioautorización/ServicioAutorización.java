/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioautorización;

import fev1.dif.afip.gov.ar.ArrayOfFECAEDetRequest;
import fev1.dif.afip.gov.ar.FEAuthRequest;
import fev1.dif.afip.gov.ar.FECAEAGetResponse;
import fev1.dif.afip.gov.ar.FECAECabRequest;
import fev1.dif.afip.gov.ar.FECAEDetRequest;
import fev1.dif.afip.gov.ar.FECAERequest;
import fev1.dif.afip.gov.ar.FECAEResponse;
import fev1.dif.afip.gov.ar.FEParamGetTiposMonedas;
import fev1.dif.afip.gov.ar.FERecuperaLastCbteResponse;
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
        
        FERecuperaLastCbteResponse ultimoComp = feCompUltimoAutorizado(auth, 12, 6);
        System.out.print("cbte numero: " + ultimoComp.getCbteNro());
        System.out.print("\ncbte tipo: " + ultimoComp.getCbteTipo());
        
        //LLAMO AL SERVICIO DE AFIP PARA AUTORIZAR UNA VENTA
        FECAERequest request = new FECAERequest();
        FECAECabRequest feCabReq = new FECAECabRequest();
        feCabReq.setPtoVta(12);
        feCabReq.setCbteTipo(6);
        feCabReq.setCantReg(5);
        request.setFeCabReq(feCabReq);
        
        ArrayOfFECAEDetRequest arrayOfFeDetReq = new ArrayOfFECAEDetRequest();
        
        FECAEDetRequest feDetReq = new FECAEDetRequest();
        feDetReq.setConcepto(1);
        feDetReq.setDocTipo(86);
        feDetReq.setDocNro(20417369253L);
        feDetReq.setCbteDesde(1);
        feDetReq.setCbteHasta(99999999);
        feDetReq.setImpTotal(5000);
        feDetReq.setImpTotConc(3000);
        feDetReq.setImpNeto(2000);
        feDetReq.setImpOpEx(1000);
        feDetReq.setImpIVA(100);
        feDetReq.setImpTrib(200);
        feDetReq.setMonId("PES");
        feDetReq.setMonCotiz(1);
        
        arrayOfFeDetReq.getFECAEDetRequest().add(feDetReq);
        
        request.setFeDetReq(arrayOfFeDetReq);
        
        
        
        FECAEResponse fecaeResponse = fecaeSolicitar(auth, request);
        System.out.println("\n"+fecaeResponse.getFeCabResp().getResultado());
        System.out.println("\nProbando GIT nuevamente");
        
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
