package lyhoangvinh.com.mymvp.model.base.api.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import lyhoangvinh.com.mymvp.Constant.ConstantsApi;
import lyhoangvinh.com.mymvp.utils.Functions;

/**
 * Created by LyHoangVinh on 21/03/2018.
 */

public class BaseApi {

    private Context context;
    private static BaseApi baseApi;

    public BaseApi(Context context) {
        this.context = context;
    }

    public static synchronized BaseApi getInstance(Context context){
        if (baseApi == null){
            baseApi = new BaseApi(context);
        }
        return baseApi;
    }

    public void loadData(Response.Listener<String> response, Response.ErrorListener error) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConstantsApi.URL_GET_ADDRESS, response, error) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(ConstantsApi.RQ_TOKEN, Functions.getUser().getToken());
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
