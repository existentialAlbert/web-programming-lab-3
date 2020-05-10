package databaseInfo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateSessionFactoryUtil {
    //XML based configuration
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            //Create Properties, can be read from property files too
            Properties props = new Properties();
            props.put("persistenceUnitName", "hibernate");
            props.put("hibernate.connection.url", "jdbc:oracle:thin:@//localhost:1521/orbis");
            props.put("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
            props.put("hibernate.connection.username", "s251806");
            props.put("hibernate.connection.password", "vxl364");
            props.put("hibernate.archive.autodetection", "class");
            props.put("hibernate.show_sql", "true");
            props.put("hibernate.format_sql", "true");
            props.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
            props.put("hibernate.hbm2ddl.auto", "update");
            props.put("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider");

            configuration.setProperties(props);
            configuration.addAnnotatedClass(model.Point.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}