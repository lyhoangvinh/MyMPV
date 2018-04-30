package lyhoangvinh.com.mymvp.base.api.github;

import io.reactivex.Single;
import lyhoangvinh.com.mymvp.constant.ConstantsApi;
import lyhoangvinh.com.mymvp.model.UserGithub;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by LyHoangVinh on 30/04/2018.
 */
public interface GithubService {

    @GET("user")
    Single<Response<UserGithub>> login(@Header(ConstantsApi.HEADER_AUTH) String basicToken);
}
