<#macro property field>
    <#assign varType = multipleType(field)/>
    /**
     * <p>${field.label!}</p>
     */
    ${visibility(field)}<#if field.static> static</#if><#if field.final> final</#if> ${varType} ${field.name};
</#macro>

<#macro setter field>
    <#assign varName = field.name>
    <#assign varType = multipleType(field)>
    public void set${varName?cap_first}(${varType} ${varName}) {
        this.${varName} = ${varName}
    }
</#macro>

<#macro getter field>
    <#assign varName = field.name>
    <#assign varType = multipleType(field)>
    public ${varType} get${varName?cap_first}() {
        return this.${varName}
    }
</#macro>

<#macro toString data>
    <#assign fields = data.properties>
    @Override
    public String toString() {
        return "${data.name}{" +
                <#list fields as f>
                "${f.name}=" + ${f.name} +<#if f_has_next> "," +</#if>
                </#list>
                "}";
    }
</#macro>

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

<#function visibility data>
    <#if data.packagePrivate>
        <#return ''/>
    <#else>
        <#return data.visibility?lower_case />
    </#if>
</#function>

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