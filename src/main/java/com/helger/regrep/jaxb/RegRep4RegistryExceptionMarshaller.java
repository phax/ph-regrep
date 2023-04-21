package com.helger.regrep.jaxb;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.rs.RegistryExceptionType;

/**
 * JAXB marshaller for RegRep4 RegistryException objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4RegistryExceptionMarshaller extends AbstractRegRepJAXBMarshaller <RegistryExceptionType>
{
  public RegRep4RegistryExceptionMarshaller ()
  {
    super (RegistryExceptionType.class,
           CRegRep4.getAllXSDsQuery (),
           new com.helger.regrep.rs.ObjectFactory ()::createRegistryException);
  }
}
