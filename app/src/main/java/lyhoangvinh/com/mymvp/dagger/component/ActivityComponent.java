package lyhoangvinh.com.mymvp.dagger.component;

import dagger.Component;
import lyhoangvinh.com.mymvp.dagger.module.ActivityModule;

@Component(dependencies = {AppComponent.class} ,modules = {ActivityModule.class})
public interface ActivityComponent {
}
