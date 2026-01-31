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
package com.helger.regrep.jaxb;

import javax.xml.namespace.QName;

import com.helger.regrep.CRegRep4;
import com.helger.regrep.lcm.UpdateObjectsRequest;

/**
 * JAXB marshaller for RegRep4 UpdateObjectsRequest objects.
 *
 * @author Philip Helger
 * @since 3.0.0
 */
public class RegRep4UpdateObjectsMarshaller extends AbstractRegRepJAXBMarshaller <UpdateObjectsRequest>
{
  public RegRep4UpdateObjectsMarshaller ()
  {
    super (UpdateObjectsRequest.class,
           CRegRep4.getAllXSDsLCM (),
           new QName ("urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0", "UpdateObjectsRequest"));
  }
}
