package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.spi.CatalogObjectsResponse;

/**
 * JAXB marshaller for RegRep4 CatalogObjectsResponse objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4CatalogObjectsResponseMarshaller extends AbstractRegRepJAXBMarshaller <CatalogObjectsResponse>
{
  public RegRep4CatalogObjectsResponseMarshaller ()
  {
    super (CatalogObjectsResponse.class,
           CRegRep4.getAllXSDsSPI (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "CatalogObjectsResponse"));
  }
}
