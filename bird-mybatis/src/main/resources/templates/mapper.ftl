<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#assign entityName = engine.entityPkg + "." + engine.entityNameGen(model.name)>
<#assign daoName = engine.daoPkg + "." + engine.daoNameGen(model.name)>
<mapper namespace="${daoName}">
<#if model.columns??>
    <#assign columns = model.columns>
    <#assign keys = engine.keys(columns)>
    <#assign tableName = model.name>

    <resultMap id="base_result_map" type="${entityName}">
        <#list columns as col>
        <result column="${col.name}" jdbcType="${engine.jdbcType(col.type)}" property="${engine.fieldNameGen(col.name)}" />
        </#list>
    </resultMap>

    <sql id="base_column_list"><#list columns as col>${col.name}<#if col_has_next>,</#if></#list></sql>

    <sql id="base_column_insert_list"><#list columns as col><#if !col.autoIncrement>${col.name}<#if col_has_next>,</#if></#if></#list></sql>

    <insert id="insert" parameterType="${entityName}"<#list columns as col><#if col.autoIncrement> useGeneratedKeys="true" keyProperty="${engine.fieldNameGen(col.name)}"</#if></#list>>
        insert into ${tableName}(<include refid="base_column_insert_list" />)
        values (<#list columns as col><#if !col.autoIncrement>
            ${r'#{'}${engine.fieldNameGen(col.name)},jdbcType=${engine.jdbcType(col.type)}}<#if col_has_next>,</#if></#if></#list>
        )
    </insert>

    <insert id="insertList" parameterType="${entityName}"<#list columns as col><#if col.autoIncrement> useGeneratedKeys="true" keyProperty="${engine.fieldNameGen(col.name)}"</#if></#list>>
        insert into ${tableName}(<include refid="base_column_insert_list" />)
        values
        <foreach collection="list" index="index" item="item" separator=",">
            (<#list columns as col><#if !col.autoIncrement>
            ${r'#{'}${engine.fieldNameGen(col.name)},jdbcType=${engine.jdbcType(col.type)}}<#if col_has_next>,</#if></#if></#list>
            )
        </foreach>
    </insert>

    <insert id="insertSelective" parameterType="${entityName}"<#list columns as col><#if col.autoIncrement> useGeneratedKeys="true" keyProperty="${engine.fieldNameGen(col.name)}"</#if></#list>>
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list columns as col>
                <#if !col.autoIncrement>
            <#--<if test="${f.fieldName} != null<#if f.fieldType == 'String'> and ${f.fieldName} != ''</#if>">-->
            <if test="${engine.fieldNameGen(col.name)} != null">
                ${col.name},
            </if>
                </#if>
            </#list>
        </trim>
        <trim prefix=" values(" suffix=")" suffixOverrides=",">
            <#list columns as f>
                <#if !f.autoIncrement>
            <#--<if test="${f.fieldName} != null<#if f.fieldType == 'String'> and ${f.fieldName} != ''</#if>">-->
            <if test="${engine.fieldNameGen(f.name)} != null">
                ${r'#{'}${engine.fieldNameGen(f.name)},jdbcType=${engine.jdbcType(f.type)}},
            </if>
                </#if>
            </#list>
        </trim>
    </insert>

    <delete id="deleteByPK" parameterType="map">
        delete from ${tableName}
        <where>
        <#list keys as key>
            ${key.name} = ${r'#{'}${key.name},jdbcType=${engine.jdbcType(key.type)}}<#if key_has_next> AND </#if>
        </#list>
        </where>
    </delete>

    <delete id="deleteByCondition" parameterType="${entityName}">
        delete from ${tableName}
        <where>
        <#list columns as col>
            <#assign fieldName = engine.fieldNameGen(col.name)>
            <#--<if test="${f.fieldName} != null<#if f.fieldType == 'String'> and ${f.fieldName} != ''</#if>">-->
            <if test="${fieldName} != null">
                AND ${col.name} = ${r'#{'}${fieldName},jdbcType=${engine.jdbcType(col.type)}}
            </if>
        </#list>
        </where>
    </delete>

    <select id="selectByPK" parameterType="map" resultMap="base_result_map">
        select
        <include refid="base_column_list" />
        from ${tableName}
        <where>
        <#list keys as key>
            ${key.name} = ${r'#{'}${key.name},jdbcType=${engine.jdbcType(key.type)}}<#if key_has_next> AND </#if>
        </#list>
        </where>
        limit 1
    </select>

    <select id="selectByCondition" parameterType="${entityName}" resultMap="base_result_map">
        select
        <include refid="base_column_list" />
        from ${tableName}
        <where>
        <#list columns as f>
            <#assign fieldName = engine.fieldNameGen(f.name)>
            <#--<if test="${f.fieldName} != null<#if f.fieldType == 'String'> and ${f.fieldName} != ''</#if>">-->
            <if test="${fieldName} != null">
            AND ${f.name} = ${r'#{'}${fieldName},jdbcType=${engine.jdbcType(f.type)}}
            </if>
        </#list>
        </where>
        limit 10000
    </select>

    <update id="updateByPK" parameterType="${entityName}">
        update ${tableName}
        <set>
        <#list columns as f>
            <#if !f.primaryKey>
            ${f.name} = ${r'#{'}${engine.fieldNameGen(f.name)},jdbcType=${engine.jdbcType(f.type)}},
            </#if>
        </#list>
        </set>
        where
            <#list keys as key>
            ${key.name} = ${r'#{'}${engine.fieldNameGen(key.name)},jdbcType=${engine.jdbcType(key.type)}}<#if key_has_next> AND </#if>
            </#list>
    </update>

    <update id="updateByPKSelective" parameterType="${entityName}">
        update ${tableName}
        <set>
        <#list columns as col>
        <#if !col.primaryKey>
            <#assign fieldName = engine.fieldNameGen(col.name)>
            <if test="${fieldName} != null">
                ${col.name} = ${r'#{'}${fieldName},jdbcType=${engine.jdbcType(col.type)}},
            </if>
        </#if>
        </#list>
        </set>
        where
            <#list keys as key>
            ${key.name} = ${r'#{'}${engine.fieldNameGen(key.name)},jdbcType=${engine.jdbcType(key.type)}}<#if key_has_next> AND </#if>
            </#list>
    </update>

<#if model.queries??>
    <#assign queries = model.queries>
    <#list queries as query>
        <#assign params = engine.queryColGen(columns,query.params)>
    <select id="${query.name}"<#if query.params??> parameterType="map"</#if><#if query.useRowMap> resultMap="base_result_map"<#else> resultType="${query.resultType}"</#if>>
        select <#if query.columns??>${query.columns}<#else><include refid="base_column_list" /></#if>
        from ${tableName}
        <#if query.params??>
        <#if query.useAuto>
        <where>
        <#list params as f>
            <if test="${f.name} != null">
                AND ${f.name} = ${r'#{'}${f.name},jdbcType=${engine.jdbcType(f.type)}}
            </if>
        </#list>
        </where>
        <#else>
        where
            <#list params as key>
            ${key.name} = ${r'#{'}${key.name},jdbcType=${engine.jdbcType(key.type)}}<#if key_has_next> AND </#if>
            </#list>
        </#if>
        </#if>
        <#if query.extSql??>
        ${query.extSql}
        </#if>
    </select>

    </#list>
</#if>

</#if>
</mapper>