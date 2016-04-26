package distrservice;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(endpointInterface="distrservice.IDispatcher")
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT)
public class DispatcherImp implements IDispatcher {

	@Override
	public String doDispatcher(String transcode,String inputParams) {
		// TODO Auto-generated method stub
		String[] parms=inputParams.split("&");
		Map<String, Object> map=new HashMap<String, Object>();
		for (int i = 0; i < parms.length; i++) {
			String   param=parms[i];
			String[] op=param.split("=");
			map.put(op[0], op[1]);
		}
		/**
		 * switch
		 * case
		 */
		IProcessor processor=new Processor830001();
		
		//String transcode=(String) map.get("transCode");
		//System.err.println(transcode);
		String flag=processor.init(inputParams);
		
		String result=processor.doProcessor(transcode, map);
		
		return result;
	}

}
