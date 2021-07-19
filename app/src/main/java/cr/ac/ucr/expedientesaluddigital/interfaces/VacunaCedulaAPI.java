package cr.ac.ucr.expedientesaluddigital.interfaces;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.models.Vacuna;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VacunaCedulaAPI {
    @GET("api/Vacuna/{cedula}")
    public Call<List<Vacuna>> find(@Path("cedula")String id);
}
