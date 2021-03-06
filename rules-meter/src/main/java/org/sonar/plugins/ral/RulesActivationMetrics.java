/*
 * Sonar Rules Meter Plugin
 * Copyright (C) 2009 SonarSource
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

package org.sonar.plugins.ral;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.Arrays;
import java.util.List;

public class RulesActivationMetrics implements Metrics {

  public static final Metric RULES_ACTIVATION_LEVEL = new Metric("rules-activation-level", "Rules Activation Level", "Level of activation of the profile", Metric.ValueType.PERCENT, 1, Boolean.TRUE, CoreMetrics.DOMAIN_RULES);
  public static final Metric RULES_ACTIVATION_LEVEL_DISTRIBUTION = new Metric("rules-activation-distribution", "Rules Activation Level Distribution", "Distribution of activation level of the profile", Metric.ValueType.DATA, 0, Boolean.FALSE, CoreMetrics.DOMAIN_RULES);

  public List<Metric> getMetrics() {
    return Arrays.asList(RULES_ACTIVATION_LEVEL, RULES_ACTIVATION_LEVEL_DISTRIBUTION);
  }
}
