<#--
    model = DataDao.class
    entity = DataObject.class
-->
<#include "java_common.ftl">
<#assign entity = model.entity />
<#assign primaryKey = model.primaryKey />
<#assign uniqueKey = model.uniqueKey />
package ${model.packageName};

import java.util.List;

import ${entity.className};
import org.apache.ibatis.annotations.Param;

/**
 * @author code-plugin
 * ${.now?string("yyyy/MM/dd HH:mm:ss")}
 */
public interface ${model.name} {

    int insert(${entity.name} ${entity.name?uncap_first});

    int insertList(List<${entity.name}> list);

    int insertSelective(${entity.name} ${entity.name?uncap_first});

    int deleteByPK(<#list primaryKey as key>@Param("${key.name}") ${multipleType(key)} ${key.name}<#if key_has_next>, </#if></#list>);

    int deleteBySelective(${entity.name} ${entity.name?uncap_first});

    ${entity.name} selectByPK(<#list primaryKey as key>@Param("${key.name}") ${multipleType(key)} ${key.name}<#if key_has_next>, </#if></#list>);

    List<${entity.name}> selectByCondition(${entity.name} ${entity.name?uncap_first});

    int updateByPK(${entity.name} ${entity.name?uncap_first});

    int updateByPKSelective(${entity.name} ${entity.name?uncap_first});

}