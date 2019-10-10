<#assign entityName = engine.entityNameGen(model.name)>
package ${engine.entityPkg};

/**
 * <p>${model.display!}</p>
 *
 * @author maven-plugin
 * ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${entityName} {
<#if model.columns??>
    <#assign fields = model.columns>

    <#list fields as field>
    /**
     * <p>${field.display!}</p>
     */
    private ${engine.fieldType(field.type)} ${engine.fieldNameGen(field.name)};

    </#list>
    <#list fields as field>
    <#assign name = engine.fieldNameGen(field.name)>
    <#assign type = engine.fieldType(field.type)>
    public void set${name?cap_first}(${type} ${name}) {
        this.${name} = ${name};
    }

    public ${type} get${name?cap_first}() {
        return this.${name};
    }

    </#list>
    @Override
    public String toString() {
        return "${entityName}{" +
                <#list fields as f>
                "${engine.fieldNameGen(f.name)}=" + ${engine.fieldNameGen(f.name)} +<#if f_has_next> "," +</#if>
                </#list>
                "}";
    }
</#if>
}