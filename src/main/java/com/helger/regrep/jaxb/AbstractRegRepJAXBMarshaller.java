/*
 * Copyright (C) 2021-2024 Philip Helger (www.helger.com)
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

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.jaxb.GenericJAXBMarshaller;
import com.helger.regrep.RegRep4NamespaceContext;

import jakarta.xml.bind.JAXBElement;

/**
 * Abstract base class for RegRep4 JAXB marshalling.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        The JAXB type to be marshaled
 */
public abstract class AbstractRegRepJAXBMarshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  /**
   * Constructor with XSD paths.
   *
   * @param aType
   *        The class of the JAXB document implementation type. May not be
   *        <code>null</code>.
   * @param aXSDs
   *        The XSDs used to validate document. May be <code>null</code> or
   *        empty indicating, that no XSD check is needed.
   * @param aJAXBElementWrapper
   *        Wrap the passed domain object into a {@link JAXBElement} for
   *        marshalling (writing). This can usually be done using the
   *        respective's package ObjectFactory implementation. May not be
   *        <code>null</code>.
   */
  protected AbstractRegRepJAXBMarshaller (@Nonnull final Class <JAXBTYPE> aType,
                                          @Nullable final List <? extends ClassPathResource> aXSDs,
                                          @Nonnull final Function <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper)
  {
    super (aType, aXSDs, aJAXBElementWrapper);
    setNamespaceContext (RegRep4NamespaceContext.getInstance ());
  }

  /**
   * Constructor with qualified element.
   *
   * @param aType
   *        The class of the JAXB document implementation type. May not be
   *        <code>null</code>.
   * @param aXSDs
   *        The XSDs used to validate document. May be <code>null</code> or
   *        empty indicating, that no XSD check is needed.
   * @param aQName
   *        the qualified element name to be used. May not be <code>null</code>.
   */
  protected AbstractRegRepJAXBMarshaller (@Nonnull final Class <JAXBTYPE> aType,
                                          @Nullable final List <? extends ClassPathResource> aXSDs,
                                          @Nonnull final QName aQName)
  {
    this (aType, aXSDs, createSimpleJAXBElement (aQName, aType));
  }
}
