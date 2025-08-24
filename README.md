# ph-regrep

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.helger/ph-regrep/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.helger/ph-regrep) 
[![javadoc](https://javadoc.io/badge2/com.helger/ph-regrep/javadoc.svg)](https://javadoc.io/doc/com.helger/ph-regrep)
[![CodeCov](https://codecov.io/gh/phax/ph-regrep/branch/master/graph/badge.svg)](https://codecov.io/gh/phax/ph-regrep)

OASIS RegRep (ebXML Registry) Java wrapper library.
It supports simple reading and writing of RegRep 4.0 documents.
It allows conversion between Java domain objects and XML representations.

It is licensed under Apache 2.0 license.

It requires at least Java 17 to be used.

# Functionality

With the class `com.helger.regrep.RegRep4Reader` arbitrary RegRep documents can be read.
With the class `com.helger.regrep.RegRep4Writer` arbitrary RegRep documents can be written.

# Maven usage

Add the following to your pom.xml to use this artifact, replacing `x.y.z` with the effective version number:

```xml
<dependency>
  <groupId>com.helger</groupId>
  <artifactId>ph-regrep</artifactId>
  <version>x.y.z</version>
</dependency>
```

# References

* [OASIS ebXML RegRep Version 4.0 - Part 0: Overview Document](http://docs.oasis-open.org/regrep/regrep-core/v4.0/regrep-core-overview-v4.0.html)

# News and noteworthy

v4.0.0 - 2025-08-24
* Requires Java 17 as the minimum version
* Updated to ph-commons 12.0.0
* Removed all deprecated methods marked for removal
* Switched JAXB Maven plugin to `org.jvnet.jaxb:jaxb-maven-plugin` 

v3.0.0 - 2023-04-22
* Moved XSDs into folder `external/...`
* Deprecated classes `ERegRep4XMLDocumentType`, `RegRep4Reader` and `RegRep4Writer`
* Added new package `com.helger.regrep.jaxb` with all the specific marshallers

v2.0.0 - 2023-01-08
* Using Java 11 as the baseline
* Updated to ph-commons 11
* Using JAXB 4.0 as the baseline

v1.2.0 - 2021-05-02
* Updated to ph-commons 10.1

v1.1.0 - 2021-03-21
* Updated to ph-commons 10

v1.0.1 - 2021-03-18
* Updated to ph-commons 9.5.5
* Changed the JAXB binding for `xs:dateTime` to `java.time.OffsetDateTime`

v1.0.0 - 2021-02-17
* Initial version

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
It is appreciated if you star the GitHub project if you like it.