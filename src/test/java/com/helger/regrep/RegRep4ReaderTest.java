/*
 * Copyright (C) 2021-2022 Philip Helger (www.helger.com)
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

import com.helger.regrep.query.QueryRequest;
import com.helger.regrep.query.QueryResponse;
import com.helger.regrep.rs.AuthorizationExceptionType;
import com.helger.regrep.rs.RegistryExceptionType;

/**
 * Test class for class {@link RegRep4Reader}.
 *
 * @author Philip Helger
 */
public final class RegRep4ReaderTest
{
  @Test
  public void testQueryRequest ()
  {
    QueryRequest qr = RegRep4Reader.queryRequest ().read (new File ("src/test/resources/examples/Data Request.xml"));
    assertNotNull (qr);

    assertNotNull (RegRep4Writer.queryRequest ().getAsBytes (qr));

    qr = RegRep4Reader.queryRequest ().read (new File ("src/test/resources/examples/Document Request.xml"));
    assertNotNull (qr);

    assertNotNull (RegRep4Writer.queryRequest ().getAsBytes (qr));
  }

  @Test
  public void testQueryResponse ()
  {
    final QueryResponse qr = RegRep4Reader.queryResponse ().read (new File ("src/test/resources/examples/Data Response.xml"));
    assertNotNull (qr);

    assertNotNull (RegRep4Writer.queryResponse ().getAsBytes (qr));
  }

  @Test
  public void testRegistryException ()
  {
    final RegistryExceptionType re = RegRep4Reader.registryException ().read (new File ("src/test/resources/examples/Exception.xml"));
    assertNotNull (re);
    assertTrue (re instanceof AuthorizationExceptionType);

    assertEquals ("DD-004", re.getCode ());

    assertNotNull (RegRep4Writer.registryException ().getAsBytes (re));
  }
}
