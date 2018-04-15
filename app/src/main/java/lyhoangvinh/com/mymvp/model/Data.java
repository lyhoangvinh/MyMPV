package lyhoangvinh.com.mymvp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by LyHoangVinh on 25/03/2018.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    public String firstname;
    public String lastname;
    public String fbid;
    public String firebaseId;
    public String email;
    public String emailverified;
    public String mobile;
    public String mobileverified;
    public String picture;
    public List<Address> address;
}
