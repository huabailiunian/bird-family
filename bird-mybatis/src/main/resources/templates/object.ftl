package ${basePackage}.object;

/**
 * <p>${display!}</p>
 *
 * @author maven-plugin
 * ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${objectName} {
<#if fields??>

    <#list fields as f>
    /**
     * <p>${f.display!}</p>
     */
    private ${f.fieldType} ${f.fieldName};

    </#list>
    <#list fields as f>
    public void set${f.fieldName?cap_first}(${f.fieldType} ${f.fieldName}) {
        this.${f.fieldName} = ${f.fieldName};
    }

    public ${f.fieldType} get${f.fieldName?cap_first}() {
        return this.${f.fieldName};
    }

    </#list>
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                <#list fields as f>
                "${f.fieldName}=" + ${f.fieldName} +<#if f_has_next> "," +</#if>
                </#list>
                "}";
    }
</#if>
}