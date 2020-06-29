package anotacoes.com.retrofitjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import anotacoes.com.retrofitjava.model.ModelWine;
import anotacoes.com.retrofitjava.network.GetDataService;
import anotacoes.com.retrofitjava.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView firstWine;
    private TextView secondWine;
    private TextView thirdWine;
    private TextView textAboutWine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstWine = findViewById(R.id.txt_name1_ID);
        secondWine =  findViewById(R.id.txt_name2_ID);
        thirdWine = findViewById(R.id.txt_name3_ID);
        textAboutWine = findViewById(R.id.txt_text_ID);

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<ModelWine> call = service.getData("steak","e3d9c39a9b134ddabd2fb1faccb60865");
        call.enqueue(new Callback<ModelWine>() {
            @Override
            public void onResponse(Call<ModelWine> call, Response<ModelWine> response) {

                ModelWine responseModel = response.body();
                String getFirstWine = responseModel.getPairedWines().get(0);
                String getSecondWine = responseModel.getPairedWines().get(1);
                String getThirdWine= responseModel.getPairedWines().get(2);
                String getTextAboutWine = responseModel.getPairingText();

                firstWine.setText(getFirstWine);
                secondWine.setText(getSecondWine);
                thirdWine.setText(getThirdWine);
                textAboutWine.setText(getTextAboutWine);


            }

            @Override
            public void onFailure(Call<ModelWine> call, Throwable t) {
                Log.d("TAG", "onFailure: " +t);

            }
        });

    }
}
