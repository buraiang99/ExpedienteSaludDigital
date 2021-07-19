package cr.ac.ucr.expedientesaluddigital.interfaces;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.models.Provincia;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProvinciaAPI {

    @GET("api/Provincia")
    Call<List<Provincia>> getProvincia();
}
