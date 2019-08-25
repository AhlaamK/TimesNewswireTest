package connection;

import Model.ApiResponseParser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AhlaamK on 20/8/2019
 */

public interface ApiInterface {

    @GET("all.json?")
    Call<ApiResponseParser> getNewsDetails(@Query("api-key") String apiKey);
}
