/*
 * Sonar C# Plugin :: StyleCop
 * Copyright (C) 2010 Jose Chillan, Alexandre Victoor and SonarSource
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

package org.sonar.plugins.csharp.stylecop.runner;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.BatchExtension;
import org.sonar.api.resources.ProjectFileSystem;

/**
 * Class that runs the StyleCop program.
 */
public class StyleCopRunner implements BatchExtension {

  private static final Logger LOG = LoggerFactory.getLogger(StyleCopRunner.class);

//  private StyleCopCommand command;

  /**
   * Constructs a {@link StyleCopRunner}.
   * 
   * @param configuration
   *          StyleCop configuration elements
   * @param fileSystem
   *          the file system of the project
   */
  public StyleCopRunner(Configuration configuration, ProjectFileSystem projectFileSystem) {
//    this.command = new StyleCopCommand(configuration, projectFileSystem);
  }

  /**
   * Launches the StyleCop program.
   * 
   * @param fxCopConfigFile
   *          the StyleCop config file to use
   */
  public void execute(File fxCopConfigFile) {
    LOG.debug("Executing StyleCop program");
    // command.setStyleCopConfigFile(fxCopConfigFile);
    // new CommandExecutor().execute(command.toArray(), command.getTimeoutMinutes() * 60);
  }

}
