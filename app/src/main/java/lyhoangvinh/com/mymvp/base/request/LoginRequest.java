package lyhoangvinh.com.mymvp.base.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by lyhoangvinh on 10/10/2017.
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("password")
    @Expose
    String password;
}
