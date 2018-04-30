package lyhoangvinh.com.mymvp.base.request;

import lombok.AllArgsConstructor;

/**
 * Created by LyHoangVinh on 17/04/2018.
 */

@AllArgsConstructor
public class ProfileRequest extends BaseRequest {
    private String email;
    private String firstname;
    private String lastname;
}
