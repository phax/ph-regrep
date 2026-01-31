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
package com.helger.regrep.slot;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Map;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.w3c.dom.Element;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.datetime.xml.XMLOffsetDateTime;
import com.helger.regrep.helper.VocabularyTerm;
import com.helger.regrep.rim.AnyValueType;
import com.helger.regrep.rim.BooleanValueType;
import com.helger.regrep.rim.CollectionValueType;
import com.helger.regrep.rim.DateTimeValueType;
import com.helger.regrep.rim.EntryType;
import com.helger.regrep.rim.FloatValueType;
import com.helger.regrep.rim.IntegerValueType;
import com.helger.regrep.rim.InternationalStringType;
import com.helger.regrep.rim.InternationalStringValueType;
import com.helger.regrep.rim.LocalizedStringType;
import com.helger.regrep.rim.MapType;
import com.helger.regrep.rim.MapValueType;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.rim.SlotValueType;
import com.helger.regrep.rim.StringValueType;
import com.helger.regrep.rim.ValueType;
import com.helger.regrep.rim.VocabularyTermType;
import com.helger.regrep.rim.VocabularyTermValueType;
import com.helger.text.IMultilingualText;

/**
 * Helper class to simplify the creation of RegRep data constructs.
 *
 * @author Philip Helger
 */
@Immutable
public final class SlotHelper
{
  private SlotHelper ()
  {}

  @NonNull
  public static LocalizedStringType createLocalizedString (@NonNull final Locale aLocale, @NonNull final String sText)
  {
    ValueEnforcer.notNull (aLocale, "Locale");
    ValueEnforcer.notNull (sText, "Text");
    return createLocalizedString (aLocale.getLanguage (), sText);
  }

  @NonNull
  public static LocalizedStringType createLocalizedString (@NonNull @Nonempty final String sLanguage,
                                                           @NonNull final String sText)
  {
    ValueEnforcer.notEmpty (sLanguage, "Language");
    ValueEnforcer.notNull (sText, "Text");
    final LocalizedStringType ret = new LocalizedStringType ();
    ret.setLang (sLanguage);
    ret.setValue (sText);
    return ret;
  }

  @NonNull
  public static InternationalStringType createInternationalStringType (@Nullable final Map <String, String> aMap)
  {
    ValueEnforcer.noNullValue (aMap, "Map");

    final InternationalStringType ret = new InternationalStringType ();
    if (aMap != null)
      for (final Map.Entry <String, String> aEntry : aMap.entrySet ())
        ret.addLocalizedString (createLocalizedString (aEntry.getKey (), aEntry.getValue ()));
    return ret;
  }

  @NonNull
  public static InternationalStringType createInternationalStringType (@Nullable final IMultilingualText aMLT)
  {
    final InternationalStringType ret = new InternationalStringType ();
    if (aMLT != null)
      for (final Map.Entry <Locale, String> aEntry : aMLT.texts ().entrySet ())
        ret.addLocalizedString (createLocalizedString (aEntry.getKey (), aEntry.getValue ()));
    return ret;
  }

  @NonNull
  public static InternationalStringType createInternationalStringType (@Nullable final LocalizedStringType... aArray)
  {
    ValueEnforcer.noNullValue (aArray, "Array");

    final InternationalStringType ret = new InternationalStringType ();
    if (aArray != null)
      for (final LocalizedStringType aItem : aArray)
        ret.addLocalizedString (aItem);
    return ret;
  }

  @NonNull
  public static MapType createMap (@Nullable final Map <? extends ValueType, ? extends ValueType> aMap)
  {
    ValueEnforcer.notNull (aMap, "Value");
    final MapType ret = new MapType ();
    if (aMap != null)
      for (final Map.Entry <? extends ValueType, ? extends ValueType> aEntry : aMap.entrySet ())
      {
        final EntryType aRegRepEntry = new EntryType ();
        aRegRepEntry.setEntryKey (aEntry.getKey ());
        aRegRepEntry.setEntryValue (aEntry.getValue ());
        ret.addEntry (aRegRepEntry);
      }
    return ret;
  }

