package anotacoes.com.retrofitjava.network;

import anotacoes.com.retrofitjava.model.ModelWine;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetDataService {

    @Headers("Content-Type: application/json")
    @GET("/food/wine/pairing")
    Call<ModelWine> getData(@Query("food") String username,@Query("apiKey") String apiKey);


}
