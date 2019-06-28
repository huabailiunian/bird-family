package ${basePackage}.dao;

import java.util.List;

import ${basePackage}.object.${objectName};

/**
 * @author sql-maven-plugin
 *  ${.now?string("yyyy/MM/dd HH:mm:ss")}
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

}