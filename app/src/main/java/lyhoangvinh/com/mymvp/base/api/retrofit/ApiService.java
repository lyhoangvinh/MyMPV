package lyhoangvinh.com.mymvp.base.api.retrofit;

import lyhoangvinh.com.mymvp.base.request.BaseRequest;
import lyhoangvinh.com.mymvp.base.response.BaseEntity;
import lyhoangvinh.com.mymvp.base.response.BaseResponse;
import lyhoangvinh.com.mymvp.model.Data;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by LyHoangVinh on 25/03/2018.
 */

public interface ApiService {

    @POST("user/profile")
    Call<BaseEntity<Data>> getAddress(@Body BaseRequest request);

    @POST("user/profile")
    Call<BaseResponse<Data>> getAddressTest(@Body BaseRequest request);
}
