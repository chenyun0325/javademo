
package distrservice.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MyserviceImpService", targetNamespace = "http://localhost/client", wsdlLocation = "http://localhost:8081/IMyservice?wsdl")
public class MyserviceImpService
    extends Service
{

    private final static URL MYSERVICEIMPSERVICE_WSDL_LOCATION;
    private final static WebServiceException MYSERVICEIMPSERVICE_EXCEPTION;
    private final static QName MYSERVICEIMPSERVICE_QNAME = new QName("http://localhost/client", "MyserviceImpService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/IMyservice?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MYSERVICEIMPSERVICE_WSDL_LOCATION = url;
        MYSERVICEIMPSERVICE_EXCEPTION = e;
    }

    public MyserviceImpService() {
        super(__getWsdlLocation(), MYSERVICEIMPSERVICE_QNAME);
    }

    public MyserviceImpService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MYSERVICEIMPSERVICE_QNAME, features);
    }

    public MyserviceImpService(URL wsdlLocation) {
        super(wsdlLocation, MYSERVICEIMPSERVICE_QNAME);
    }

    public MyserviceImpService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MYSERVICEIMPSERVICE_QNAME, features);
    }

    public MyserviceImpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MyserviceImpService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IMyservice
     */
    @WebEndpoint(name = "IMysPort")
    public IMyservice getIMysPort() {
        return super.getPort(new QName("http://localhost/client", "IMysPort"), IMyservice.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IMyservice
     */
    @WebEndpoint(name = "IMysPort")
    public IMyservice getIMysPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://localhost/client", "IMysPort"), IMyservice.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MYSERVICEIMPSERVICE_EXCEPTION!= null) {
            throw MYSERVICEIMPSERVICE_EXCEPTION;
        }
        return MYSERVICEIMPSERVICE_WSDL_LOCATION;
    }

}
