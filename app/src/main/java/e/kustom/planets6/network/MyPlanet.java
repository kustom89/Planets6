package e.kustom.planets6.network;

import e.kustom.planets6.models.PlanetsResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyPlanet {


    @GET("api/planets/2/")
    Call<PlanetsResults[]> planmetByName();
}
