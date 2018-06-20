package com.warm.livelive.eyepetizer.data.http.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.warm.livelive.eyepetizer.data.bean.BaseBean;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 作者：warm
 * 时间：2018-06-19 09:47
 * 描述：
 */
public class ContentAdapter<T> extends TypeAdapter<BaseBean<T>> {



    @Override
    public void write(JsonWriter out, BaseBean<T> value) throws IOException {
    }

    @Override
    public BaseBean<T> read(JsonReader in) throws IOException {
        JsonElement jsonElement = Streams.parse(in);
        BaseBean<T> baseBean = new BaseBean<>();

        if (jsonElement.isJsonNull()) {
            Type type = new TypeToken<T>() {}.getType();
            baseBean.setData(new Gson().fromJson(jsonElement, type));

            return baseBean;
        }else {
            baseBean.setErrorCode(BaseBean.ERROR_404);
            return baseBean;
        }
    }
}
