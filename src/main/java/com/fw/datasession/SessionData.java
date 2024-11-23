package com.fw.datasession;

public class SessionData {

    public SessionData() {
    }

    private static final ThreadLocal<TestSessionVariables> testSessionThreadLocal = new ThreadLocal();

    public static SessionVariableSetter setSessionVariable(Object key) {
        return new SessionVariableSetter(key);
    }

    public static <T> T sessionVariableCalled(Object key) {
        return (T) getCurrentSession().get(key);
    }

    public static SessionMap<Object, Object> getCurrentSession() {
        if (testSessionThreadLocal.get() == null) {
            testSessionThreadLocal.set(new TestSessionVariables());
        }

        return (SessionMap) testSessionThreadLocal.get();
    }


    //CLASS
    public static class SessionVariableSetter {
        final Object key;

        public SessionVariableSetter(Object key) {
            this.key = key;
        }

        public <T> void to(T value) {
            if (value != null) {
                SessionData.getCurrentSession().put(this.key, value);
            } else {
                SessionData.getCurrentSession().remove(this.key);
            }

        }
    }

}
