package com.bird.mybatis;

import com.bird.core.tools.CollectionTools;
import com.bird.core.tools.StringTools;
import com.bird.mybatis.definition.Column;
import com.bird.mybatis.definition.Query;
import com.bird.mybatis.definition.Table;
import com.bird.mybatis.jdbc.JdbcTypeMapper;
import com.bird.mybatis.model.Config;
import com.bird.mybatis.model.FieldModel;
import com.bird.mybatis.model.ObjectModel;
import com.bird.mybatis.model.QueryModel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author youly
 * 2019/8/13 13:58
 */
public class ObjectModelConvertor {


    public static List<ObjectModel> convertor(Config config, List<Table> tables) {
        if (CollectionTools.isNotEmpty(tables)) {
            List<ObjectModel> result = new ArrayList<>(tables.size());
            for (Table table : tables) {
                ObjectModel object = new ObjectModel();
                object.setPkg(config.getBasePackage());
                object.setName(StringTools.camelCase(table.getName(), config.getRegex()));
                object.setTableName(table.getName());
                object.setDisplay(table.getDisplay());
                object.setDaoSuffix(config.getDaoSuffix());
                object.setEntitySuffix(config.getEntitySuffix());
                object.setPrimaryKey(new ArrayList<>());
                object.setFields(new ArrayList<>());
                fieldGen(object, config.getRegex(), table.getColumns());
                queryGen(object, config.getRegex(), table.getQueries());
                result.add(object);
            }
            return result;
        }
        return Collections.emptyList();
    }

    private static void queryGen(ObjectModel object, String regex, List<Query> queries) {
        if (CollectionTools.isNotEmpty(queries)) {
            List<QueryModel> queryModels = new ArrayList<>(queries.size());
            for (Query query : queries) {
                QueryModel queryModel = new QueryModel();
                queryModel.setType(query.getType());
                queryModel.setName(query.getName());
                queryModel.setParamType(query.getParamType());
                String params = query.getParams();
                if (StringUtils.isNotBlank(params)) {
                    queryModel.setParams(query.getParamList());
                }
                queryModel.setResultType(query.getResultType());
                queryModel.setArray(query.isArray());

                queryModels.add(queryModel);
            }
            object.setQueries(queryModels);
        }
    }

    private static void fieldGen(ObjectModel object, String regex, List<Column> columns) {
        if (CollectionTools.isNotEmpty(columns)) {
            for (Column column : columns) {
                FieldModel field = new FieldModel();
                String type = column.getType();
                field.setFieldType(JdbcTypeMapper.valueOf(type.toUpperCase()).objectType());
                field.setJdbcType(JdbcTypeMapper.valueOf(type.toUpperCase()).jdbcType());
                field.setFieldName(StringTools.lowerCaseFirst(StringTools.camelCase(column.getName().trim(), regex)));
                field.setColumnName(column.getName().trim());
                field.setDisplay(column.getDisplay());
                field.setDesc(column.getDesc());
                field.setLength(column.getLength());
                if (column.isAutoIncrement()) {
                    field.setAutoIncrement(true);
                    field.setPrimaryKey(true);
                    object.setAutoInPK(field);
                }
                if (column.isPrimaryKey()) {
                    field.setPrimaryKey(true);
                    object.getPrimaryKey().add(field);
                }
                object.getFields().add(field);
            }
        }
    }
}
