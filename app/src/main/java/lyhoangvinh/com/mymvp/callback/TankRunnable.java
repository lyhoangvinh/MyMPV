package lyhoangvinh.com.mymvp.callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lyhoangvinh.com.mymvp.model.Address;

/**
 * Created by ADMIN on 12/5/2017.
 */

public class TankRunnable implements Runnable {

    private String response;
    private AddressCallback callback;
    private List<Address> listAddress;
    private Address address;

    public TankRunnable(String response, AddressCallback callback) {
        this.response = response;
        this.callback = callback;
        listAddress = new ArrayList<>();
    }

    @Override
    public void run() {
        if (callback != null) {
            try {
                JSONObject obj = new JSONObject(response);
                JSONObject jsData = obj.getJSONObject("data");
                JSONArray addressJson = jsData.getJSONArray("address");
                if (addressJson.length() <= 0) {
                    callback.OnNullAddress();
                } else {
                    for (int i = 0; i <= addressJson.length(); i++) {
                        JSONObject jsonObject = addressJson.getJSONObject(i);
                        int idAdress = jsonObject.getInt("id");
                        String lableName = jsonObject.getString("label");
                        String street = jsonObject.getString("street");
                        String postcode = jsonObject.getString("postcode");
                        String locationll = jsonObject.getString("locationll");
                        address = new Address(idAdress, lableName, street, postcode, locationll);
                        listAddress.add(address);
                        callback.OnComplete(listAddress);
                    }
                }

            } catch (JSONException e) {
                callback.OnError(e);
            }
        }
    }
}
