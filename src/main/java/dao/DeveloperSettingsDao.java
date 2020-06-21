package dao;

import org.hibernate.Session;
import settings.AppSettings;
import settings.DeveloperSettings;

public class DeveloperSettingsDao {

    public DeveloperSettings findSettingsById(Session session, int id) {
        return session.get(DeveloperSettings.class, id);
    }

    public void save(Session session, DeveloperSettings settings) {
        session.persist(settings);
    }

    public void update(Session session, DeveloperSettings settings) {
        session.merge(settings);
    }

    public void delete(Session session, DeveloperSettings settings) {
        session.remove(settings);
    }

}
