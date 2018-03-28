package lyhoangvinh.com.mymvp.model.object;

import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by LyHoangVinh on 21/03/2018.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends RealmObject{
    private int id;
    private String label;
    private String street;
    private String postcode;
    private String locationall;
}
