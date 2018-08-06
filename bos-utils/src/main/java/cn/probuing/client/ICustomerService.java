package cn.probuing.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2018-08-06T16:32:09.440+08:00
 * Generated source version: 2.4.2
 */
@WebService(targetNamespace = "http://service.bos_crm.probuing.cn/", name = "ICustomerService")
//@XmlSeeAlso({ObjectFactory.class})
public interface ICustomerService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.bos_crm.probuing.cn/", className = "cn.probuing.client.FindAll")
    @WebMethod
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.bos_crm.probuing.cn/", className = "cn.probuing.client.FindAllResponse")
    public java.util.List<Customer> findAll();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findListHasAssociation", targetNamespace = "http://service.bos_crm.probuing.cn/", className = "cn.probuing.client.FindListHasAssociation")
    @WebMethod
    @ResponseWrapper(localName = "findListHasAssociationResponse", targetNamespace = "http://service.bos_crm.probuing.cn/", className = "cn.probuing.client.FindListHasAssociationResponse")
    public java.util.List<Customer> findListHasAssociation(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findListNotAssociation", targetNamespace = "http://service.bos_crm.probuing.cn/", className = "cn.probuing.client.FindListNotAssociation")
    @WebMethod
    @ResponseWrapper(localName = "findListNotAssociationResponse", targetNamespace = "http://service.bos_crm.probuing.cn/", className = "cn.probuing.client.FindListNotAssociationResponse")
    public java.util.List<Customer> findListNotAssociation();
}
