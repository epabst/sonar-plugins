/*
 * Sonar Ant Task
 * Copyright (C) 2011 SonarSource
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

package org.sonar.ant;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.apache.tools.ant.Project;
import org.junit.Test;

import java.io.File;

public class SonarTaskTest {
  @Test
  public void shouldCheckVersion() {
    assertThat(SonarTask.isVersionPriorTo2Dot8("1.0"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.0"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.1"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.2"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.3"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.4"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.4.1"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.5"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.6"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.7"), is(true));
    assertThat(SonarTask.isVersionPriorTo2Dot8("2.8"), is(false));
  }

  @Test
  public void shouldGetVersion() {
    String version = SonarTask.getTaskVersion();
    assertThat(version, containsString("."));
    assertThat(version, not(containsString("$")));
  }

  @Test
  public void shouldReturnDefaultValues() {
    SonarTask task = new SonarTask();
    task.setProject(new Project());
    assertThat(task.getServerUrl(), is("http://localhost:9000"));
    assertThat(task.getWorkDir(), is(new File(task.getProject().getBaseDir(), ".sonar")));
  }

}
