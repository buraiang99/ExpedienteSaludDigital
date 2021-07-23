package cr.ac.ucr.expedientesaluddigital.interfaces;

import cr.ac.ucr.expedientesaluddigital.models.Paciente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PacienteAPI {
    @GET("api/Paciente/{cedula}")
    public Call<Paciente> find(@Path("cedula")String id);

    @POST("api/Paciente")
    Call<Paciente> insertarPaciente(@Body Paciente paciente);

    @PUT("api/Paciente/{cedula}")
    Call<Paciente> actualizar(@Path("cedula") String cedula,@Body Paciente paciente);
}
