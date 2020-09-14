# lexorank4j 
![build](https://github.com/pravin-raha/lexorank4j/workflows/.github/workflows/maven.yml/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.pravin-raha/lexorank4j/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pravin-raha/lexorank4j/badge.svg)

A reference implementation of a list ordering system like JIRA's Lexorank algorithm.

## Get lexorank4j library
### You can add lexorank4j to your project as maven dependency

For maven:
```
<dependency>
  <groupId>com.github.pravin-raha</groupId>
  <artifactId>lexorank4j</artifactId>
  <version>0.1.0</version>
</dependency>
```

For sbt:
```
libraryDependencies += "com.github.pravin-raha" % "lexorank4j" % "0.1.0"
```

### You can build lexorank4j from sources
```shell script
git clone https://github.com/pravin-raha/lexorank4j.git
cd lexorank4j
mvn clean install
```

## Using

### Static methods

```java
// min
LexoRank minLexoRank = LexoRank.min();
// max
LexoRank maxLexoRank = LexoRank.max();
// middle
LexoRank middleLexoRank = LexoRank.middle();
// parse
LexoRank parsedLexoRank = LexoRank.parse("0|0i0000:");
```

### Public methods

```java
// any lexoRank
LexoRank lexoRank = LexoRank.middle();

// generate next lexorank
LexoRank nextLexoRank = lexoRank.genNext();

// generate previous lexorank
LexoRank prevLexoRank = lexoRank.genPrev();

// toString
String lexoRankStr = lexoRank.toString();
```
## Credits
This library uses Open Source components. You can find the source code of their open source projects along with license information below. We acknowledge and are grateful to these developers for their contributions to open source.

Project: lexorank-dotnet https://github.com/kvandake/lexorank-dotnet

License (MIT)

## Copyright and license
Copyright 2020 Pravin Rahangdale

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.