  @NonNull
  public static VocabularyTermType createVocabularyTerm (@NonNull final VocabularyTerm aTerm)
  {
    ValueEnforcer.notNull (aTerm, "Term");
    return createVocabularyTerm (aTerm.getVocabulary (), aTerm.getTerm ());
  }

  @NonNull
  public static VocabularyTermType createVocabularyTerm (@NonNull final String sVocabulary, @NonNull final String sTerm)
  {
    ValueEnforcer.notNull (sVocabulary, "Vocabulary");
    ValueEnforcer.notNull (sTerm, "Term");
    final VocabularyTermType ret = new VocabularyTermType ();
    ret.setVocabulary (sVocabulary);
    ret.setTerm (sTerm);
    return ret;
  }

  @NonNull
  public static AnyValueType createSlotValue (@NonNull final Element x)
  {
    ValueEnforcer.notNull (x, "Value");
    final AnyValueType ret = new AnyValueType ();
    ret.setAny (x);
    return ret;
  }

  @NonNull
  public static BooleanValueType createSlotValue (final boolean x)
  {
    return new BooleanValueType (Boolean.valueOf (x));
  }

  @NonNull
  public static CollectionValueType createSlotValue (@Nullable final ValueType... x)
  {
    return createSlotValue ((ERegRepCollectionType) null, x);
  }

  @NonNull
  public static CollectionValueType createSlotValue (@Nullable final ERegRepCollectionType eType,
                                                     @Nullable final ValueType... x)
  {
    ValueEnforcer.noNullValue (x, "Value");
    final CollectionValueType ret = new CollectionValueType ();
    if (eType != null)
      ret.setCollectionType (eType.getID ());
    if (x != null)
      for (final ValueType aItem : x)
        ret.addElement (aItem);
    return ret;
  }

  @NonNull
  public static CollectionValueType createSlotValue (@Nullable final ERegRepCollectionType eType,
                                                     @Nullable final Iterable <? extends ValueType> x)
  {
    ValueEnforcer.noNullValue (x, "Value");
    final CollectionValueType ret = new CollectionValueType ();
    if (eType != null)
      ret.setCollectionType (eType.getID ());
    if (x != null)
      for (final ValueType aItem : x)
        ret.addElement (aItem);
    return ret;
  }

  @NonNull
  public static DateTimeValueType createSlotValue (@NonNull final OffsetDateTime x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new DateTimeValueType (XMLOffsetDateTime.of (x));
  }

  @NonNull
  public static DateTimeValueType createSlotValue (@NonNull final XMLOffsetDateTime x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new DateTimeValueType (x);
  }

  @NonNull
  public static FloatValueType createSlotValue (final float x)
  {
    return new FloatValueType (Float.valueOf (x));
  }

  @NonNull
  public static IntegerValueType createSlotValue (@NonNull final BigInteger x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new IntegerValueType (x);
  }

  @NonNull
  public static InternationalStringValueType createSlotValue (@NonNull final InternationalStringType x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new InternationalStringValueType (x);
  }

  @NonNull
  public static MapValueType createSlotValue (@NonNull final MapType x)
  {
    ValueEnforcer.notNull (x, "Value");
    final MapValueType ret = new MapValueType ();
    ret.setMap (x);
    return ret;
  }

  @NonNull
  public static SlotValueType createSlotValue (@NonNull final SlotType x)
  {
    ValueEnforcer.notNull (x, "Value");
    final SlotValueType ret = new SlotValueType ();
    ret.setSlot (x);
    return ret;
  }

  @NonNull
  public static StringValueType createSlotValue (@NonNull final String x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new StringValueType (x);
  }

  @NonNull
  public static VocabularyTermValueType createSlotValue (@NonNull final VocabularyTermType x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new VocabularyTermValueType (x);
  }

  @NonNull
  public static SlotType createSlot (@NonNull @Nonempty final String sName, @NonNull final ValueType aValue)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    ValueEnforcer.notNull (aValue, "Value");

    final SlotType ret = new SlotType ();
    ret.setName (sName);
    ret.setSlotValue (aValue);
    return ret;
  }
}
