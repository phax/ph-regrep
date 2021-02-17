/**
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.w3c.dom.Element;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.datetime.PDTFactory;
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
public class SlotBuilder
{
  private String m_sName;
  private ValueType m_aValue;

  public SlotBuilder ()
  {}

  @Nonnull
  public SlotBuilder setName (@Nonnull @Nonempty final String sName)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    m_sName = sName;
    return this;
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final ValueType x)
  {
    ValueEnforcer.notNull (x, "Value");
    m_aValue = x;
    return this;
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final Element aNode)
  {
    // Cannot be a DOM Document
    return setValue (SlotHelper.createSlotValue (aNode));
  }

  @Nonnull
  public SlotBuilder setValue (final boolean b)
  {
    return setValue (SlotHelper.createSlotValue (b));
  }

  @Nonnull
  public SlotBuilder setValue (@Nullable final ERegRepCollectionType eCollectionType, @Nullable final ValueType... aArray)
  {
    return setValue (SlotHelper.createSlotValue (eCollectionType, aArray));
  }

  @Nonnull
  public SlotBuilder setValue (@Nullable final ERegRepCollectionType eCollectionType, @Nullable final Iterable <? extends ValueType> aCont)
  {
    return setValue (SlotHelper.createSlotValue (eCollectionType, aCont));
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final LocalDateTime x)
  {
    ValueEnforcer.notNull (x, "Value");
    return setValue (SlotHelper.createSlotValue (x));
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final LocalDate x)
  {
    ValueEnforcer.notNull (x, "Value");
    return setValue (x.atStartOfDay ());
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final ZonedDateTime x)
  {
    ValueEnforcer.notNull (x, "Value");
    return setValue (x.toLocalDateTime ());
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final Date x)
  {
    ValueEnforcer.notNull (x, "Value");
    return setValue (PDTFactory.createLocalDateTime (x));
  }

  @Nonnull
  public SlotBuilder setValue (final float x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final BigInteger x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @Nonnull
  public SlotBuilder setValue (final int x)
  {
    return setValue (BigInteger.valueOf (x));
  }

  @Nonnull
  public SlotBuilder setValue (final long x)
  {
    return setValue (BigInteger.valueOf (x));
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final InternationalStringType x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final MapType x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final SlotType x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final String x)
  {
    return setValue (SlotHelper.createSlotValue (x));
  }

  @Nonnull
  public SlotBuilder setVocabularyTermValue (@Nonnull final String sVocabulary, @Nonnull final String sTerm)
  {
    return setValue (SlotHelper.createVocabularyTerm (sVocabulary, sTerm));
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final VocabularyTerm x)
  {
    return setValue (SlotHelper.createVocabularyTerm (x));
  }

  @Nonnull
  public SlotBuilder setValue (@Nonnull final VocabularyTermType x)
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

  @Nonnull
  public SlotType build ()
  {
    if (m_sName == null)
      throw new IllegalStateException ("Name is missing");
    if (m_aValue == null)
      throw new IllegalStateException ("Value is missing");

    return SlotHelper.createSlot (m_sName, m_aValue);
  }
}
