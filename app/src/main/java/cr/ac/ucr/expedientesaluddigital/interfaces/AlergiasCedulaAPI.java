package cr.ac.ucr.expedientesaluddigital.interfaces;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.models.Alergia;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlergiasCedulaAPI {
    @GET("api/Alergia/{cedula}")
    public Call<List<Alergia>> find(@Path("cedula")String id);
}
