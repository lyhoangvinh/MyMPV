package lyhoangvinh.com.mymvp.model.base.api.retrofit;

import lyhoangvinh.com.mymvp.Constant.ConstantsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LyHoangVinh on 25/03/2018.
 */

public class ApiClient {

    public static Retrofit retrofit;

    public static Retrofit getInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(ConstantsApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
