package Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AhlaamK on 20/8/2019
 */
public class ApiResponseParser {
    @SerializedName("status")
    private final String status;

    @SerializedName("num_results")
    private final int num_results;

    @SerializedName("results")
    private final List<NewsModel> results;
//COnstructor
    public ApiResponseParser(String status, int num_results, List<NewsModel> results) {
        this.status = status;
        this.num_results = num_results;
        this.results = results;
    }

    public List<NewsModel> getResults() {
        return results;
    }
}
