package ru.javawebinar.topjava;

import org.springframework.lang.NonNull;
import org.springframework.test.context.ActiveProfilesResolver;
import ru.javawebinar.topjava.web.Profiles;

public class ActiveDbProfileResolver implements ActiveProfilesResolver {
    @Override
    public @NonNull
    String[] resolve(@NonNull Class<?> aClass) {
        return new String[]{Profiles.getActiveDbProfile()};
    }
}
