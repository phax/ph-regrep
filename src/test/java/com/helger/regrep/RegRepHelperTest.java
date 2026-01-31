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

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.helger.datetime.helper.PDTFactory;
import com.helger.regrep.jaxb.RegRep4QueryRequestMarshaller;
import com.helger.regrep.jaxb.RegRep4QueryResponseMarshaller;
import com.helger.regrep.query.QueryRequest;
import com.helger.regrep.query.QueryResponse;
import com.helger.regrep.slot.SlotBuilder;
import com.helger.regrep.slot.SlotHelper;
import com.helger.xml.serialize.read.DOMReader;

/**
 * Test class for class {@link RegRepHelper}
 *
 * @author Philip Helger
 */
public final class RegRepHelperTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (RegRepHelperTest.class);

  private static void _validate (@NonNull final QueryRequest aQR)
  {
    assertNotNull (aQR);

    final RegRep4QueryRequestMarshaller m = new RegRep4QueryRequestMarshaller ();
    if (false)
      LOGGER.info (m.setFormattedOutput (true).getAsString (aQR));

    assertNotNull (m.getAsDocument (aQR));
  }

  @Test
  public void testRequest ()
  {
    final Document aDoc = DOMReader.readXMLDOM ("<root attr='a' xmlns='urn:anything-weird/bla-foo'><child><child2>value</child2></child></root>");

    final QueryRequest aQR = RegRepHelper.createQueryRequest ("mock-data-request",
                                                              new SlotBuilder ().setName ("IssueDateTime")
                                                                                .setValue (PDTFactory.getCurrentLocalDateTime ())
                                                                                .build (),
                                                              new SlotBuilder ().setName ("DataConsumerRequestPurpose")
                                                                                .setValue (SlotHelper.createInternationalStringType (SlotHelper.createLocalizedString (Locale.ENGLISH,
                                                                                                                                                                       "Qualification Procedure in Public Procurement"),
                                                                                                                                     SlotHelper.createLocalizedString (Locale.GERMAN,
                                                                                                                                                                       "Qualifizierungsverfahren im öffentlichen Beschaffungswesen")))
                                                                                .build (),
                                                              new SlotBuilder ().setName ("DummyXML")
                                                                                .setValue (aDoc.getDocumentElement ())
                                                                                .build ());
    _validate (aQR);
  }

  private static void _validate (@NonNull final QueryResponse aQR)
  {
    assertNotNull (aQR);

    final RegRep4QueryResponseMarshaller m = new RegRep4QueryResponseMarshaller ();
    if (true)
      LOGGER.info (m.setFormattedOutput (true).getAsString (aQR));

    assertNotNull (m.getAsDocument (aQR));
  }

  @Test
  public void testResponse ()
  {
    final Document aDoc = DOMReader.readXMLDOM ("<root attr='a' xmlns='urn:anything-weird/bla-foo'><child><child2>value</child2></child></root>");

    final QueryResponse aQR = RegRepHelper.createQueryResponse (ERegRepResponseStatus.SUCCESS,
                                                                "mock-data-Response",
                                                                new SlotBuilder ().setName ("IssueDateTime")
                                                                                  .setValue (PDTFactory.getCurrentLocalDateTime ())
                                                                                  .build (),
                                                                new SlotBuilder ().setName ("DataConsumerResponsePurpose")
                                                                                  .setValue (SlotHelper.createInternationalStringType (SlotHelper.createLocalizedString (Locale.ENGLISH,
                                                                                                                                                                         "Qualification Procedure in Public Procurement"),
                                                                                                                                       SlotHelper.createLocalizedString (Locale.GERMAN,
                                                                                                                                                                         "Qualifizierungsverfahren im öffentlichen Beschaffungswesen")))
                                                                                  .build (),
                                                                new SlotBuilder ().setName ("DummyXML")
                                                                                  .setValue (aDoc.getDocumentElement ())
                                                                                  .build ());
    _validate (aQR);
  }
}
