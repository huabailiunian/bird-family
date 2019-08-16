package com.bird.core.context;

import com.bird.core.tools.CollectionTools;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread local context
 * 线程变量上下文
 *
 * @author youly
 * 2019/7/24 10:06
 */
public class ThreadContext {

    private static ThreadLocal<Map<String, Object>> resources = new InheritableThreadLocalMap();

    protected ThreadContext() {
    }

    public static Map<String, Object> getResource() {
        return CollectionTools.isNotEmpty(resources.get()) ? new HashMap<>(resources.get()) : Collections.emptyMap();
    }

    public static void setResource(Map<String, Object> resource) {
        if (CollectionTools.isEmpty(resource)) {
            return;
        }
        ensureResourcesInitialized();
        resources.get().clear();
        resources.get().putAll(resource);
    }

    private static void ensureResourcesInitialized() {
        if (resources.get() == null) {
            resources.set(new HashMap<>());
        }
    }

    public static Object get(String key) {
        return getValue(key);
    }

    private static Object getValue(String key) {
        Map<String, Object> preResources = resources.get();
        return CollectionTools.isEmpty(preResources) ? null : preResources.get(key);
    }

    public static void put(String key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (value == null) {
            remove(key);
            return;
        }
        ensureResourcesInitialized();
        resources.get().put(key, value);
    }

    public static Object remove(String key) {
        Map<String, Object> preResources = resources.get();
        return CollectionTools.isEmpty(preResources) ? null : preResources.remove(key);
    }

    /**
     * {@link ThreadLocal#remove Remove}s the underlying {@link ThreadLocal ThreadLocal} from the thread.
     * <p/>
     * This method is meant to be the final 'clean up' operation that is called at the end of thread execution to
     * prevent thread corruption in pooled thread environments.
     *
     * @since 1.0
     */
    public static void remove() {
        resources.remove();
    }

    private static final class InheritableThreadLocalMap extends InheritableThreadLocal<Map<String, Object>> {

        /**
         * This implementation was added to address a
         * user-reported issue</a>.
         *
         * @param parentValue the parent value, a HashMap as defined in the {@link #initialValue()} method.
         * @return the HashMap to be used by any parent-spawned child threads (a clone of the parent HashMap).
         */
        @Override
        @SuppressWarnings("unchecked")
        protected Map<String, Object> childValue(Map<String, Object> parentValue) {
            if (parentValue != null) {
                return (Map<String, Object>) ((HashMap<String, Object>) parentValue).clone();
            } else {
                return null;
            }
        }
    }
}
