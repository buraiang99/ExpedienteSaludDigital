package cr.ac.ucr.expedientesaluddigital.interfaces;

import cr.ac.ucr.expedientesaluddigital.models.Citas;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CitaIdAPI {
    @GET("api/Cita/{id}")
    public Call<Citas> find(@Path("id")int id);
}
