/*
 * Sonar JaCoCo Plugin
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

package org.sonar.plugins.jacoco;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.sonar.api.resources.InputFile;
import org.sonar.api.resources.Java;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.ProjectFileSystem;

import java.util.Collections;
import java.util.List;

public class JacocoMavenInitializerTest {
  private JaCoCoMavenPluginHandler mavenPluginHandler;
  private JacocoMavenInitializer initializer;
  private static final List<InputFile> INPUT_FILE_LIST0 = Collections.emptyList();
  private static final List<InputFile> INPUT_FILE_LIST1 = Collections.singletonList(mock(InputFile.class));

  @Before
  public void setUp() {
    mavenPluginHandler = mock(JaCoCoMavenPluginHandler.class);
    initializer = new JacocoMavenInitializer(mavenPluginHandler);
  }

  @Test
  public void shouldDoNothing() {
    Project project = mockProject();
    initializer.execute(project);
    verifyNoMoreInteractions(project);
    verifyNoMoreInteractions(mavenPluginHandler);
  }

  @Test
  public void shouldExecuteMaven() {
    Project project = mockProject();
    when(project.getFileSystem().testFiles(argThat(is(Java.KEY)), argThat(is("scala")))).thenReturn(INPUT_FILE_LIST1);
    when(project.getAnalysisType()).thenReturn(Project.AnalysisType.DYNAMIC);

    assertThat(initializer.shouldExecuteOnProject(project), is(true));
    assertThat(initializer.getMavenPluginHandler(project), instanceOf(JaCoCoMavenPluginHandler.class));
  }

  @Test
  public void shouldNotExecuteMavenWhenReuseReports() {
    Project project = mockProject();
    when(project.getFileSystem().testFiles(argThat(is(Java.KEY)), argThat(is("scala")))).thenReturn(INPUT_FILE_LIST1);
    when(project.getAnalysisType()).thenReturn(Project.AnalysisType.REUSE_REPORTS);

    assertThat(initializer.shouldExecuteOnProject(project), is(false));
  }

  @Test
  public void shouldNotExecuteMavenWhenNoTests() {
    Project project = mockProject();
    when(project.getFileSystem().testFiles(argThat(is(Java.KEY)), argThat(is("scala")))).thenReturn(INPUT_FILE_LIST0);
    when(project.getAnalysisType()).thenReturn(Project.AnalysisType.DYNAMIC);

    assertThat(initializer.shouldExecuteOnProject(project), is(false));
  }

  private Project mockProject() {
    Project project = mock(Project.class);
    ProjectFileSystem projectFileSystem = mock(ProjectFileSystem.class);
    when(project.getFileSystem()).thenReturn(projectFileSystem);
    return project;
  }
}
