/*
 * Sonar W3C Markup Validation Plugin
 * Copyright (C) 2010 Matthijs Galesloot
 * dev@sonar.codehaus.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sonar.plugins.web.markup.validation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.sun.syndication.io.XmlReader;

/**
 * Detects charset using rome library.
 *
 * @author Matthijs Galesloot
 * @since 1.0
 */
public final class CharsetDetector {

  private CharsetDetector() {
    // cannot instantiate
  }

  /**
   * Find charset using rome toolkit.
   */
  public static String detect(File file) {
    XmlReader reader = null;
    try {
      reader = new XmlReader(file);
      return reader.getEncoding();
    } catch (IOException e) {
      return null;
    } finally {
      IOUtils.closeQuietly(reader);
    }
  }
}
