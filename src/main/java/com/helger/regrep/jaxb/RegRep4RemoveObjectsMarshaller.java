package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.lcm.RemoveObjectsRequest;

/**
 * JAXB marshaller for RegRep4 RemoveObjectsRequest objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4RemoveObjectsMarshaller extends AbstractRegRepJAXBMarshaller <RemoveObjectsRequest>
{
  public RegRep4RemoveObjectsMarshaller ()
  {
    super (RemoveObjectsRequest.class,
           CRegRep4.getAllXSDsLCM (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "RemoveObjectsRequest"));
  }
}
