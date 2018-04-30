package lyhoangvinh.com.mymvp.base.response;

import lombok.Getter;
import lombok.Setter;
import lyhoangvinh.com.mymvp.model.Data;

/**
 * Created by LyHoangVinh on 01/04/2018.
 */
@Getter
@Setter
public class ResponseTest {
    private String status;
    private String token;
    private String message;
    private Data data;
}
