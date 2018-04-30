package lyhoangvinh.com.mymvp.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LyHoangVinh on 30/04/2018.
 */

@Getter
@Setter
public class UserGithub {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    public Long id;

    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("avatar_url")
    @Expose
    public String avatarUrl;
    @SerializedName("gravatar_id")
    @Expose
    public String gravatarId;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("followers_url")
    @Expose
    public String followersUrl;
    @SerializedName("following_url")
    @Expose
    public String followingUrl;
    @SerializedName("gists_url")
    @Expose
    public String gistsUrl;
    @SerializedName("starred_url")
    @Expose
    public String starredUrl;
    @SerializedName("subscriptions_url")
    @Expose
    public String subscriptionsUrl;
    @SerializedName("organizations_url")
    @Expose
    public String organizationsUrl;
    @SerializedName("repos_url")
    @Expose
    public String reposUrl;
    @SerializedName("events_url")
    @Expose
    public String eventsUrl;
    @SerializedName("received_events_url")
    @Expose
    public String receivedEventsUrl;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("site_admin")
    @Expose
    public Boolean siteAdmin;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("company")
    @Expose
    public String company;
    @SerializedName("blog")
    @Expose
    public String blog;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("hireable")
    @Expose
    public Boolean hireable;
    @SerializedName("bio")
    @Expose
    public String bio;
    @SerializedName("public_repos")
    @Expose
    public long publicRepos;
    @SerializedName("public_gists")
    @Expose
    public long publicGists;
    @SerializedName("followers")
    @Expose
    public long followers;
    @SerializedName("following")
    @Expose
    public long following;
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;
    @SerializedName("private_gists")
    @Expose
    public long privateGists;
    @SerializedName("total_private_repos")
    @Expose
    public long totalPrivateRepos;
    @SerializedName("owned_private_repos")
    @Expose
    public long ownedPrivateRepos;
    @SerializedName("disk_usage")
    @Expose
    public long diskUsage;
    @SerializedName("collaborators")
    @Expose
    public long collaborators;
    @SerializedName("two_factor_authentication")
    @Expose
    public Boolean twoFactorAuthentication;
    @SerializedName("plan")
    @Expose
    public Plan plan;

    public boolean equals(@Nullable User user) {
        return user != null && id.equals(user.getId());
    }

    public String getDisplayName() {
        return TextUtils.isEmpty(name) ? login : name;
    }
}
