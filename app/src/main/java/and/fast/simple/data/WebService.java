package and.fast.simple.data;

import and.fast.simple.data.entities.ImageListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("Girl/type/Girl/page/{page}/count/{count}")
    Call<ImageListResponse> images(@Path("page") int page, @Path("count") int count);

}
