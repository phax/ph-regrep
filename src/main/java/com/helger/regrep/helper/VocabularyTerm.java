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
package com.helger.regrep.helper;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * This is a helper class to have an easy way to provide a vocabulary term.
 *
 * @author Philip Helger
 */
@Immutable
@MustImplementEqualsAndHashcode
public final class VocabularyTerm implements Serializable
{
  private final String m_sVocabulary;
  private final String m_sTerm;

  public VocabularyTerm (@Nonnull @Nonempty final String sVocabulary, @Nonnull @Nonempty final String sTerm)
  {
    ValueEnforcer.notEmpty (sVocabulary, "Vocabulary");
    ValueEnforcer.notEmpty (sTerm, "Term");
    m_sVocabulary = sVocabulary;
    m_sTerm = sTerm;
  }

  /**
   * @return The vocabulary as provided in the constructor. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getVocabulary ()
  {
    return m_sVocabulary;
  }

  /**
   * Check if this term has the provided vocabulary or not.
   *
   * @param sVocabulary
   *        The vocabulary to compare to. May be <code>null</code>.
   * @return <code>true</code> if the vocabularies are identical.
   */
  public boolean hasVocabulary (@Nullable final String sVocabulary)
  {
    return m_sVocabulary.equals (sVocabulary);
  }

  /**
   * @return The term as provided in the constructor. Neither <code>null</code>
   *         nor empty.
   */
  @Nonnull
  @Nonempty
  public String getTerm ()
  {
    return m_sTerm;
  }

  /**
   * Check if this vocabulary term has the provided term or not.
   *
   * @param sTerm
   *        The term to compare to. May be <code>null</code>.
   * @return <code>true</code> if the values are identical.
   */
  public boolean hasTerm (@Nullable final String sTerm)
  {
    return m_sTerm.equals (sTerm);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final VocabularyTerm rhs = (VocabularyTerm) o;
    return m_sVocabulary.equals (rhs.m_sVocabulary) && m_sTerm.equals (rhs.m_sTerm);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sVocabulary).append (m_sTerm).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("Vocabulary", m_sVocabulary).append ("Term", m_sTerm).getToString ();
  }
}
