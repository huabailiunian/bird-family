package com.bird.demo.infrastructure.drl;

import com.bird.demo.infrastructure.drl.element.RuleGlobal;
import com.bird.demo.infrastructure.drl.element.RuleProperty;
import com.bird.demo.infrastructure.drl.element.lhs.IPattern;
import com.bird.demo.infrastructure.drl.rhs.IAction;

import java.util.List;
import java.util.Set;

/**
 * @author youly
 * 2019/8/2 10:16
 */
public class RuleModel {

    private String pkg = "drl.def";
    private Set<String> imports;
    private List<RuleGlobal> globals;
    private String ruleName;
    private List<RuleProperty> properties;
    private List<IPattern> patterns;
    private List<IAction> actions;

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public Set<String> getImports() {
        return imports;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
    }

    public List<RuleGlobal> getGlobals() {
        return globals;
    }

    public void setGlobals(List<RuleGlobal> globals) {
        this.globals = globals;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<RuleProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<RuleProperty> properties) {
        this.properties = properties;
    }

    public List<IPattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<IPattern> patterns) {
        this.patterns = patterns;
    }

    public List<IAction> getActions() {
        return actions;
    }

    public void setActions(List<IAction> actions) {
        this.actions = actions;
    }


    @Override
    public String toString() {
        return "RuleModel{" +
                "pkg='" + pkg + '\'' +
                ", imports=" + imports +
                ", globals=" + globals +
                ", ruleName='" + ruleName + '\'' +
                ", properties=" + properties +
                ", patterns=" + patterns +
                ", actions=" + actions +
                '}';
    }
}
