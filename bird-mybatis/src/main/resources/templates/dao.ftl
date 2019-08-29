<#assign entityName = engine.entityNameGen(model.name)>
<#assign daoName = engine.daoNameGen(model.name)>
<#assign columns = model.columns>
<#assign keys = engine.keys(columns)>
package ${engine.daoPkg};

import java.util.List;

import ${engine.entityPkg}.${entityName};
import org.apache.ibatis.annotations.Param;

/**
 * @author maven-plugin
 * ${.now?string("yyyy/MM/dd HH:mm:ss")}
 */
public interface ${daoName} {

    int insert(${entityName} object);

    int insertList(List<${entityName}> list);

    int insertSelective(${entityName} object);

    int deleteByPK(<#list keys as key>@Param("${key.name}") ${engine.fieldType(key.type)} ${engine.fieldNameGen(key.name)}<#if key_has_next>, </#if></#list>);

    int deleteByCondition(${entityName} object);

    ${entityName} selectByPK(<#list keys as key>@Param("${key.name}") ${engine.fieldType(key.type)} ${engine.fieldNameGen(key.name)}<#if key_has_next>, </#if></#list>);

    List<${entityName}> selectByCondition(${entityName} object);

    int updateByPK(${entityName} object);

    int updateByPKSelective(${entityName} object);

}