package com.atuluttam;

import com.atuluttam.Student;
import com.atuluttam.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        insertStudent("Alice", "Computer Science", 22);
        insertStudent("Bob", "Mathematics", 20);

        retrieveStudents();

        updateStudent(1, "Physics");

        deleteStudent(2);

        HibernateUtil.shutdown();
    }

    private static void insertStudent(String name, String course, int age)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            Student student = new Student(name, course, age);
            session.save(student);
            transaction.commit();
        }
    }

    private static void retrieveStudents()
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            String hql = "FROM Student";
            Query<Student> query = session.createQuery(hql, Student.class);
            List<Student> students = query.list();

            for (Student student : students)
            {
                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getName() +
                        ", Course: " + student.getCourse());
            }

        }
    }

    private static void updateStudent(int id, String newCourse)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();

            String hql = "UPDATE Student S SET S.course = :course WHERE S.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("course", newCourse);
            query.setParameter("id", id);
            int result = query.executeUpdate();

            transaction.commit();

            System.out.println("Rows affected: " + result);
        }
    }

    private static void deleteStudent(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            String hql = "DELETE FROM Student S WHERE S.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int result = query.executeUpdate();

            transaction.commit();

            System.out.println("Rows deleted: " + result);
        }
    }
}
