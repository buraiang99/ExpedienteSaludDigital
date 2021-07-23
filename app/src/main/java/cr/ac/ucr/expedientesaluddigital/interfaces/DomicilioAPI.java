package cr.ac.ucr.expedientesaluddigital.interfaces;

import java.util.Map;

import cr.ac.ucr.expedientesaluddigital.models.Domicilio;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DomicilioAPI {

    @FormUrlEncoded
    @POST("api/Domicilio")
    Call<Domicilio> insertarDomicilio(@Field("idProvincia") int idProvincia,
                                      @Field("idCanton") int idCanton,
                                      @Field("idDistrito") int idDistrito,
                                      @Field("detalles") String detalles);

    //@FormUrlEncoded
    //@POST("api/Domicilio")
    //Call<Domicilio> insertarDomicilio(@FieldMap Map<String, String> fields);

    @POST("api/Domicilio")
    Call<Domicilio> insertarDomicilio(@Body Domicilio domicilio);
}
