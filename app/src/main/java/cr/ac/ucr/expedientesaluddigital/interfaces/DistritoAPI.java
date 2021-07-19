package cr.ac.ucr.expedientesaluddigital.interfaces;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.models.Distrito;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DistritoAPI {
    @GET("api/Distrito")
    Call<List<Distrito>> getDistrito();
}
