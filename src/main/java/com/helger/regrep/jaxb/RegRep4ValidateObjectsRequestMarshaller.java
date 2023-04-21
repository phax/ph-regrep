package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.spi.ValidateObjectsRequest;

/**
 * JAXB marshaller for RegRep4 ValidateObjectsRequest objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4ValidateObjectsRequestMarshaller extends AbstractRegRepJAXBMarshaller <ValidateObjectsRequest>
{
  public RegRep4ValidateObjectsRequestMarshaller ()
  {
    super (ValidateObjectsRequest.class,
           CRegRep4.getAllXSDsSPI (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "ValidateObjectsRequest"));
  }
}
