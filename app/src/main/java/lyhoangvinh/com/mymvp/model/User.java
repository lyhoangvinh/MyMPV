package lyhoangvinh.com.mymvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {

    @SerializedName("uid")
    @Expose
    private String id;

    @SerializedName("firebaseId")
    @Expose
    private String firebaseId;

    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("devicetoken")
    @Expose
    private String devicetoken;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("driverRating")
    @Expose
    private Double driverRating;

    @SerializedName("firebaseToken")
    @Expose
    private String firebaseToken;

    @SerializedName("bank_account")
    @Expose
    private String bank_account;

    public boolean equals(User user) {
        return user != null && this.token.equals(user.token);
    }

    public String getFullName() {
        String firstName = android.text.TextUtils.isEmpty(getFirstname()) ? "" : getFirstname();
        String lastName = android.text.TextUtils.isEmpty(getLastname()) ? "" : getLastname();

        if (!android.text.TextUtils.isEmpty(firstName) && android.text.TextUtils.isEmpty(lastName)) {
            return firstName;
        } else if (android.text.TextUtils.isEmpty(firstName) && !android.text.TextUtils.isEmpty(lastName)) {
            return lastName;
        } else if (!android.text.TextUtils.isEmpty(firstName) && !android.text.TextUtils.isEmpty(lastName)) {
            return firstName + " " + lastName;
        }
        return getNameEmail(getEmail());
    }

    private static String getNameEmail(String email) {
        if (!android.text.TextUtils.isEmpty(email)) {
            return email.substring(0, email.indexOf("@"));
        }
        return "";
    }
}
