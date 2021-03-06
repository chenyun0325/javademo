
package distrservice.client;

import distrservice.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IMyservice", targetNamespace = "http://distrservice/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IMyservice {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://distrservice/", className = "distrservice.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://distrservice/", className = "distrservice.AddResponse")
    public int add(
        @WebParam(name = "arg0", targetNamespace = "")
            int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
            int arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "minus", targetNamespace = "http://distrservice/", className = "distrservice.Minus")
    @ResponseWrapper(localName = "minusResponse", targetNamespace = "http://distrservice/", className = "distrservice.MinusResponse")
    public int minus(
        @WebParam(name = "arg0", targetNamespace = "")
            int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
            int arg1);

}
