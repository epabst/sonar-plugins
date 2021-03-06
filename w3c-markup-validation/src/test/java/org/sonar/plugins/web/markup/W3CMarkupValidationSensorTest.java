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
package org.sonar.plugins.web.markup;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;
import org.sonar.api.rules.Violation;
import org.sonar.plugins.web.markup.constants.MarkupValidatorConstants;

/**
 * @author Matthijs Galesloot
 */
public class W3CMarkupValidationSensorTest extends AbstractWebScannerPluginTester {

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Mock
  private SensorContext sensorContext;

  @Test
  public void markupValidationPlugin() {
    W3CMarkupValidationPlugin webscannerPlugin = new W3CMarkupValidationPlugin();
    assertNull(webscannerPlugin.getKey());
    assertNull(webscannerPlugin.getName());
    assertNull(webscannerPlugin.getDescription());
    assertEquals(5, webscannerPlugin.getExtensions().size());
  }

  @Test
  public void testSensor() throws Exception {
    File pomFile = new File(W3CMarkupSensor.class.getResource("/pom.xml").toURI());
    final Project project = loadProjectFromPom(pomFile);
    project.getConfiguration().setProperty(MarkupValidatorConstants.PAUSE_BETWEEN_VALIDATIONS, "999");

    W3CMarkupSensor sensor = new W3CMarkupSensor(project, createStandardRulesProfile(), new MarkupRuleFinder());

    assertTrue(sensor.shouldExecuteOnProject(project));

    sensor.analyse(project, sensorContext);

    Mockito.verify(sensorContext, Mockito.atLeast(1)).saveViolation((Violation) Mockito.any());
  }
}
