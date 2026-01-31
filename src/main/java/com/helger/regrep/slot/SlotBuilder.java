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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.w3c.dom.Element;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.builder.IBuilder;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.datetime.helper.PDTFactory;
import com.helger.regrep.helper.VocabularyTerm;
import com.helger.regrep.rim.InternationalStringType;
import com.helger.regrep.rim.MapType;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.rim.ValueType;
import com.helger.regrep.rim.VocabularyTermType;

/**
 * A type -safe builder for RegRep slots.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class SlotBuilder implements IBuilder <SlotType>
{
  private String m_sName;
  private ValueType m_aValue;

  public SlotBuilder ()
  {}

  @NonNull
  public SlotBuilder setName (@NonNull @Nonempty final String sName)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    m_sName = sName;
    return this;
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final ValueType x)
  {
    ValueEnforcer.notNull (x, "Value");
    m_aValue = x;
    return this;
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final Element aNode)
  {
    // Cannot be a DOM Document
    return setValue (SlotHelper.createSlotValue (aNode));
  }

  @NonNull
  public SlotBuilder setValue (final boolean b)
  {
    return setValue (SlotHelper.createSlotValue (b));
  }

  @NonNull
  public SlotBuilder setValue (@Nullable final ERegRepCollectionType eCollectionType,
                               @Nullable final ValueType... aArray)
  {
    return setValue (SlotHelper.createSlotValue (eCollectionType, aArray));
  }

  @NonNull
  public SlotBuilder setValue (@Nullable final ERegRepCollectionType eCollectionType,
                               @Nullable final Iterable <? extends ValueType> aCont)
  {
    return setValue (SlotHelper.createSlotValue (eCollectionType, aCont));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final OffsetDateTime x)
  {
    ValueEnforcer.notNull (x, "Value");
    return setValue (SlotHelper.createSlotValue (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final LocalDateTime x)
  {
    ValueEnforcer.notNull (x, "Value");
    return setValue (PDTFactory.createOffsetDateTime (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final LocalDate x)
  {
    ValueEnforcer.notNull (x, "Value");
    return setValue (PDTFactory.createOffsetDateTime (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final ZonedDateTime x)
  {
    ValueEnforcer.notNull (x, "Value");
    return setValue (PDTFactory.createOffsetDateTime (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final Date x)
  {
    ValueEnforcer.notNull (x, "Value");
    return setValue (PDTFactory.createOffsetDateTime (x));
  }

  @NonNull
  public SlotBuilder setValue (final float x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final BigInteger x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @NonNull
  public SlotBuilder setValue (final int x)
  {
    return setValue (BigInteger.valueOf (x));
  }

  @NonNull
  public SlotBuilder setValue (final long x)
  {
    return setValue (BigInteger.valueOf (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final InternationalStringType x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final MapType x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final SlotType x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final String x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @NonNull
  public SlotBuilder setVocabularyTermValue (@NonNull final String sVocabulary, @NonNull final String sTerm)
  {
    return setValue (SlotHelper.createVocabularyTerm (sVocabulary, sTerm));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final VocabularyTerm x)
  {
    return setValue (SlotHelper.createVocabularyTerm (x));
  }

  @NonNull
  public SlotBuilder setValue (@NonNull final VocabularyTermType x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  /**
   * @return <code>true</code> if all mandatory fields are set and build will
   *         succeed.
   */
  public boolean areAllMandatoryFieldsSet ()
  {
    return m_sName != null && m_aValue != null;
  }

  @NonNull
  public SlotType build ()
  {
    if (m_sName == null)
      throw new IllegalStateException ("Name is missing");
    if (m_aValue == null)
      throw new IllegalStateException ("Value is missing");

    return SlotHelper.createSlot (m_sName, m_aValue);
  }
}
