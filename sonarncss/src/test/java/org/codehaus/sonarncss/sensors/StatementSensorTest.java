/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2009 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.codehaus.sonarncss.sensors;

import org.codehaus.sonarncss.SonarNcss;
import static org.codehaus.sonarncss.SonarNcssTestUtils.getFile;
import org.codehaus.sonarncss.entities.JavaType;
import org.codehaus.sonarncss.entities.Resource;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StatementSensorTest {

  @Test
  public void testNoStatements() {
    Resource res = SonarNcss.analyze(getFile("/metrics/statements/NoStatements.java"));
    System.out.println(res);
    assertEquals(15, res.measures.getStatements());

    Resource simpleIf = res.find("simpleIf()", JavaType.METHOD);
    assertEquals(5, simpleIf.measures.getStatements());
  }
}
