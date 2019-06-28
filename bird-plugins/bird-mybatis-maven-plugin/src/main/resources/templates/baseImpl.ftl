package ${basePackage}.base.impl;

import ${basePackage}.base.BaseDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author sql-maven-plugin
 *  ${.now?string("yyyy/MM/dd HH:mm:ss")}
 */
@Repository
public class BaseDaoImpl implements BaseDao {

    private static final String SQL_INSERT = ".insert";
    private static final String SQL_DELETE = ".deleteByPK";
    private static final String SQL_UPDATE = ".updateByPK";
    private static final String SQL_SELECT = ".selectByPK";
    private static final String SQL_INSERT_SELECTIVE = ".insertSelective";
    private static final String SQL_UPDATE_SELECTIVE = ".updateByPKSelective";
    private static final String SQL_DELETE_BY_WHERE_CASE = ".deleteByWhereCase";
    private static final String SQL_SELECT_BY_WHERE_CASE = ".selectByWhereCase";

    @Autowired
    SqlSession sqlSession;

    @Override
    public <${r'T'}> int save(T t) {
        return this.sqlSession.insert(buildSqlId(t.getClass(), SQL_INSERT), t);
    }

    @Override
    public <${r'T'}> int saveSelective(T t) {
        return this.sqlSession.insert(buildSqlId(t.getClass(), SQL_INSERT_SELECTIVE), t);
    }

    @Override
    public <${r'T'}> int save(String sqlId, T t) {
        return this.sqlSession.insert(sqlId, t);
    }

    @Override
    public int save(String sqlId, Map<${r'String'}, Object> param) {
        return this.sqlSession.insert(sqlId, param);
    }

    @Override
    public <${r'T'}> int update(T t) {
        return this.sqlSession.update(buildSqlId(t.getClass(), SQL_UPDATE), t);
    }

    @Override
    public <${r'T'}> int updateByPKSelective(T t) {
        return this.sqlSession.update(buildSqlId(t.getClass(), SQL_UPDATE_SELECTIVE), t);
    }

    @Override
    public <${r'T'}> int update(String sqlId, T t) {
        return this.sqlSession.update(sqlId, t);
    }

    @Override
    public int update(String sqlId, Map<${r'String'}, Object> param) {
        return this.sqlSession.update(sqlId, param);
    }

    @Override
    public <${r'T'}> int deleteByPK(T t) {
        return this.sqlSession.delete(buildSqlId(t.getClass(), SQL_DELETE), t);
    }

    @Override
    public <${r'T'}> int deleteByWhereCase(T t) {
        return this.sqlSession.delete(buildSqlId(t.getClass(), SQL_DELETE_BY_WHERE_CASE), t);
    }

    @Override
    public <${r'T'}> int delete(String sqlId, T t) {
        return this.sqlSession.delete(sqlId, t);
    }

    @Override
    public int delete(String sqlId, Map<${r'String'}, Object> param) {
        return this.sqlSession.delete(sqlId, param);
    }

    @Override
    public <${r'T'}> T queryByPK(T t) {
        return this.sqlSession.selectOne(buildSqlId(t.getClass(), SQL_SELECT), t);
    }

    @Override
    public <${r'T'}> List<${r'T'}> queryForList(T t) {
        return this.sqlSession.selectList(buildSqlId(t.getClass(), SQL_SELECT_BY_WHERE_CASE), t);
    }

    @Override
    public <${r'T'}> T queryForOne(T t) {
        List<${r'T'}> list = this.queryForList(t);
        if (null != list && !list.isEmpty()) {
            if (list.size() > 1) {
                throw new RuntimeException("存在多条记录");
            } else {
                return list.get(0);
            }
        } else {
            return null;
        }
    }

    @Override
    public <${r'T'}> List<${r'T'}> queryForList(String sqlId, T t) {
        return this.sqlSession.selectList(sqlId, t);
    }

    @Override
    public <${r'E'}> List<${r'E'}> queryForList(String sqlId, Map<${r'String'}, Object> param) {
        return this.sqlSession.selectList(sqlId, param);
    }

    @Override
    public Map<${r'String'}, Object> queryForMap(String sqlId, Object object, String mapKey) {
        return this.sqlSession.selectMap(sqlId, object, mapKey);
    }

    @Override
    public SqlSession getSqlSession() {
        return this.sqlSession;
    }

    @Override
    public int queryForCount(String sqlId, Map<${r'String'}, Object> param) {
        return ((Integer) this.getSqlSession().selectOne(sqlId, param)).intValue();
    }

    private String buildSqlId(Class cls, String suffix) {
        return cls.getName().replace(".pojo.", ".dao.") + "Dao" + suffix;
    }
}
