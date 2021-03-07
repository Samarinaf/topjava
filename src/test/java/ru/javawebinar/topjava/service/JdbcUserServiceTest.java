package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.web.Profiles;

@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends UserServiceTest{
}
