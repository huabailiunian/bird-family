<#assign entityName = engine.entityNameGen(model.name)>
<#assign daoName = engine.daoNameGen(model.name)>
package ${engine.daoPkg};

import java.util.List;

import ${engine.entityPkg}.${entityName};

/**
 * @author maven-plugin
 * ${.now?string("yyyy/MM/dd HH:mm:ss")}
 */
public interface ${daoName} {

    int insert(${entityName} object);

    int insertList(List<${entityName}> list);

    int insertSelective(${entityName} object);

    int deleteByPK(${entityName} object);

    int deleteByCondition(${entityName} object);

    ${entityName} selectByPK(${entityName} object);

    List<${entityName}> selectByCondition(${entityName} object);

    int updateByPK(${entityName} object);

    int updateByPKSelective(${entityName} object);

}