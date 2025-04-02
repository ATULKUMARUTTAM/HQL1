package com.atuluttam;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil
{
    public static final SessionFactory sessionFactory = buildSessionfactory();
    public static SessionFactory buildSessionfactory()
    {
        Configuration configuration = new Configuration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void shutdown()
    {
        sessionFactory.close();
    }

}
