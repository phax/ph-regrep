/*
 * Copyright (C) 2021-2025 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.id.IHasID;
import com.helger.base.lang.EnumHelper;

/**
 * Enumeration with collection types.
 *
 * @author Philip Helger
 */
public enum ERegRepCollectionType implements IHasID <String>
{
  BAG ("urn:oasis:names:tc:ebxml-regrep:CollectionType:Bag"),
  LIST ("urn:oasis:names:tc:ebxml-regrep:CollectionType:List"),
  SET ("urn:oasis:names:tc:ebxml-regrep:CollectionType:Set"),
  SORTED_SET ("urn:oasis:names:tc:ebxml-regrep:CollectionType:Set:SortedSet");

  private final String m_sID;

  ERegRepCollectionType (@NonNull @Nonempty final String sValue)
  {
    m_sID = sValue;
  }

  @NonNull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public static ERegRepCollectionType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (ERegRepCollectionType.class, sID);
  }
}
