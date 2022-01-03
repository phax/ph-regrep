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
package com.helger.regrep.slot;

import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.mutable.MutableInt;
import com.helger.regrep.RegRep4Writer;
import com.helger.regrep.RegRepHelper;
import com.helger.regrep.query.QueryRequest;
import com.helger.regrep.rim.ValueType;
import com.helger.xml.serialize.read.DOMReader;

/**
 * Test class for class {@link SlotBuilder}
 *
 * @author Philip Helger
 */
public final class SlotBuilderTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (SlotBuilderTest.class);
  private static final MutableInt COUNTER = new MutableInt (0);

  @Nonnull
  private static SlotBuilder _sb ()
  {
    return new SlotBuilder ().setName ("slot" + COUNTER.inc ());
  }

  @Test
  public void testBasic ()
  {
    final Document aDoc = DOMReader.readXMLDOM ("<root attr='a' xmlns='urn:anything-weird/bla-foo'><child><child2>value</child2></child></root>");
    final ICommonsMap <ValueType, ValueType> aMap = new CommonsLinkedHashMap <> ();
    aMap.put (SlotHelper.createSlotValue ("Key1"), SlotHelper.createSlotValue (PDTFactory.getCurrentOffsetDateTime ()));
    aMap.put (SlotHelper.createSlotValue (BigInteger.valueOf (1234)), SlotHelper.createSlotValue (42f));

    final QueryRequest aQR;
    aQR = RegRepHelper.createQueryRequest ("mock-data-request",
                                           _sb ().setValue (aDoc.getDocumentElement ()).build (),
                                           _sb ().setValue (true).build (),
                                           _sb ().setValue (ERegRepCollectionType.LIST,
                                                            SlotHelper.createSlotValue ("ListItem1"),
                                                            SlotHelper.createSlotValue ("ListItem2"))
                                                 .build (),
                                           _sb ().setValue (PDTFactory.getCurrentLocalDateTime ()).build (),
                                           _sb ().setValue (PDTFactory.getCurrentLocalDate ()).build (),
                                           _sb ().setValue (PDTFactory.getCurrentZonedDateTimeUTC ()).build (),
                                           _sb ().setValue (new Date ()).build (),
                                           _sb ().setValue (3.223344f).build (),
                                           _sb ().setValue (BigInteger.TEN).build (),
                                           _sb ().setValue (11).build (),
                                           _sb ().setValue (12L).build (),
                                           _sb ().setValue (SlotHelper.createInternationalStringType (SlotHelper.createLocalizedString (Locale.ENGLISH,
                                                                                                                                        "Qualification Procedure in Public Procurement"),
                                                                                                      SlotHelper.createLocalizedString (Locale.GERMAN,
                                                                                                                                        "Qualifizierungsverfahren im öffentlichen Beschaffungswesen")))
                                                 .build (),
                                           _sb ().setValue (SlotHelper.createMap (aMap)).build (),
                                           _sb ().setValue (SlotHelper.createSlot ("nestedSlot",
                                                                                   SlotHelper.createSlotValue ("simpleString")))
                                                 .build (),
                                           _sb ().setValue ("text only").build (),
                                           _sb ().setValue (SlotHelper.createVocabularyTerm ("myVoc", "myTerm"))
                                                 .build (),
                                           _sb ().setVocabularyTermValue ("myVoc2", "myTerm2").build ());
    assertNotNull (RegRep4Writer.queryRequest ().getAsDocument (aQR));

    LOGGER.info (RegRep4Writer.queryRequest ().setFormattedOutput (true).getAsString (aQR));
  }
}
