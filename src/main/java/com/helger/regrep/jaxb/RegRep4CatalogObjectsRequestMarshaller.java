package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.spi.CatalogObjectsRequest;

/**
 * JAXB marshaller for RegRep4 CatalogObjectsRequest objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4CatalogObjectsRequestMarshaller extends AbstractRegRepJAXBMarshaller <CatalogObjectsRequest>
{
  public RegRep4CatalogObjectsRequestMarshaller ()
  {
    super (CatalogObjectsRequest.class,
           CRegRep4.getAllXSDsSPI (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "CatalogObjectsRequest"));
  }
}
