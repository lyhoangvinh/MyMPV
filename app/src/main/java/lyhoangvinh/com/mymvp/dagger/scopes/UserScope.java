package lyhoangvinh.com.mymvp.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Duy Pham on 05/17.
 * Scope for instances which be created after user userLogin and until user logout
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
