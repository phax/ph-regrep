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
package com.helger.regrep;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.state.ESuccess;
import com.helger.jaxb.builder.IJAXBDocumentType;
import com.helger.jaxb.builder.JAXBDocumentType;
import com.helger.jaxb.builder.JAXBWriterBuilder;
import com.helger.regrep.lcm.RemoveObjectsRequest;
import com.helger.regrep.lcm.SubmitObjectsRequest;
import com.helger.regrep.lcm.UpdateObjectsRequest;
import com.helger.regrep.query.QueryRequest;
import com.helger.regrep.query.QueryResponse;
import com.helger.regrep.rs.RegistryExceptionType;
import com.helger.regrep.spi.CatalogObjectsRequest;
import com.helger.regrep.spi.CatalogObjectsResponse;
import com.helger.regrep.spi.FilterObjectsRequest;
import com.helger.regrep.spi.FilterObjectsResponse;
import com.helger.regrep.spi.ValidateObjectsRequest;
import com.helger.regrep.spi.ValidateObjectsResponse;

/**
 * A class to write RegRep request and response documents in a structured way.
 * Use the static factory methods to create the correct instances.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        The implementation type.
 */
public class RegRep4Writer <JAXBTYPE> extends JAXBWriterBuilder <JAXBTYPE, RegRep4Writer <JAXBTYPE>>
{
  /**
   * Constructor with an arbitrary document type.
   *
   * @param eDocType
   *        Document type to be used. May not be <code>null</code>.
   */
  public RegRep4Writer (@Nonnull final ERegRep4XMLDocumentType eDocType)
  {
    super (eDocType);
    setNamespaceContext (RegRep4NamespaceContext.getInstance ());
  }

  private RegRep4Writer (@Nonnull final IJAXBDocumentType eDocType)
  {
    super (eDocType);
    setNamespaceContext (RegRep4NamespaceContext.getInstance ());
  }

  // Hack to disable package name check for QueryException
  @Override
  @Nonnull
  public ESuccess write (@Nonnull final JAXBTYPE aJAXBDocument, @Nonnull final IJAXBMarshaller <JAXBTYPE> aMarshallerFunc)
  {
    ValueEnforcer.notNull (aJAXBDocument, "JAXBDocument");
    ValueEnforcer.notNull (aMarshallerFunc, "MarshallerFunc");

    try
    {
      final Marshaller aMarshaller = createMarshaller ();

      // Customize on demand
      final Consumer <? super Marshaller> aCustomizer = getMarshallerCustomizer ();
      if (aCustomizer != null)
        aCustomizer.accept (aMarshaller);

      // start marshalling
      final JAXBElement <JAXBTYPE> aJAXBElement = createJAXBElement (aJAXBDocument);
      aMarshallerFunc.doMarshal (aMarshaller, aJAXBElement);
      return ESuccess.SUCCESS;
    }
    catch (final JAXBException ex)
    {
      exceptionCallbacks ().forEach (x -> x.onException (ex));
    }
    return ESuccess.FAILURE;
  }

  /**
   * Create a writer builder for {@link SubmitObjectsRequest}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <SubmitObjectsRequest> submitObjectsRequest ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.SUBMIT_OBJECTS_REQUEST);
  }

  /**
   * Create a writer builder for {@link UpdateObjectsRequest}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <UpdateObjectsRequest> updateObjectsRequest ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.UPDATE_OBJECTS_REQUEST);
  }

  /**
   * Create a writer builder for {@link RemoveObjectsRequest}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <RemoveObjectsRequest> removeObjectsRequest ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.REMOVE_OBJECTS_REQUEST);
  }

  /**
   * Create a writer builder for {@link QueryRequest}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <QueryRequest> queryRequest ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.QUERY_REQUEST);
  }

  /**
   * Create a writer builder for {@link QueryRequest}.
   *
   * @param aAdditionalXSDs
   *        Additional XSDs
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <QueryRequest> queryRequest (@Nonnull final ClassPathResource... aAdditionalXSDs)
  {
    return queryRequest (new CommonsArrayList <> (aAdditionalXSDs));
  }

  /**
   * Create a writer builder for {@link QueryRequest}.
   *
   * @param aAdditionalXSDs
   *        Additional XSDs
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <QueryRequest> queryRequest (@Nonnull final Iterable <? extends ClassPathResource> aAdditionalXSDs)
  {
    final ICommonsList <ClassPathResource> aXSDs = CRegRep4.getAllXSDsQuery ().getClone ();
    aXSDs.addAll (aAdditionalXSDs);
    return new RegRep4Writer <> (new JAXBDocumentType (com.helger.regrep.query.QueryRequest.class, aXSDs, null));
  }

  /**
   * Create a writer builder for {@link QueryResponse}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <QueryResponse> queryResponse ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.QUERY_RESPONSE);
  }

  /**
   * Create a writer builder for {@link QueryResponse}.
   *
   * @param aAdditionalXSDs
   *        Additional XSDs
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <QueryResponse> queryResponse (@Nonnull final ClassPathResource... aAdditionalXSDs)
  {
    return queryResponse (new CommonsArrayList <> (aAdditionalXSDs));
  }

  /**
   * Create a writer builder for {@link QueryResponse}.
   *
   * @param aAdditionalXSDs
   *        Additional XSDs
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <QueryResponse> queryResponse (@Nonnull final Iterable <? extends ClassPathResource> aAdditionalXSDs)
  {
    final ICommonsList <ClassPathResource> aXSDs = CRegRep4.getAllXSDsQuery ().getClone ();
    aXSDs.addAll (aAdditionalXSDs);
    return new RegRep4Writer <> (new JAXBDocumentType (com.helger.regrep.query.QueryResponse.class, aXSDs, null));
  }

  /**
   * Create a writer builder for {@link RegistryExceptionType}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <RegistryExceptionType> registryException ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.REGISTRY_EXCEPTION);
  }

  /**
   * Create a writer builder for {@link ValidateObjectsRequest}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <ValidateObjectsRequest> validateObjectsRequest ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.VALIDATE_OBJECTS_REQUEST);
  }

  /**
   * Create a writer builder for {@link ValidateObjectsResponse}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <ValidateObjectsResponse> validateObjectsResponse ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.VALIDATE_OBJECTS_RESPONSE);
  }

  /**
   * Create a writer builder for {@link CatalogObjectsRequest}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <CatalogObjectsRequest> catalogObjectsRequest ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.CATALOG_OBJECTS_REQUEST);
  }

  /**
   * Create a writer builder for {@link CatalogObjectsResponse}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <CatalogObjectsResponse> catalogObjectsResponse ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.CATALOG_OBJECTS_RESPONSE);
  }

  /**
   * Create a writer builder for {@link FilterObjectsRequest}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <FilterObjectsRequest> filterObjectsRequest ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.FILTER_OBJECTS_REQUEST);
  }

  /**
   * Create a writer builder for {@link FilterObjectsResponse}.
   *
   * @return The builder and never <code>null</code>
   */
  @Nonnull
  public static RegRep4Writer <FilterObjectsResponse> filterObjectsResponse ()
  {
    return new RegRep4Writer <> (ERegRep4XMLDocumentType.FILTER_OBJECTS_RESPONSE);
  }
}
