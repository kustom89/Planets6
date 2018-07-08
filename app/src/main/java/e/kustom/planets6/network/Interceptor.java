package e.kustom.planets6.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Interceptor {

    private static final String BASE_URL="https://swapi.co/api/";

    public MyPlanet getBasic(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new okhttp3.Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest=chain.request();
                Request request= originalRequest.newBuilder().build();
                Response response= chain.proceed(request);

                int retryCount = 0;
                while (!response.isSuccessful() && retryCount < 3) {
                    retryCount++;
                    response = chain.proceed(request);
                }

                return response;

            }
        });
        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        MyPlanet request = interceptor.create(MyPlanet.class);

        return request;

    }
}
