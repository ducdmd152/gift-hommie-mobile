package com.mobilers.gift_hommie_mobile.service.ghn;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GHNApiClient {
    private static final String BASE_URL = "https://dev-online-gateway.ghn.vn/shiip/public-api/";
    private static final String TOKEN = "1f1091d3-06cb-11ee-aaed-4aa85994373f";
    public static IGHNService getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add token
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("token", TOKEN)
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        // loging
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        httpClient.addInterceptor(loggingInterceptor);

        // build
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(IGHNService.class);
    }
}
