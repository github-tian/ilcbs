
package cn.tzs.export.webservice;

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
@WebService(name = "IEpService", targetNamespace = "http://webservice.export.tzs.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IEpService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "exportE", targetNamespace = "http://webservice.export.tzs.cn/", className = "cn.tzs.export.webservice.ExportE")
    @ResponseWrapper(localName = "exportEResponse", targetNamespace = "http://webservice.export.tzs.cn/", className = "cn.tzs.export.webservice.ExportEResponse")
    public String exportE(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws Exception_Exception
    ;

}
