package lyhoangvinh.com.mymvp.base.response;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Response;

@Getter
@Setter
public class ResponseZip<T1, T2> {
    Response<T1> t1;
    Response<T2> t2;
}
