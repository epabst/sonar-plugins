/*
 * Ada Sonar Plugin
 * Copyright (C) 2010 Akram Ben Aissi
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

package org.sonar.plugins.ada.gnat.metric;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.BatchExtension;
import org.sonar.api.utils.SonarException;

import org.sonar.plugins.ada.gnat.metric.xml.FileNode;
import org.sonar.plugins.ada.gnat.metric.xml.GlobalNode;
import org.sonar.plugins.ada.gnat.metric.xml.MetricNode;
import org.sonar.plugins.ada.gnat.metric.xml.MetricNodeConverter;
import org.sonar.plugins.ada.gnat.metric.xml.UnitNode;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

/**
 * The GnatMetricResultParser parses xml files generated by gnat metric tool.
 */
public class GnatMetricResultsParser implements BatchExtension {

  private static final Logger LOG = LoggerFactory.getLogger(GnatMetricResultsParser.class);

  /**
   * Gets the metrics.
   * 
   * @param report
   *          the report
   * @return the metrics
   */
  GlobalNode parse(File report) {
    LOG.debug("Parsing report file " + report);
    InputStream inputStream = null;
    String reportFilename = report.getAbsolutePath();
    try {
      XStream xstream = new XStream();
      // Migration Sonar 2.2
      xstream.setClassLoader(getClass().getClassLoader());
      xstream.processAnnotations(GlobalNode.class);
      xstream.processAnnotations(FileNode.class);
      xstream.processAnnotations(UnitNode.class);
      xstream.processAnnotations(MetricNode.class);
      xstream.registerConverter(new MetricNodeConverter());
      inputStream = new FileInputStream(report);
      return (GlobalNode) xstream.fromXML(inputStream);
    } catch (XStreamException e) {
      throw new SonarException("Error while parsing file " + reportFilename, e);
    } catch (IOException e) {
      throw new SonarException("Error while reading file " + reportFilename, e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
  }

}