/*
 *  Copyright(c) 2017 lizhaotailang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yicha.app.RetrofitServiceApi;

import android.util.Log;

import com.yicha.app.BaseApplication;
import com.yicha.app.Utlis.BaseApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static final String PLATFORM = "android";
    private static final String CLIENT = "lg";

    private static class ClientHolder {

        private static OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())//添加请求拦截
                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间
                .retryOnConnectionFailure(true);

        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE)
                .client(client.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }




    public static Retrofit getInstance() {
        return ClientHolder.retrofit;
    }

    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request original = chain.request();
            Request request = original.newBuilder()
            .addHeader("Accept-Charset", "utf-8")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Accept", "*/*")
                    .addHeader("x-version", "1.0.0")
                    .addHeader("x-platform", PLATFORM)
                    .addHeader("x-client", CLIENT)
                    .method(original.method(), original.body())
                    .build();
// .header("token", BaseApplication.getInstance().getToken())
            Log.i("http","token====>"+ BaseApplication.getInstance().getToken());
            Log.i("http","url====>"+ request.url());
            Log.i("http","headers====>"+ request.headers());
            Response response= null;
            try {
                response = chain.proceed(request);
                ResponseBody responseBody = response.peekBody(1024 * 1024);
                Log.i("http","json====>"+ responseBody.string());
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("http","IOException====>"+ e);
            }

            return response;
        }
    }

}
