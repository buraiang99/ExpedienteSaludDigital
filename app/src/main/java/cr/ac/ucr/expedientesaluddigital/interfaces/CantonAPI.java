package cr.ac.ucr.expedientesaluddigital.interfaces;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.models.Canton;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CantonAPI {
    @GET("api/Canton")
    Call<List<Canton>> getCanton();
}
