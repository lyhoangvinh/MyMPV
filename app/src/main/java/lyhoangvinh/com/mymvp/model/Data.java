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
    public User user;
    public List<Address> address;
}
