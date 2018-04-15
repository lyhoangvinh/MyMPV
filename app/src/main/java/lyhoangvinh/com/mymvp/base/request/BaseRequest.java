package lyhoangvinh.com.mymvp.base.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by LyHoangVinh on 25/03/2018.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest {
    @SerializedName("requestToken")
    @Expose
    String requestToken;
}
