package com.fw.datasession;

import java.util.Map;

//INTERFACE
public interface SessionMap<K, V> extends Map<K, V> {
    Map<String, String> getMetaData();

    void addMetaData(String var1, String var2);

    void clearMetaData();

    void shouldContainKey(K var1);
}
