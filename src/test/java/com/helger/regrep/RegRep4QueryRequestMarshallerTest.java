/*
 * Copyright (C) 2021-2026 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.regrep;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.helger.regrep.jaxb.RegRep4QueryRequestMarshaller;
import com.helger.regrep.jaxb.RegRep4QueryResponseMarshaller;
import com.helger.regrep.jaxb.RegRep4RegistryExceptionMarshaller;
import com.helger.regrep.query.QueryRequest;
import com.helger.regrep.query.QueryResponse;
import com.helger.regrep.rs.AuthorizationExceptionType;
import com.helger.regrep.rs.RegistryExceptionType;

/**
 * Test class for class {@link RegRep4QueryRequestMarshaller}.
 *
 * @author Philip Helger
 */
public final class RegRep4QueryRequestMarshallerTest
{
  @Test
  public void testQueryRequest ()
  {
    final RegRep4QueryRequestMarshaller m = new RegRep4QueryRequestMarshaller ();
    QueryRequest qr = m.read (new File ("src/test/resources/external/examples/Data Request.xml"));
    assertNotNull (qr);

    assertNotNull (m.getAsBytes (qr));

    qr = m.read (new File ("src/test/resources/external/examples/Document Request.xml"));
    assertNotNull (qr);

    assertNotNull (m.getAsBytes (qr));
  }

  @Test
  public void testQueryResponse ()
  {
    final RegRep4QueryResponseMarshaller m = new RegRep4QueryResponseMarshaller ();
    final QueryResponse qr = m.read (new File ("src/test/resources/external/examples/Data Response.xml"));
    assertNotNull (qr);

    assertNotNull (m.getAsBytes (qr));
  }

  @Test
  public void testRegistryException ()
  {
    final RegRep4RegistryExceptionMarshaller m = new RegRep4RegistryExceptionMarshaller ();
    final RegistryExceptionType re = m.read (new File ("src/test/resources/external/examples/Exception.xml"));
    assertNotNull (re);
    assertTrue (re instanceof AuthorizationExceptionType);

    assertEquals ("DD-004", re.getCode ());

    assertNotNull (m.getAsBytes (re));
  }
}
