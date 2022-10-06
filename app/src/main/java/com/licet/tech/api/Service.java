package com.licet.tech.api;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
   // private static final String BASE_URL = "http://jeevambrudham.startupman.in/Api/Api/";
        private static final String BASE_URL = "https://licettechnologies.com/schoolapp/api/";
   // private static final String BASE_URL = "https://diskney.com/jpsr/";
    private static final String GOOGLE_BASE_URL = "https://maps.googleapis.com/maps/api/";

    private static final OkHttpClient httpClient = new OkHttpClient();

    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()));

    private static final Retrofit.Builder GoogleBuilder =
            new Retrofit.Builder()
                    .baseUrl(GOOGLE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = GoogleBuilder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }


    public static <S> S createServiceHeader(Class<S> serviceClass) {

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();


                                // Request customization: add request headers
                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Content-Type", "application/x-www-form-urlencoded");
//                                        .method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        })
                .build();

        Retrofit retrofit = builder.client(okClient).build();
        return retrofit.create(serviceClass);
    }

}