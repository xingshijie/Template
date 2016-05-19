package com.xingshijie.common.network.coverter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static com.xingshijie.template.utils.LogUtils.LOGD;
import static com.xingshijie.template.utils.LogUtils.LOGE;
import static com.xingshijie.template.utils.LogUtils.makeLogTag;

/**
 * Created by Word Xing  on 2016/5/11.
 */
public class FastJsonConverterFactory extends Converter.Factory{

    private static final String TAG = makeLogTag(FastJsonConverterFactory.class);
    private final MediaType mediaType =MediaType.parse("application/json; charset=UTF-8");

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new Converter<ResponseBody, Object>() {
            @Override
            public Object convert(ResponseBody body) throws IOException {
                try {
                    if (body == null) {
                        return null;
                    }
                    BufferedSource source = body.source();
                    final Buffer buffer = new Buffer();
                    buffer.writeAll(source);
                    source.close();
                    body= ResponseBody.create(body.contentType(), body.contentLength(), buffer);
                    String s=body.string();
                    LOGD(TAG, "convertResponse: " + s);
                    return JSON.parseObject(s, type);
                }catch (Exception e){
                    LOGE(TAG, "convert: ", e);
                }
                return null;
            }
        };
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new Converter<Object, RequestBody>() {
            @Override
            public RequestBody convert(Object value) throws IOException {
                String json = JSON.toJSONString(value);
                LOGD(TAG, "convertRequest: "+json);
                return RequestBody.create(mediaType, json);
            }
        };
    }
}
