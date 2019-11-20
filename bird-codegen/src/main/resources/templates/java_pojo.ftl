<#--
    model = DataObject.class
-->
<#include "java_common.ftl">
package ${model.packageName};

/**
 * <p>${model.label!}</p>
 *
 * @author maven-plugin
 * ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
${visibility(model)} class ${model.name} {
<#if model.properties??>
    <#assign fields = model.properties>

    <#list fields as field>
    <@property field=field/>

    </#list>
    <#list fields as field>
    <@setter field=field/>

    <@getter field=field/>

    </#list>
    @Override
    public String toString() {
        return "${model.name}{" +
                <#list fields as f>
                "${f.name}=" + ${f.name} +<#if f_has_next> "," +</#if>
                </#list>
                "}";
    }
</#if>
}