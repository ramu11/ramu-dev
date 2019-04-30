
package com.mycompany.cxf.soap.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mycompany.cxf.soap.model package. 
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

    private final static QName _DeletePerson_QNAME = new QName("http://model.soap.cxf.mycompany.com/", "deletePerson");
    private final static QName _DeletePersonResponse_QNAME = new QName("http://model.soap.cxf.mycompany.com/", "deletePersonResponse");
    private final static QName _GetPerson_QNAME = new QName("http://model.soap.cxf.mycompany.com/", "getPerson");
    private final static QName _GetPersonResponse_QNAME = new QName("http://model.soap.cxf.mycompany.com/", "getPersonResponse");
    private final static QName _PutPerson_QNAME = new QName("http://model.soap.cxf.mycompany.com/", "putPerson");
    private final static QName _PutPersonResponse_QNAME = new QName("http://model.soap.cxf.mycompany.com/", "putPersonResponse");
    private final static QName _Caller_QNAME = new QName("http://model.soap.cxf.mycompany.com/", "caller");
    private final static QName _PersonException_QNAME = new QName("http://model.soap.cxf.mycompany.com/", "PersonException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mycompany.cxf.soap.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link GetPerson }
     * 
     */
    public GetPerson createGetPerson() {
        return new GetPerson();
    }

    /**
     * Create an instance of {@link GetPersonResponse }
     * 
     */
    public GetPersonResponse createGetPersonResponse() {
        return new GetPersonResponse();
    }

    /**
     * Create an instance of {@link PutPerson }
     * 
     */
    public PutPerson createPutPerson() {
        return new PutPerson();
    }

    /**
     * Create an instance of {@link PutPersonResponse }
     * 
     */
    public PutPersonResponse createPutPersonResponse() {
        return new PutPersonResponse();
    }

    /**
     * Create an instance of {@link Caller }
     * 
     */
    public Caller createCaller() {
        return new Caller();
    }

    /**
     * Create an instance of {@link PersonException }
     * 
     */
    public PersonException createPersonException() {
        return new PersonException();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.soap.cxf.mycompany.com/", name = "deletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<DeletePerson>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.soap.cxf.mycompany.com/", name = "deletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.soap.cxf.mycompany.com/", name = "getPerson")
    public JAXBElement<GetPerson> createGetPerson(GetPerson value) {
        return new JAXBElement<GetPerson>(_GetPerson_QNAME, GetPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.soap.cxf.mycompany.com/", name = "getPersonResponse")
    public JAXBElement<GetPersonResponse> createGetPersonResponse(GetPersonResponse value) {
        return new JAXBElement<GetPersonResponse>(_GetPersonResponse_QNAME, GetPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PutPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.soap.cxf.mycompany.com/", name = "putPerson")
    public JAXBElement<PutPerson> createPutPerson(PutPerson value) {
        return new JAXBElement<PutPerson>(_PutPerson_QNAME, PutPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PutPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.soap.cxf.mycompany.com/", name = "putPersonResponse")
    public JAXBElement<PutPersonResponse> createPutPersonResponse(PutPersonResponse value) {
        return new JAXBElement<PutPersonResponse>(_PutPersonResponse_QNAME, PutPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Caller }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.soap.cxf.mycompany.com/", name = "caller")
    public JAXBElement<Caller> createCaller(Caller value) {
        return new JAXBElement<Caller>(_Caller_QNAME, Caller.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.soap.cxf.mycompany.com/", name = "PersonException")
    public JAXBElement<PersonException> createPersonException(PersonException value) {
        return new JAXBElement<PersonException>(_PersonException_QNAME, PersonException.class, null, value);
    }

}
