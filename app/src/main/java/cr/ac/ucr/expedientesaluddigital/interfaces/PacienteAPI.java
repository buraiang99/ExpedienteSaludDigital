package cr.ac.ucr.expedientesaluddigital.interfaces;

import cr.ac.ucr.expedientesaluddigital.models.Paciente;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PacienteAPI {
    @GET("api/Paciente/{cedula}")
    public Call<Paciente> find(@Path("cedula")String id);
}
