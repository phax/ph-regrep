package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.spi.FilterObjectsRequest;

/**
 * JAXB marshaller for RegRep4 FilterObjectsRequest objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4FilterObjectsRequestMarshaller extends AbstractRegRepJAXBMarshaller <FilterObjectsRequest>
{
  public RegRep4FilterObjectsRequestMarshaller ()
  {
    super (FilterObjectsRequest.class,
           CRegRep4.getAllXSDsSPI (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "FilterObjectsRequest"));
  }
}
