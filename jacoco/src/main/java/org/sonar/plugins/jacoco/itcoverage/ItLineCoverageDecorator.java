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

package org.sonar.plugins.jacoco.itcoverage;

import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.batch.DependsUpon;
import org.sonar.api.measures.MeasureUtils;
import org.sonar.api.measures.Metric;

import java.util.Arrays;
import java.util.List;

/**
 * Copied from org.sonar.plugins.core.sensors.LineCoverageDecorator
 */
public class ItLineCoverageDecorator extends AbstractCoverageDecorator {
  @Override
  protected Metric getTargetMetric() {
    return JaCoCoItMetrics.IT_LINE_COVERAGE;
  }

  @DependsUpon
  public List<Metric> dependsUponMetrics() {
    return Arrays.asList(JaCoCoItMetrics.IT_UNCOVERED_LINES, JaCoCoItMetrics.IT_LINES_TO_COVER);
  }

  @Override
  protected Double countCoveredElements(DecoratorContext context) {
    double uncoveredLines = MeasureUtils.getValue(context.getMeasure(JaCoCoItMetrics.IT_UNCOVERED_LINES), 0.0);
    double lines = MeasureUtils.getValue(context.getMeasure(JaCoCoItMetrics.IT_LINES_TO_COVER), 0.0);
    return lines - uncoveredLines;
  }

  @Override
  protected Double countElements(DecoratorContext context) {
    return MeasureUtils.getValue(context.getMeasure(JaCoCoItMetrics.IT_LINES_TO_COVER), 0.0);
  }
}
