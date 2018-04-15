package lyhoangvinh.com.mymvp.model;

import lombok.Getter;
import lombok.Setter;

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
