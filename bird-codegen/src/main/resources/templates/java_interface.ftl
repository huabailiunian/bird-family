<#--
    model = JavaInterface.class
    method = ObjectMethod.class
-->
<#include "java_common.ftl">
<#assign varModel = model/>
package ${varModel.packageName};

/**
* @author code-plugin
* ${.now?string("yyyy/MM/dd HH:mm:ss")}
*/
${visibility(varModel)} interface ${varModel.name?cap_first}${resolveInterface(varModel)} {

<#if varModel.methods??>
<#assign methods = varModel.methods />
<#list methods as method>
<@genMethod method=method/>

</#list>
</#if>
}

