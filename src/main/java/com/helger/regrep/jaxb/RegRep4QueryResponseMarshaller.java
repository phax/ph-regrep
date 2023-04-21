package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.query.QueryResponse;

/**
 * JAXB marshaller for RegRep4 QueryResponse objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4QueryResponseMarshaller extends AbstractRegRepJAXBMarshaller <QueryResponse>
{
  public RegRep4QueryResponseMarshaller ()
  {
    super (QueryResponse.class,
           CRegRep4.getAllXSDsQuery (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:query:4.0", "QueryResponse"));
  }
}
