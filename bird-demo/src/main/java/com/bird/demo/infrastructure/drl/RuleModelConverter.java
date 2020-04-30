package com.bird.demo.infrastructure.drl;

import com.bird.commons.tools.CollectionTools;
import com.bird.demo.infrastructure.drl.converter.PatternConverter;
import com.bird.demo.infrastructure.drl.element.RuleGlobal;
import com.bird.demo.infrastructure.drl.element.RuleProperty;
import com.bird.demo.infrastructure.drl.element.lhs.IPattern;
import com.bird.demo.infrastructure.drl.enums.ElementType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author youly
 * 2019/8/2 14:29
 */
public class RuleModelConverter {

    private static Map<ElementType, IConverter> converterMap = new HashMap<>();

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        converterMap.put(ElementType.PATTERN, new PatternConverter());
    }

    public static String converter(RuleModel ruleModel) {
        StringBuilder builder = new StringBuilder();

        //package
        builder.append("package ").append(ruleModel.getPkg()).append(";\n\n");

        //imports
        Set<String> imports = ruleModel.getImports();
        if (CollectionTools.isNotEmpty(imports)) {
            for (String anImport : imports) {
                builder.append("import ").append(anImport).append(";\n");
            }
            builder.append("\n");
        }

        //globals
        List<RuleGlobal> globals = ruleModel.getGlobals();
        if (CollectionTools.isNotEmpty(globals)) {
            globals.stream().map(RuleGlobal::toString).forEach(builder::append);
            builder.append("\n");
        }

        //ruleName
        String ruleName = ruleModel.getRuleName();
        if (StringUtils.isBlank(ruleName)) {
            ruleName = "rule_test";
        }
        builder.append("rule \"").append(ruleName).append("\"\n");

        //properties
        List<RuleProperty> properties = ruleModel.getProperties();
        if (CollectionTools.isNotEmpty(properties)){
            for (RuleProperty property : properties) {
                String temp = property.toString();
                builder.append("\t").append(temp).append("\n");
            }
        }

        //patterns
        builder.append("\twhen\n");
        List<IPattern> patterns = ruleModel.getPatterns();
        if (CollectionTools.isNotEmpty(patterns)) {
            IConverter converter = converterMap.get(patterns.get(0).getElementType());
            for (IPattern pattern : patterns) {
                String patternStr = converter.process(pattern);
                builder.append("\t").append(patternStr).append(";\n");
            }
        }

        //actions
        builder.append("\tthen\n");


        //end
        builder.append("end\n");

        return builder.toString();
    }

    public static RuleModel readFromJson(JsonNode jsonNode) {
        RuleModel ruleModel = new RuleModel();

        String pkg = jsonNode.get("package").asText();
        if (StringUtils.isNotBlank(pkg)) {
            ruleModel.setPkg(pkg);
        }

        String imports = jsonNode.get("imports").asText();

        return ruleModel;
    }
}
