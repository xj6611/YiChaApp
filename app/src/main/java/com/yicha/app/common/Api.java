package com.yicha.app.common;

import android.text.TextUtils;
import android.util.Log;


import com.yicha.app.RetrofitServiceApi.RetrofitService;
import com.yicha.app.Utlis.BaseApi;
import com.yicha.app.Utlis.DeviceUtil;
import com.yicha.app.Utlis.FastDataUtil;
import com.yicha.app.Utlis.ToastUtil;
import com.yicha.app.common.json.JsonConverterFactory;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.CacheControl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Api service
 * Created by dulei on 2017/8/23.
 */

public class Api {


    private int maxStale = 60 * 60 * 24;
    private static final String PLATFORM = "android";
    private static final String CLIENT = "lg";

    public static OkHttpClient.Builder client() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS);

        addCheckNetworkChain(httpClientBuilder);

        httpClientBuilder.networkInterceptors()
                .add(chain -> {
                    Request.Builder req = chain.request().newBuilder();
                    req.addHeader("Accept-Charset", "utf-8")
                            .addHeader("Connection", "keep-alive")
                            .addHeader("Accept", "*/*")
                            .addHeader("x-version", "1.0.0")
                            .addHeader("x-platform", PLATFORM)
                            .addHeader("x-client", CLIENT);

                    addAuthorizationHeader(req);
                    Response response = chain.proceed(req.build());
                    return response.newBuilder().build();
                });
        httpClientBuilder.addInterceptor(
                new HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                )
        );

        httpClientBuilder.addNetworkInterceptor(chain -> {
            /*
            * 接口字段返回 拦截器
            * */
            Charset UTF8 = Charset.forName("UTF-8");
            Response response = chain.proceed(chain.request());
            ResponseBody responseBody = response.body();
            Log.d("response", response.toString());
            String resultsBody = null;
            if (HttpHeaders.hasBody(response)) {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        e.printStackTrace();
                    }
                }
                resultsBody = buffer.clone().readString(charset);
            }

            try {
//                if(resultsBody!=null&&!TextUtils.isEmpty(resultsBody)) {
//                    Gson gson = new Gson();
//                    RequestLoginBean requestLoginBean = gson.fromJson(resultsBody, RequestLoginBean.class);
//
//                    if (!requestLoginBean.isLogin()) {
//                        if (FastDataUtil.getToken() != null && !TextUtils.isEmpty(FastDataUtil.getToken())) {
//                            FastDataUtil.setToken("");
//                            EventBus.getDefault().post(new FinishEvent());
//                            LoginActivity.open(StaffApplication.getInstanse());
//                        }
//                    }
//
//                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return response;
        });

        return httpClientBuilder;
    }

    public RetrofitService apiService() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

        Retrofit retrofit = retrofitBuilder
                .baseUrl(BaseApi.BASE)
                .client(client().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitService.class);
    }

    private static Retrofit initRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BaseApi.BASE)
                .addConverterFactory(JsonConverterFactory.create())
                .client(client().build())
                .build();
    }


    protected static void addCheckNetworkChain(OkHttpClient.Builder httpClientBuilder) {
        httpClientBuilder.addInterceptor(chain -> {

            Request request = chain.request();
            if (!DeviceUtil.isNetworkAvailable()) {

                Maybe.just("没有可用网络, 请连接网络后重试")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ToastUtil::show, throwable -> {
                            Log.e("Debug", "error", throwable);
                        });

                // @notice  由于服务器不支持缓存，所以这里其实是没有起作用，等服务器起作用就可以了
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            return chain.proceed(request)
                    .newBuilder()
                    .build();
        });
    }

    public static void addAuthorizationHeader(Request.Builder req) {
        if (!TextUtils.isEmpty(FastDataUtil.getToken())) {
            String authorizationHeader = String.format(Locale.CHINA, "Bearer %s", FastDataUtil.getToken());
            req.addHeader("Authorization", authorizationHeader);
            Log.e("TAG", "========="+authorizationHeader);
        } else {
            req.addHeader("Authorization", "Basic bGc6YmFyQExn");
        }
    }

    static Api api = null;

    public static RetrofitService getInstanceGson() {

        if (api == null) {
            api = new Api();
        }
        return api.getService();
    }

    public RetrofitService getService() {
        return apiService();
    }

}
