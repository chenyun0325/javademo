package distrservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT)
public interface IDispatcher {
	
	@WebMethod
	String doDispatcher(String transcode, String inputParams);

}
