/*
 * Sonar Xml Plugin
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

package org.sonar.plugins.xml.rules;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.ActiveRule;
import org.sonar.api.utils.ValidationMessages;
import org.sonar.plugins.xml.checks.XmlSchemaCheck;

/**
 * Default XML profile.
 *
 * @author Matthijs Galesloot
 * @since 1.0
 */
public final class XhtmlTransitionalProfile extends ProfileDefinition {

  private final DefaultXmlProfile defaultXmlProfile;

  public XhtmlTransitionalProfile(DefaultXmlProfile defaultXmlProfile) {
    this.defaultXmlProfile = defaultXmlProfile;
  }


  @Override
  public RulesProfile createProfile(ValidationMessages validation) {
    RulesProfile rulesProfile = defaultXmlProfile.createProfile(validation);
    rulesProfile.setName("XHTML1-Transitional");

    setXhtmlSchema(rulesProfile);

    rulesProfile.setDefaultProfile(false);
    return rulesProfile;
  }

  private void setXhtmlSchema(RulesProfile rulesProfile) {
    ActiveRule activeRule = rulesProfile.getActiveRuleByConfigKey(XmlRulesRepository.REPOSITORY_KEY,
        XmlSchemaCheck.class.getSimpleName());
    activeRule.setParameter("schemas", "xhtml1-transitional");
  }
}