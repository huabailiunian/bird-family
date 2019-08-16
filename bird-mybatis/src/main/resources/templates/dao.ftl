package ${pkg}.dao;

import java.util.List;

import ${pkg}.object.${objectName};

/**
 * @author maven-plugin
 * ${.now?string("yyyy/MM/dd HH:mm:ss")}
 */
public interface ${daoName} {

    int insert(${objectName} object);

    int insertList(List<${objectName}> list);

    int insertSelective(${objectName} object);

    int deleteByPK(${objectName} object);

    int deleteByCondition(${objectName} object);

    ${objectName} selectByPK(${objectName} object);

    List<${objectName}> selectByCondition(${objectName} object);

    int updateByPK(${objectName} object);

    int updateByPKSelective(${objectName} object);

<#if queries??>
    <#list queries as query>
    <#if query.array>List<</#if><#if query.rowMap>${objectName}<#else>${query.resultType}</#if><#if query.array>></#if> ${query.name}();
    </#list>
</#if>

}