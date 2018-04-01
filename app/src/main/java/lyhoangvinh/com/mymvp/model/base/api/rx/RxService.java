package lyhoangvinh.com.mymvp.model.base.api.rx;

import io.reactivex.Single;
import lyhoangvinh.com.mymvp.model.base.request.BaseRequest;
import lyhoangvinh.com.mymvp.model.base.response.BaseResponse;
import lyhoangvinh.com.mymvp.model.object.Data;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by LyHoangVinh on 01/04/2018.
 */
public interface RxService {

    @POST("user/profile")
    Single<BaseResponse<Data>> getAddress(@Body BaseRequest request);
}
