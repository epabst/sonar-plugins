/*
 * Sonar Web Plugin
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

package org.sonar.plugins.web.checks.style;

import static junit.framework.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;
import org.sonar.plugins.web.checks.AbstractCheckTester;
import org.sonar.plugins.web.visitor.WebSourceCode;

/**
 * @author Matthijs Galesloot
 */
public class InlineStyleCheckTest extends AbstractCheckTester {

  @Test
  public void violateInlineStyleCheck() throws FileNotFoundException {

    String fragment = "<head><style>nadadana</style></head>";
    Reader reader = new StringReader(fragment);
    WebSourceCode sourceCode = parseAndCheck(reader,  InlineStyleCheck.class);

    assertEquals("Incorrect number of violations", 1, sourceCode.getViolations().size());
  }
}
