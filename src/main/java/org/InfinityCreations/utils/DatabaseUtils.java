package org.InfinityCreations.utils;

import org.InfinityCreations.controller.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

public class DatabaseUtils {

    public static int getColumnMaxLength(String tableName, String columnName) {
    String query = "SELECT CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS " +
            "WHERE TABLE_NAME = :tableName AND COLUMN_NAME = :columnName";

    SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
    Session session = sessionFactory.openSession();
    try {
        NativeQuery<Integer> q = session.createNativeQuery(query, Integer.class);
        q.setParameter("tableName", tableName.toUpperCase());
        q.setParameter("columnName", columnName.toUpperCase());

        Integer maxLength = (Integer) q.uniqueResult();
        return maxLength != null ? maxLength : -1;
    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    } finally {
        session.close();
    }
}
}
