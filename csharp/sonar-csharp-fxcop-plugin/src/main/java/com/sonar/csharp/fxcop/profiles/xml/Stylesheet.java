/*
 * Sonar C# Plugin :: FxCop
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

package com.sonar.csharp.fxcop.profiles.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * The Stylesheet to use for a FxCop report.
 */
@XmlType(name = "Stylesheet")
public class Stylesheet {

  @XmlAttribute(name = "Apply")
  private String apply = "False";
  @XmlValue
  private String path = "c:\\program files\\microsoft fxcop 1.36\\Xml\\FxCopReport.xsl";

  /**
   * Constructs a @link{Stylesheet}.
   */
  public Stylesheet() {
  }

  /**
   * Returns the apply.
   * 
   * @return The apply to return.
   */
  public String getApply() {
    return this.apply;
  }

  /**
   * Sets the apply.
   * 
   * @param apply
   *          The apply to set.
   */
  public void setApply(String apply) {
    this.apply = apply;
  }

  /**
   * Returns the path.
   * 
   * @return The path to return.
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Sets the path.
   * 
   * @param path
   *          The path to set.
   */
  public void setPath(String path) {
    this.path = path;
  }

}
