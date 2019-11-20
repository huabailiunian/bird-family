<#--
    model = DataDao.class
    entity = DataObject.class
-->
<#include "java_common.ftl">
<#assign entity = model.entity />
package ${model.packageName};

import java.util.List;

import ${entity.className};
import org.apache.ibatis.annotations.Param;

/**
 * @author code-plugin
 * ${.now?string("yyyy/MM/dd HH:mm:ss")}
 */
public interface ${model.name} {

    int insert(${entity.name} object);

    int insertList(List<${entity.name}> list);

    int insertSelective(${entity.name} object);

    int deleteByPK(<#list keys as key>@Param("${key.name}") ${engine.fieldType(key.type)} ${engine.fieldNameGen(key.name)}<#if key_has_next>, </#if></#list>);

    int deleteByCondition(${entityName} object);

    ${entityName} selectByPK(<#list keys as key>@Param("${key.name}") ${engine.fieldType(key.type)} ${engine.fieldNameGen(key.name)}<#if key_has_next>, </#if></#list>);

    List<${entityName}> selectByCondition(${entityName} object);

    int updateByPK(${entityName} object);

    int updateByPKSelective(${entityName} object);

<#if model.queries??>
<#assign queries = model.queries>
<#list queries as query>
<#assign params = engine.queryColGen(columns,query.params)>
    <#if query.array>List<</#if><#if query.useRowMap>${entityName}<#else>${query.resultType}</#if><#if query.array>></#if> ${query.name}(<#if query.params??><#list params as key>@Param("${key.name}") ${engine.fieldType(key.type)} ${engine.fieldNameGen(key.name)}<#if key_has_next>, </#if></#list></#if><#if query.usePage><#if query.params??>, </#if>@Param("offset") Integer offset, @Param("size") Integer size</#if>);

</#list>
</#if>

}