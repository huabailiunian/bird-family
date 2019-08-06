<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.dao.${daoName}">
<#if fields??>
    <resultMap id="base_result_map" type="${basePackage}.object.${objectName}">
        <#--<#list primaryKey as key>-->
        <#--<id column="${key.columnName}" jdbcType="${key.jdbcType}" property="${key.fieldName}" />-->
        <#--</#list>-->
        <#list fields as f>
        <#--<#if !f.primaryKey>-->
        <result column="${f.columnName}" jdbcType="${f.jdbcType}" property="${f.fieldName}" />
        <#--</#if>-->
        </#list>
    </resultMap>

    <sql id="base_column_list"><#list fields as f>${f.columnName}<#if f_has_next>,</#if></#list></sql>

    <sql id="base_column_insert_list"><#list fields as f><#if !f.autoIncrement>${f.columnName}<#if f_has_next>,</#if></#if></#list></sql>

    <insert id="insert" parameterType="${basePackage}.object.${objectName}"<#if autoInPK??> useGeneratedKeys="true" keyProperty="${autoInPK.fieldName}"</#if>>
        insert into ${tableName}(<include refid="base_column_insert_list" />)
        values (<#list fields as f><#if !f.autoIncrement>
            ${r'#{'}${f.fieldName},jdbcType=${f.jdbcType}}<#if f_has_next>,</#if></#if></#list>
        )
    </insert>

    <insert id="insertList" parameterType="${basePackage}.object.${objectName}"<#if autoInPK??> useGeneratedKeys="true" keyProperty="${autoInPK.fieldName}" keyColumn="${autoInPK.columnName}"</#if>>
        insert into ${tableName}(<include refid="base_column_insert_list" />)
        values
        <foreach collection="list" index="index" item="item" separator=",">
            (<#list fields as f><#if !f.autoIncrement>
            ${r'#{item.'}${f.fieldName},jdbcType=${f.jdbcType}}<#if f_has_next>,</#if></#if></#list>
            )
        </foreach>
    </insert>

    <insert id="insertSelective" parameterType="${basePackage}.object.${objectName}"<#if autoInPK??> useGeneratedKeys="true" keyProperty="${autoInPK.fieldName}"</#if>>
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list fields as f>
                <#if !f.autoIncrement>
            <if test="${f.fieldName} != null<#if f.fieldType == 'String'> and ${f.fieldName} != ''</#if>">
                ${f.columnName},
            </if>
                </#if>
            </#list>
        </trim>
        <trim prefix=" values(" suffix=")" suffixOverrides=",">
            <#list fields as f>
                <#if !f.autoIncrement>
            <if test="${f.fieldName} != null<#if f.fieldType == 'String'> and ${f.fieldName} != ''</#if>">
                ${r'#{'}${f.fieldName},jdbcType=${f.jdbcType}},
            </if>
                </#if>
            </#list>
        </trim>
    </insert>

    <delete id="deleteByPK" parameterType="${basePackage}.object.${objectName}">
        delete from ${tableName}
        where
        <#list primaryKey as key>
            ${key.columnName} = ${r'#{'}${key.fieldName},jdbcType=${key.jdbcType}}<#if key_has_next> and</#if>
        </#list>
    </delete>

    <delete id="deleteByCondition" parameterType="${basePackage}.object.${objectName}">
        delete from ${tableName}
        <where>
        <#list fields as f>
            <if test="${f.fieldName} != null<#if f.fieldType == 'String'> and ${f.fieldName} != ''</#if>">
                AND ${f.columnName} = ${r'#{'}${f.fieldName},jdbcType=${f.jdbcType}}
            </if>
        </#list>
        </where>
    </delete>

    <select id="selectByPK" parameterType="${basePackage}.object.${objectName}" resultMap="base_result_map">
        select
        <include refid="base_column_list" />
        from ${tableName}
        where
            <#list primaryKey as key>
            ${key.columnName} = ${r'#{'}${key.fieldName},jdbcType=${key.jdbcType}}<#if key_has_next> and</#if>
            </#list>
        limit 1
    </select>

    <select id="selectByCondition" parameterType="${basePackage}.object.${objectName}" resultMap="base_result_map">
        select
        <include refid="base_column_list" />
        from ${tableName}
        <where>
        <#list fields as f>
            <if test="${f.fieldName} != null<#if f.fieldType == 'String'> and ${f.fieldName} != ''</#if>">
            AND ${f.columnName} = ${r'#{'}${f.fieldName},jdbcType=${f.jdbcType}}
            </if>
        </#list>
        </where>
        limit 10000
    </select>

    <update id="updateByPK" parameterType="${basePackage}.object.${objectName}">
        update ${tableName}
        <set>
        <#list fields as f>
            <#if !f.primaryKey>
            ${f.columnName} = ${r'#{'}${f.fieldName},jdbcType=${f.jdbcType}},
            </#if>
        </#list>
        </set>
        where
            <#list primaryKey as key>
            ${key.columnName} = ${r'#{'}${key.fieldName},jdbcType=${key.jdbcType}}<#if key_has_next> and</#if>
            </#list>
    </update>

    <update id="updateByPKSelective" parameterType="${basePackage}.object.${objectName}">
        update ${tableName}
        <set>
        <#list fields as f>
        <#if !f.primaryKey>
            <if test="${f.fieldName} != null<#if f.fieldType == 'String'> and ${f.fieldName} != ''</#if>">
                ${f.columnName} = ${r'#{'}${f.fieldName},jdbcType=${f.jdbcType}},
            </if>
        </#if>
        </#list>
        </set>
        where
            <#list primaryKey as key>
            ${key.columnName} = ${r'#{'}${key.fieldName},jdbcType=${key.jdbcType}}<#if key_has_next> and</#if>
            </#list>
    </update>
</#if>
</mapper>