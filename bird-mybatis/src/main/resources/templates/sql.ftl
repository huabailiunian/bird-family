<#assign strEnums=["char","varchar","character"]/>
<#assign largeEnums=["text","mediumtext","longtext","blob","mediumblob","longblob"]/>
<#assign timeEnums=["timestamp","datetime","date","time"]/>
<#if model??>
<#list model as table>
DROP TABLE IF EXISTS `${table.name}`;
CREATE TABLE `${table.name}` (
<#if table.columns??>
<#assign keys = engine.keys(table.columns)>
<#list table.columns as column>
<#if strEnums?seq_contains(column.type)>
    `${column.name}` ${column.type}<#if column.length??>(${column.length})</#if><#if column.unsigned> unsigned</#if><#if column.allowNull> DEFAULT NULL<#else> NOT NULL DEFAULT '${column.defaultValue!}'</#if><#if column.display??> COMMENT '${column.display}'</#if>,
<#elseif timeEnums?seq_contains(column.type)>
    `${column.name}` ${column.type}<#if column.length??>(${column.length})</#if><#if column.unsigned> unsigned</#if><#if column.allowNull> DEFAULT NULL<#else> NOT NULL DEFAULT ${column.defaultValue!'CURRENT_TIMESTAMP'}</#if><#if column.display??> COMMENT '${column.display}'</#if>,
<#elseif largeEnums?seq_contains(column.type)>
    `${column.name}` ${column.type}<#if column.display??> COMMENT '${column.display}'</#if>,
<#else>
    `${column.name}` ${column.type}<#if column.length??>(${column.length})</#if><#if column.unsigned> unsigned</#if><#if column.allowNull> DEFAULT NULL<#else> NOT NULL<#if !column.autoIncrement> DEFAULT ${column.defaultValue!'0'}</#if></#if><#if column.autoIncrement> AUTO_INCREMENT</#if><#if column.display??> COMMENT '${column.display}'</#if>,
</#if>
</#list>
    PRIMARY KEY(<#list keys as key>`${key.name}`<#if key_has_next>,</#if></#list>)<#if table.uniqueKeys?? || table.indexes??>,</#if>
</#if>
<#if table.uniqueKeys??>
<#list table.uniqueKeys as key>
    UNIQUE KEY `${key.name!}` (<#list key.columnList as column>`${column}`<#if column_has_next>,</#if></#list>)<#if key_has_next || table.indexes??>,</#if>
</#list>
</#if>
<#if table.indexes??>
<#list table.indexes as key>
    KEY `${key.name!}` (<#list key.columnList as column>`${column}`<#if column_has_next>,</#if></#list>)<#if key_has_next>,</#if>
</#list>
</#if>
) ENGINE=InnoDB DEFAULT CHARSET=${(setting.charset)!"utf8mb4"} COMMENT '${table.display!}';

</#list>
</#if>