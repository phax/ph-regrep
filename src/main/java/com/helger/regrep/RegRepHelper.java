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

import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.regrep.query.QueryRequest;
import com.helger.regrep.query.QueryResponse;
import com.helger.regrep.query.ResponseOptionType;
import com.helger.regrep.rim.QueryType;
import com.helger.regrep.rim.SlotType;

/**
 * Helper class to simplify the creation of RegRep data constructs.
 *
 * @author Philip Helger
 */
@Immutable
public final class RegRepHelper
{
  private RegRepHelper ()
  {}

  @NonNull
  public static QueryRequest createEmptyQueryRequest ()
  {
    final QueryRequest ret = new QueryRequest ();
    ret.setId (UUID.randomUUID ().toString ());
    ret.setResponseOption (new ResponseOptionType ());
    return ret;
  }

  @NonNull
  public static QueryRequest createQueryRequest (@NonNull @Nonempty final String sQueryDefinition,
                                                 @NonNull @Nonempty final SlotType... aSlots)
  {
    ValueEnforcer.notEmpty (sQueryDefinition, "sQueryDefinition");
    ValueEnforcer.notEmptyNoNullValue (aSlots, "Slots");
    final QueryRequest ret = createEmptyQueryRequest ();

    final QueryType aQuery = new QueryType ();
    aQuery.setQueryDefinition (sQueryDefinition);
    for (final SlotType aSlot : aSlots)
      aQuery.addSlot (aSlot);
    ret.setQuery (aQuery);
    return ret;
  }

  @NonNull
  public static QueryResponse createEmptyQueryResponse (@NonNull final ERegRepResponseStatus eStatus)
  {
    ValueEnforcer.notNull (eStatus, "Status");

    final QueryResponse ret = new QueryResponse ();
    ret.setStatus (eStatus.getID ());
    return ret;
  }

  @NonNull
  public static QueryResponse createQueryResponse (@NonNull final ERegRepResponseStatus eStatus,
                                                   @Nullable final String sRequestID,
                                                   @NonNull @Nonempty final SlotType... aSlots)
  {
    final QueryResponse ret = createEmptyQueryResponse (eStatus);
    ret.setRequestId (sRequestID);
    for (final SlotType aSlot : aSlots)
      ret.addSlot (aSlot);
    return ret;
  }
}
