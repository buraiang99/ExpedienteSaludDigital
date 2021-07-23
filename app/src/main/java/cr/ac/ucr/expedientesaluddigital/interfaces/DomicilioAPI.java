package cr.ac.ucr.expedientesaluddigital.interfaces;

import java.util.Map;

import cr.ac.ucr.expedientesaluddigital.models.Domicilio;
import cr.ac.ucr.expedientesaluddigital.models.RespuestaDomicilio;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DomicilioAPI {

    @GET("api/Domicilio/{cedula}")
    Call<RespuestaDomicilio> buscarDomicilio(@Path("cedula") String id);

    @POST("api/Domicilio")
    Call<Domicilio> insertarDomicilio(@Body Domicilio domicilio);

    @PUT("api/Domicilio/{cedula}")
    Call<RespuestaDomicilio> actualizar(@Path("cedula") String id, @Body RespuestaDomicilio respuestaDomicilio);
}
