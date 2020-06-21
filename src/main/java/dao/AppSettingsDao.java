package dao;

import entities.Alias;
import org.hibernate.Session;
import settings.AppSettings;

public class AppSettingsDao {

    public AppSettings findSettingsById(Session session, int id) {
        return session.get(AppSettings.class, id);
    }

    public void save(Session session, AppSettings settings) {

        session.persist(settings);
    }

    public void update(Session session, AppSettings settings) {
        session.merge(settings);
    }

    public void delete(Session session, AppSettings settings) {
        session.remove(settings);
    }
}
