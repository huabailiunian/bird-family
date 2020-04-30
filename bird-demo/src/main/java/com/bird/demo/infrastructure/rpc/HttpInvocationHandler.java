package com.bird.demo.infrastructure.rpc;

import okhttp3.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author master
 * @date 2020-04-23 09:48
 */
public class HttpInvocationHandler implements InvocationHandler {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json;charset=UTF-8");

    private OkHttpClient httpClient;

    private ServiceRepository serviceRepository;

    private ParamEncode paramEncode;

    private ResultDecode resultDecode;

    public HttpInvocationHandler(OkHttpClient httpClient, ServiceRepository serviceRepository) {
        this.httpClient = httpClient;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Type returnType = method.getGenericReturnType();
        RequestBody requestBody = RequestBody.create(paramEncode.encode(args), MEDIA_TYPE);
        Request request = new Request.Builder().post(requestBody).url("").build();
        Response response = httpClient.newCall(request).execute();
        String result = Objects.requireNonNull(response.body()).string();
        Object object = resultDecode.decode(result, returnType);
        return object;
    }
}
