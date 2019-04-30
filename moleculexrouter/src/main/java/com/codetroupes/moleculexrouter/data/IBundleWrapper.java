package com.codetroupes.moleculexrouter.data;

import android.os.Bundle;


import com.codetroupes.moleculexrouter.constant.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A bundle wrapper for parsing bundle extras
 * @author MondyXue <a href="mailto:mondyxue@gmail.com">E-Mail</a>
 */
public interface IBundleWrapper{

    IBundleWrapper put(@Type int type, String key, String value);
    IBundleWrapper put(String key, Object value);
    IBundleWrapper put(String key, ArrayList<String> value);
    IBundleWrapper put(String key, String[] value);
    <T extends Serializable> IBundleWrapper put(String key, List<T> value);
    IBundleWrapper put(Bundle bundle);
    String getString(String key);
    String getString(String key, String def);
    Long getLong(String key);
    Long getLong(String key, Long def);
    Integer getInt(String key);
    Integer getInt(String key, Integer def);
    Double getDouble(String key);
    Double getDouble(String key, Double def);
    Boolean getBoolean(String key);
    Boolean getBoolean(String key, Boolean def);
    Float getFloat(String key);
    Float getFloat(String key, Float def);
    <T extends Serializable> T getSerializable(String key);
    <T extends Serializable> List<T> getList(String key);
    ArrayList<String> getStringArrayList(String key);
    String[] getStringArray(String key);
    Bundle getBundle();
    boolean isEmpty();
    void clear();

}