package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.spi.ValidateObjectsResponse;

/**
 * JAXB marshaller for RegRep4 ValidateObjectsResponse objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4ValidateObjectsResponseMarshaller extends AbstractRegRepJAXBMarshaller <ValidateObjectsResponse>
{
  public RegRep4ValidateObjectsResponseMarshaller ()
  {
    super (ValidateObjectsResponse.class,
           CRegRep4.getAllXSDsSPI (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "ValidateObjectsResponse"));
  }
}
