/*
 * Copyright (C) 2021-2023 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

/**
 * Query response status enum
 *
 * @author Philip Helger
 */
public enum ERegRepResponseStatus implements IHasID <String>
{
  SUCCESS ("urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success"),
  PARTIAL_SUCCESS ("urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:PartialSuccess"),
  FAILURE ("urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Failure");

  private final String m_sID;

  ERegRepResponseStatus (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public static ERegRepResponseStatus getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (ERegRepResponseStatus.class, sID);
  }

  @Nullable
  public static ERegRepResponseStatus getFromIDOrDefault (@Nullable final String sID, @Nullable final ERegRepResponseStatus eDefault)
  {
    return EnumHelper.getFromIDOrDefault (ERegRepResponseStatus.class, sID, eDefault);
  }
}
