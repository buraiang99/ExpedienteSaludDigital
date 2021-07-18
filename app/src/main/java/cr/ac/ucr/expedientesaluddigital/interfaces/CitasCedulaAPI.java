package cr.ac.ucr.expedientesaluddigital.interfaces;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.models.Cita;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CitasCedulaAPI {
    @GET("api/Cita/{cedula}")
    public Call<List<Cita>> find(@Path("cedula")String id);
}
