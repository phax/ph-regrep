package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.lcm.SubmitObjectsRequest;

/**
 * JAXB marshaller for RegRep4 SubmitObjectsRequest objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4SubmitObjectsMarshaller extends AbstractRegRepJAXBMarshaller <SubmitObjectsRequest>
{
  public RegRep4SubmitObjectsMarshaller ()
  {
    super (SubmitObjectsRequest.class,
           CRegRep4.getAllXSDsLCM (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "SubmitObjectsRequest"));
  }
}
