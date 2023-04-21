package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.lcm.UpdateObjectsRequest;

/**
 * JAXB marshaller for RegRep4 UpdateObjectsRequest objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4UpdateObjectsMarshaller extends AbstractRegRepJAXBMarshaller <UpdateObjectsRequest>
{
  public RegRep4UpdateObjectsMarshaller ()
  {
    super (UpdateObjectsRequest.class,
           CRegRep4.getAllXSDsLCM (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "UpdateObjectsRequest"));
  }
}
