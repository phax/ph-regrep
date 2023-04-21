package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.query.QueryRequest;

/**
 * JAXB marshaller for RegRep4 QueryRequest objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4QueryRequestMarshaller extends AbstractRegRepJAXBMarshaller <QueryRequest>
{
  public RegRep4QueryRequestMarshaller ()
  {
    super (QueryRequest.class,
           CRegRep4.getAllXSDsQuery (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:query:4.0", "QueryRequest"));
  }
}
