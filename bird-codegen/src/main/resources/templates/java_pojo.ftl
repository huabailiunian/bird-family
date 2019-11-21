<#--
    model = DataObject.class
-->
<#include "java_common.ftl">
<#assign varModel = model />
package ${varModel.packageName};

/**
 * <p>${varModel.label!}</p>
 *
 * @author code-plugin
 * ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
${visibility(varModel)} ${javaType(varModel)} ${varModel.name?cap_first}<#if varModel.supperClassName??> extends ${varModel.supperClassName}</#if>${resolveInterface(varModel)} {
<#if varModel.properties??>
    <#assign fields = varModel.properties>

    <#list fields as field>
    <@property field=field/>

    </#list>
    <#list fields as field>
    <@setter field=field/>

    <@getter field=field/>

    </#list>
    <@toString data=varModel/>
</#if>
}