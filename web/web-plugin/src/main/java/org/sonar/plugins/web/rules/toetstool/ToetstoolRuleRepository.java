/*
 * Copyright (C) 2010 Matthijs Galesloot
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

package org.sonar.plugins.web.rules.toetstool;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RulePriority;
import org.sonar.api.rules.RuleRepository;
import org.sonar.api.rules.RulesCategory;
import org.sonar.check.IsoCategory;
import org.sonar.plugins.web.language.Web;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public final class ToetstoolRuleRepository extends RuleRepository {

  @XStreamAlias("rule")
  public class ToetstoolRule {

    private String explanation;
    private String key;
    private RulePriority priority;
    private String remark;

    public String getExplanation() {
      return explanation;
    }

    public String getKey() {
      return key;
    }

    public RulePriority getPriority() {
      return priority;
    }

    public String getRemark() {
      return remark;
    }

    public void setExplanation(String explanation) {
      this.explanation = explanation;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }
  }

  @XStreamAlias("rules")
  public class ToetstoolRules {

    @XStreamImplicit(itemFieldName = "rule")
    public List<ToetstoolRule> rules;
  }

  private static final String ALL_RULES = "org/sonar/plugins/web/rules/toetstool/rules.xml";
  public static final String REPOSITORY_KEY = "ToetstoolValidation";

  public static final String REPOSITORY_NAME = "Toetstool Validation";

  private static final int RULENAME_MAX_LENGTH = 192;

  public ToetstoolRuleRepository() {
    super(ToetstoolRuleRepository.REPOSITORY_KEY, Web.KEY);
    setName(ToetstoolRuleRepository.REPOSITORY_NAME);
  }

  @Override
  public List<Rule> createRules() {
    List<Rule> rules = new ArrayList<Rule>();

    XStream xstream = new XStream();
    xstream.setClassLoader(getClass().getClassLoader());
    xstream.processAnnotations(ToetstoolRules.class);
    ToetstoolRules toetstoolRules = (ToetstoolRules) xstream.fromXML(getClass().getClassLoader().getResourceAsStream(ALL_RULES));
    for (ToetstoolRule toetstoolRule : toetstoolRules.rules) {
      Rule rule = Rule.create(REPOSITORY_KEY, toetstoolRule.getKey(),
          StringUtils.abbreviate(toetstoolRule.getRemark(), RULENAME_MAX_LENGTH));
      if (toetstoolRule.getExplanation() != null) {
        rule.setDescription(StringEscapeUtils.escapeHtml(toetstoolRule.getExplanation()));
      }
      rule.setPriority(toetstoolRule.getPriority());
      rule.setRulesCategory(RulesCategory.fromIsoCategory(IsoCategory.Usability));
      rules.add(rule);
    }
    return rules;
  }
}