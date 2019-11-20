package com.bird.codegen;

import java.util.List;

/**
 * @author youly
 * 2019/11/20 16:20
 */
public interface DataDao extends JavaType {

    DataObject getEntity();

    List<ObjectProperty> getPrimaryKey();

    List<ObjectProperty> getUniqueKey();
}
