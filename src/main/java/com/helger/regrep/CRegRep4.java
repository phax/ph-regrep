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
package com.helger.regrep;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.mime.EMimeContentType;
import com.helger.commons.mime.IMimeType;
import com.helger.xsds.wsaddr.CWSAddr;
import com.helger.xsds.xlink.CXLink;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Contains all the constants for RegRep XSD handling.
 *
 * @author Philip Helger
 */
@Immutable
public final class CRegRep4
{
  public static final String DEFAULT_PREFIX_RIM = "rim";
  public static final String NAMESPACE_URI_RIM = "urn:oasis:names:tc:ebxml-regrep:xsd:rim:4.0";

  public static final String DEFAULT_PREFIX_RS = "rs";
  public static final String NAMESPACE_URI_RS = "urn:oasis:names:tc:ebxml-regrep:xsd:rs:4.0";

  public static final String DEFAULT_PREFIX_LCM = "lcm";
  public static final String NAMESPACE_URI_LCM = "urn:oasis:names:tc:ebxml-regrep:xsd:lcm:4.0";

  public static final String DEFAULT_PREFIX_QUERY = "query";
  public static final String NAMESPACE_URI_QUERY = "urn:oasis:names:tc:ebxml-regrep:xsd:query:4.0";

  public static final String DEFAULT_PREFIX_SPI = "spi";
  public static final String NAMESPACE_URI_SPI = "urn:oasis:names:tc:ebxml-regrep:xsd:spi:4.0";

  // Since beta4
  public static final IMimeType MIME_TYPE_EBRS_XML = EMimeContentType.APPLICATION.buildMimeType ("x-ebrs+xml");

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CRegRep4.class.getClassLoader ();
  }

  private static final String PREFIX = "external/schemas/regrep4/";

  // Note: requires rim, rs
  @Nonnull
  public static ClassPathResource getXSDResourceLCM ()
  {
    return new ClassPathResource (PREFIX + "lcm.xsd", _getCL ());
  }

  // Note: requires rim, rs
  @Nonnull
  public static ClassPathResource getXSDResourceQuery ()
  {
    return new ClassPathResource (PREFIX + "query.xsd", _getCL ());
  }

  // Note: requires xlink, ws-addr
  @Nonnull
  public static ClassPathResource getXSDResourceRIM ()
  {
    return new ClassPathResource (PREFIX + "rim.xsd", _getCL ());
  }

  // Note: requires rim
  @Nonnull
  public static ClassPathResource getXSDResourceRS ()
  {
    return new ClassPathResource (PREFIX + "rs.xsd", _getCL ());
  }

  // Note: requires rim, rs
  @Nonnull
  public static ClassPathResource getXSDResourceSPI ()
  {
    return new ClassPathResource (PREFIX + "spi.xsd", _getCL ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDIncludes ()
  {
    return new CommonsArrayList <> (CXML_XSD.getXSDResource (),
                                    CXLink.getXSDResource (),
                                    CWSAddr.getXSDResource (),
                                    getXSDResourceRIM (),
                                    getXSDResourceRS ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDsLCM ()
  {
    final ICommonsList <ClassPathResource> ret = getAllXSDIncludes ();
    ret.add (getXSDResourceLCM ());
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDsQuery ()
  {
    final ICommonsList <ClassPathResource> ret = getAllXSDIncludes ();
    ret.add (getXSDResourceQuery ());
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDsSPI ()
  {
    final ICommonsList <ClassPathResource> ret = getAllXSDIncludes ();
    ret.add (getXSDResourceSPI ());
    return ret;
  }

  @PresentForCodeCoverage
  private static final CRegRep4 INSTANCE = new CRegRep4 ();

  private CRegRep4 ()
  {}
}
