package connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.Utils;

/**
 * Created by AhlaamK on 20/8/2019
 */

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
