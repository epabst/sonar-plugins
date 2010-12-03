/*
 * Sonar W3CMarkup Plugin
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

package org.sonar.plugins.webscanner.w3cmarkup;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;
import org.sonar.api.resources.Project;
import org.sonar.plugins.webscanner.w3cmarkup.HtmlViolationFilter;

public class MessageFilterTest {

  @Test
  public void checkPatterns() {
    List<String> expressions = Arrays.asList("127:.*required attribute \"rows\" not specified",
        "065:.*document type does not allow element \"input\" here");
    Project project = new Project("");
    project.setConfiguration(new PropertiesConfiguration());
    project.getConfiguration().addProperty(HtmlViolationFilter.EXCLUDE_VIOLATIONS, expressions);
    HtmlViolationFilter htmlViolationFilter = new HtmlViolationFilter(project);

    String id = "127";
    String message = "required attribute \"rows\" not specified";
    assertFalse(htmlViolationFilter.accept(id, message));

    id = "128";
    message = "required attribute \"rows\" not specified";
    assertTrue(htmlViolationFilter.accept(id, message));

    id = "065";
    message = "document type does not allow element \"input\" here; missing one of \"p\", \"h1\", \"h2\", \"h3\", \"h4\", "
        + "\"h5\", \"h6\", \"div\", \"pre\", \"address\", \"fieldset\", \"ins\", \"del\" start-tag";
    assertFalse(htmlViolationFilter.accept(id, message));
  }
}