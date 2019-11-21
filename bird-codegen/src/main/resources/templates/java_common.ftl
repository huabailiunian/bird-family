<#--field = ObjectProperty.class-->
<#macro property field>
    <#assign varType = multipleType(field)/>
    /**
     * <p>${field.label!}</p>
     */
    ${visibility(field)}<#if field.static> static</#if><#if field.final> final</#if> ${varType} ${field.name?uncap_first};
</#macro>

<#macro setter field>
    <#assign varName = field.name?uncap_first>
    <#assign varType = multipleType(field)>
    public void set${varName?cap_first}(${varType} ${varName}) {
        this.${varName} = ${varName}
    }
</#macro>

<#macro getter field>
    <#assign varName = field.name?uncap_first>
    <#assign varType = multipleType(field)>
    public ${varType} get${varName?cap_first}() {
        return this.${varName}
    }
</#macro>

<#macro toString data>
    <#assign fields = data.properties>
    @Override
    public String toString() {
        return "${data.name?cap_first}{" +
                <#list fields as f>
                "${f.name?uncap_first}=" + ${f.name?uncap_first} +<#if f_has_next> "," +</#if>
                </#list>
                "}";
    }
</#macro>

<#--
    method = ObjectMethod.class
-->
<#macro genMethod method>
    <#assign params = method.params />
    ${visibility(method)}<#if method.static> static</#if><#if method.final> final</#if> ${multipleType(method)} ${method.name}(<#list params as param>${multipleType(param)} ${param.name?uncap_first}<#if param_has_next>, </#if></#list>);
</#macro>

<#--data = JavaType.class-->
<#function javaType data>
    <#if data.annotation>
        <#return '@interface'/>
    <#else>
        <#return data.typeKind?lower_case />
    </#if>
</#function>

<#function multipleType field>
    <#assign varBag = field.bag!'java.util.List'/>
    <#if field.multiple>
        <#return varBag + '<' + field.className + '>'/>
    <#else>
        <#return field.className />
    </#if>
</#function>

<#--data = HasVisibility.class-->
<#function visibility data>
    <#if data.packagePrivate>
        <#return ''/>
    <#else>
        <#return data.visibility?lower_case />
    </#if>
</#function>

<#--data = HasInterface.class-->
<#function resolveInterface data>
    <#if data.interfaces?? && (data.interfaces?size > 0)>
        <#assign faces = data.interfaces />
        <#assign varData = ' implements '/>
        <#if data.interface>
            <#assign varData = ' extends '/>
        </#if>
        <#list faces as fa>
            <#assign varData = varData + fa/>
            <#if fa_has_next>
                <#assign varData = varData + ', '/>
            </#if>
        </#list>
        <#return varData/>
    <#else>
        <#return ''/>
    </#if>
</#function>