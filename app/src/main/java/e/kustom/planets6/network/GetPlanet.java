package e.kustom.planets6.network;

import android.os.AsyncTask;

import java.io.IOException;

import e.kustom.planets6.models.PlanetsResults;
import retrofit2.Call;
import retrofit2.Response;

public class GetPlanet extends AsyncTask<String, Void, PlanetsResults> {


    @Override
    protected PlanetsResults doInBackground(String... strings) {

        MyPlanet myPlanet= new Interceptor().getBasic();
        Call<PlanetsResults[]> planetResults = myPlanet.planmetByName();
        try {
            Response<PlanetsResults[]> response=planetResults.execute();
            if(200 == response.code()&response.isSuccessful()){
                return response.body()[0];
            }else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
