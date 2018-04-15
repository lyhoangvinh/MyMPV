package lyhoangvinh.com.mymvp.model.base.api.rx;

import io.reactivex.Single;
import lyhoangvinh.com.mymvp.model.base.request.BaseRequest;
import lyhoangvinh.com.mymvp.model.base.request.LoginRequest;
import lyhoangvinh.com.mymvp.model.base.response.BaseResponse;
import lyhoangvinh.com.mymvp.model.Data;
import lyhoangvinh.com.mymvp.model.ResponseTest;
import lyhoangvinh.com.mymvp.model.User;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by LyHoangVinh on 01/04/2018.
 */
public interface RxService {

    @POST("user/profile")
    Single<BaseResponse<Data>> getAddress(@Body BaseRequest request);

    @POST("user/login")
    Single<BaseResponse<User>> login(@Body LoginRequest request);

    @POST("user/login")
    Single<ResponseTest> loginTest(@Body LoginRequest request);
}
