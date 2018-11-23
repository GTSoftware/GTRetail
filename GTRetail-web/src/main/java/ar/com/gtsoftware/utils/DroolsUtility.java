/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.utils;


import ar.com.gtsoftware.rules.OfertaDto;
import org.drools.core.spi.KnowledgeHelper;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.utils.KieHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility to operate Drools on runtime.
 *
 * @author Gabriel Stelmach <gabriel_stelmach@hotmail.com>
 * @since 1.0
 */
public final class DroolsUtility {

    private static final Logger LOG = Logger.getLogger(DroolsUtility.class.getName());
    /**
     * Debug tool to show what is happening over each triggered execution.<br>
     * Name of rule trigger as well the object inspected are printed.
     *
     * @param helper Injected when a consequence is fired.
     */
    public static void debug(final KnowledgeHelper helper) {
        LOG.log(Level.FINE,"Triggered rule: " + helper.getRule().getName());

        if (helper.getMatch() != null && helper.getMatch().getObjects() != null) {
            for (Object object : helper.getMatch().getObjects()) {
                LOG.log(Level.FINE,"Data object: " + object);
            }
        }
    }

    /**
     * Loads a session to execute rules in memory using a template file.
     *
     * @param rules        List of rules to be compiled.
     * @param templatePath Relative path to template file describing the rule's pattern.
     * @return Session for execution of rules.
     * @throws Exception
     */
    public static StatelessKieSession loadSession(List<OfertaDto> rules, String templatePath) throws Exception {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>(rules.size());

        for (OfertaDto rule : rules) {
            maps.add(rule.toMap());
        }

        return loadSession(templatePath, maps);
    }

    /**
     * Loads a session to execute rules in memory using a template file.
     *
     * @param templatePath      Relative path to template file describing the rule's pattern.
     * @param rulesAsParameters List of maps representing each rule as a set of parameters.
     * @return Session for execution of rules.
     * @throws Exception
     */
    private static StatelessKieSession loadSession(String templatePath, List<Map<String, Object>> rulesAsParameters) throws Exception {
        ObjectDataCompiler compiler = new ObjectDataCompiler();
        //Compiles the list of rules using the template to create a readable Drools Rules Language
        String drl = compiler.compile(rulesAsParameters, Thread.currentThread().getContextClassLoader().getResourceAsStream(templatePath));

        LOG.log(Level.FINE,"drl:\n" + drl);

        KieHelper kieHelper = new KieHelper();
        kieHelper.kfs.write("src/main/resources/drools/templates/rule.drl", drl);
        return kieHelper.build().newStatelessKieSession();

    }
}
