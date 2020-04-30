package com.bird.demo.groovy;//package com.bird.demo.groovy;
//
//import com.bird.core.cache.ICache;
//import cn.tongdun.demo.dal.dao.GroovySourceDao;
//import cn.tongdun.demo.dal.object.GroovySource;
//import groovy.lang.GroovyClassLoader;
//import groovy.lang.GroovyObject;
//import groovy.lang.MetaMethod;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author youly
// * 2019/7/2 14:22
// */
//public class GroovyCache implements ICache<String, GroovyObject> {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    private GroovySourceDao groovySourceDao;
//
//    private GroovyClassLoader groovyClassLoader;
//
//    private ConcurrentHashMap<String, GroovyObject> cache = new ConcurrentHashMap<>();
//
//    public void init() {
//        ClassLoader classLoader = getClass().getClassLoader();
//        groovyClassLoader = new GroovyClassLoader(classLoader);
//    }
//
//    private GroovyObject load(String key) {
//        GroovySource select = new GroovySource();
//        GroovySource source = groovySourceDao.selectByPK(select);
//        Class cls = groovyClassLoader.parseClass(source.getContext());
//        try {
//            GroovyObject object = (GroovyObject) cls.newInstance();
//            List<MetaMethod> methods = object.getMetaClass().getMethods();
//            methods.get(0).getNativeParameterTypes();
//            this.set(key, object);
//            return object;
//        } catch (Exception e) {
//            logger.error("Load groovy source error[key:{}]", key, e);
//        }
//        return null;
//    }
//
//    @Override
//    public GroovyObject get(String key) {
//        return cache.containsKey(key) ? cache.get(key) : load(key);
//    }
//
//    @Override
//    public void set(String key, GroovyObject groovyObject) {
//        cache.put(key, groovyObject);
//    }
//
//    @Override
//    public void clear() {
//        cache.clear();
//    }
//
//    @Override
//    public void remove(String key) {
//        cache.remove(key);
//    }
//
//    @Override
//    public boolean exists(String key) {
//        return cache.containsKey(key);
//    }
//
//    @Override
//    public Set<String> keySet() {
//        return cache.keySet();
//    }
//
//    public GroovySourceDao getGroovySourceDao() {
//        return groovySourceDao;
//    }
//
//    public void setGroovySourceDao(GroovySourceDao groovySourceDao) {
//        this.groovySourceDao = groovySourceDao;
//    }
//}
