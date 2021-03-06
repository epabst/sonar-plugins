/*
 * Sonar Flex Plugin
 * Copyright (C) 2010 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */

package org.sonar.plugins.flex.colorizer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class FlexKeywords {

  private static final Set<String> FLEX_KEYWORDS = new HashSet<String>();

  static {
    Collections.addAll(FLEX_KEYWORDS,
        "as",
        "break",
        "case",
        "catch",
        "class",
        "const",
        "continue",
        "default",
        "delete",
        "do",
        "else",
        "extends",
        "false",
        "finally",
        "for",
        "function",
        "if",
        "implements",
        "import",
        "in",
        "instanceof",
        "interface",
        "internal",
        "is",
        "native",
        "new",
        "null",
        "package",
        "private",
        "protected",
        "public",
        "return",
        "super",
        "switch",
        "this",
        "throw",
        "to",
        "true",
        "try",
        "typeof",
        "use",
        "var",
        "void",
        "while",
        "with",
        "each",
        "get",
        "set",
        "namespace",
        "include",
        "dynamic",
        "final",
        "native",
        "override",
        "static",
        "abstract",
        "boolean",
        "byte",
        "cast",
        "char",
        "debugger",
        "double",
        "enum",
        "export",
        "float",
        "goto",
        "intrinsic",
        "long",
        "prototype",
        "short",
        "synchronized",
        "throws",
        "to",
        "transient",
        "type",
        "virtual",
        "volatile"
    );
  }

  private FlexKeywords() {
  }

  public static Set<String> get() {
    return Collections.unmodifiableSet(FLEX_KEYWORDS);
  }
}
