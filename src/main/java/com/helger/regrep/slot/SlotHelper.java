/*
 * Copyright (C) 2021 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.w3c.dom.Element;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.datetime.XMLOffsetDateTime;
import com.helger.commons.text.IMultilingualText;
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

  @Nonnull
  public static LocalizedStringType createLocalizedString (@Nonnull final Locale aLocale, @Nonnull final String sText)
  {
    ValueEnforcer.notNull (aLocale, "Locale");
    ValueEnforcer.notNull (sText, "Text");
    return createLocalizedString (aLocale.getLanguage (), sText);
  }

  @Nonnull
  public static LocalizedStringType createLocalizedString (@Nonnull @Nonempty final String sLanguage,
                                                           @Nonnull final String sText)
  {
    ValueEnforcer.notEmpty (sLanguage, "Language");
    ValueEnforcer.notNull (sText, "Text");
    final LocalizedStringType ret = new LocalizedStringType ();
    ret.setLang (sLanguage);
    ret.setValue (sText);
    return ret;
  }

  @Nonnull
  public static InternationalStringType createInternationalStringType (@Nullable final Map <String, String> aMap)
  {
    ValueEnforcer.noNullValue (aMap, "Map");

    final InternationalStringType ret = new InternationalStringType ();
    if (aMap != null)
      for (final Map.Entry <String, String> aEntry : aMap.entrySet ())
        ret.addLocalizedString (createLocalizedString (aEntry.getKey (), aEntry.getValue ()));
    return ret;
  }

  @Nonnull
  public static InternationalStringType createInternationalStringType (@Nullable final IMultilingualText aMLT)
  {
    final InternationalStringType ret = new InternationalStringType ();
    if (aMLT != null)
      for (final Map.Entry <Locale, String> aEntry : aMLT.texts ().entrySet ())
        ret.addLocalizedString (createLocalizedString (aEntry.getKey (), aEntry.getValue ()));
    return ret;
  }

  @Nonnull
  public static InternationalStringType createInternationalStringType (@Nullable final LocalizedStringType... aArray)
  {
    ValueEnforcer.noNullValue (aArray, "Array");

    final InternationalStringType ret = new InternationalStringType ();
    if (aArray != null)
      for (final LocalizedStringType aItem : aArray)
        ret.addLocalizedString (aItem);
    return ret;
  }

  @Nonnull
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

  @Nonnull
  public static VocabularyTermType createVocabularyTerm (@Nonnull final VocabularyTerm aTerm)
  {
    ValueEnforcer.notNull (aTerm, "Term");
    return createVocabularyTerm (aTerm.getVocabulary (), aTerm.getTerm ());
  }

  @Nonnull
  public static VocabularyTermType createVocabularyTerm (@Nonnull final String sVocabulary, @Nonnull final String sTerm)
  {
    ValueEnforcer.notNull (sVocabulary, "Vocabulary");
    ValueEnforcer.notNull (sTerm, "Term");
    final VocabularyTermType ret = new VocabularyTermType ();
    ret.setVocabulary (sVocabulary);
    ret.setTerm (sTerm);
    return ret;
  }

  @Nonnull
  public static AnyValueType createSlotValue (@Nonnull final Element x)
  {
    ValueEnforcer.notNull (x, "Value");
    final AnyValueType ret = new AnyValueType ();
    ret.setAny (x);
    return ret;
  }

  @Nonnull
  public static BooleanValueType createSlotValue (final boolean x)
  {
    return new BooleanValueType (Boolean.valueOf (x));
  }

  @Nonnull
  public static CollectionValueType createSlotValue (@Nullable final ValueType... x)
  {
    return createSlotValue ((ERegRepCollectionType) null, x);
  }

  @Nonnull
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

  @Nonnull
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

  @Nonnull
  public static DateTimeValueType createSlotValue (@Nonnull final OffsetDateTime x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new DateTimeValueType (XMLOffsetDateTime.of (x));
  }

  @Nonnull
  public static DateTimeValueType createSlotValue (@Nonnull final XMLOffsetDateTime x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new DateTimeValueType (x);
  }

  @Nonnull
  public static FloatValueType createSlotValue (final float x)
  {
    return new FloatValueType (Float.valueOf (x));
  }

  @Nonnull
  public static IntegerValueType createSlotValue (@Nonnull final BigInteger x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new IntegerValueType (x);
  }

  @Nonnull
  public static InternationalStringValueType createSlotValue (@Nonnull final InternationalStringType x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new InternationalStringValueType (x);
  }

  @Nonnull
  public static MapValueType createSlotValue (@Nonnull final MapType x)
  {
    ValueEnforcer.notNull (x, "Value");
    final MapValueType ret = new MapValueType ();
    ret.setMap (x);
    return ret;
  }

  @Nonnull
  public static SlotValueType createSlotValue (@Nonnull final SlotType x)
  {
    ValueEnforcer.notNull (x, "Value");
    final SlotValueType ret = new SlotValueType ();
    ret.setSlot (x);
    return ret;
  }

  @Nonnull
  public static StringValueType createSlotValue (@Nonnull final String x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new StringValueType (x);
  }

  @Nonnull
  public static VocabularyTermValueType createSlotValue (@Nonnull final VocabularyTermType x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new VocabularyTermValueType (x);
  }

  @Nonnull
  public static SlotType createSlot (@Nonnull @Nonempty final String sName, @Nonnull final ValueType aValue)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    ValueEnforcer.notNull (aValue, "Value");

    final SlotType ret = new SlotType ();
    ret.setName (sName);
    ret.setSlotValue (aValue);
    return ret;
  }
}
