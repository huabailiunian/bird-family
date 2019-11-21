<#--
    model = JavaInterface.class
-->
<#include "java_common.ftl">
<#macro genMethod data>
    <#assign params = data.params />
    ${visibility(data)} ${data.className} ${data.name}(<#list params as param>${multipleType(param)} ${param.name}<#if param_has_next>, </#if></#list>);
</#macro>
<#assign varModel = model/>
package ${varModel.packageName};

/**
* @author code-plugin
* ${.now?string("yyyy/MM/dd HH:mm:ss")}
*/
${visibility(varModel)} interface ${varModel.name}${resolveInterface(varModel)} {
<#if varModel.methods??>
<#assign methods = varModel.methods />
<#list methods as method>
<@genMethod data=method/>

</#list>
</#if>
}

