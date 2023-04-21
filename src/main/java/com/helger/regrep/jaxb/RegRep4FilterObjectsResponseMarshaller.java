package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.spi.FilterObjectsResponse;

/**
 * JAXB marshaller for RegRep4 FilterObjectsResponse objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4FilterObjectsResponseMarshaller extends AbstractRegRepJAXBMarshaller <FilterObjectsResponse>
{
  public RegRep4FilterObjectsResponseMarshaller ()
  {
    super (FilterObjectsResponse.class,
           CRegRep4.getAllXSDsSPI (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "FilterObjectsResponse"));
  }
}
