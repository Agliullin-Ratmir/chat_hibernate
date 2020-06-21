package service;

import dao.AppSettingsDao;
import dao.DeveloperSettingsDao;
import entities.Alias;
import entities.Message;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import settings.AbstractSettings;
import settings.AppSettings;
import settings.DeveloperSettings;

import java.io.File;

public class SettingsTest {

    private static final String OS_NAME_PROPERTY_KEY = "os.name";
    private static final String DEVELOPER_EMAIL = "developer@gmail.com";
    private static final String USER_NAME_PROPERTY_KEY = "user.name";
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    @Before
    public void before() {
        sessionFactory = createSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @Test
    public void testSettings() {
        AppSettingsDao appSettingsDao = new AppSettingsDao();
        DeveloperSettingsDao developerSettingsDao = new DeveloperSettingsDao();
        putSettings(appSettingsDao, developerSettingsDao);
        System.out.println("settings have been written");

        System.out.println(appSettingsDao.findSettingsById(session, 1));
        System.out.println(developerSettingsDao.findSettingsById(session, 2));
    }

    private void putSettings(AppSettingsDao appSettingsDao, DeveloperSettingsDao developerSettingsDao) {
        appSettingsDao.save(session, getAppSettings());
        developerSettingsDao.save(session, getDeveloperSettings());
    }

    private AppSettings getAppSettings() {
        AppSettings settings = new AppSettings();
        settings.setOsName(System.getProperty(OS_NAME_PROPERTY_KEY));
        settings.setMemorySize(new File("/").getTotalSpace());
        return settings;
    }

    private DeveloperSettings getDeveloperSettings() {
        DeveloperSettings settings = new DeveloperSettings();
        settings.setEmail(DEVELOPER_EMAIL);
        settings.setName(System.getProperty(USER_NAME_PROPERTY_KEY));
        return settings;
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Message.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Alias.class)
                .addAnnotatedClass(AbstractSettings.class)
                .addAnnotatedClass(AppSettings.class)
                .addAnnotatedClass(DeveloperSettings.class);
        configuration.setProperty("hibernate.dialect",
                "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./data/db");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.show_sql", "true");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    @After
    public void after() {
        session.close();
        sessionFactory.close();
    }
}
