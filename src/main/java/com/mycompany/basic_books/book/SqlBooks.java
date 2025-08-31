/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basic_books.book;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author semih
 */
public class SqlBooks {

    Session session;
    SessionFactory ssFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Books.class)
            .buildSessionFactory();

    public void exit() {
        if (session != null) {
            session.close();
        }

        if (ssFactory != null) {
            ssFactory.close();
        }
    }

    public void list(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = ssFactory.getCurrentSession();
            session.beginTransaction();

            List<Books> listBooks = session.createQuery("from Books", Books.class).getResultList();

            for (Books book : listBooks) {
                model.addRow(new Object[]{book.getId(), book.getName(), book.getType(), book.getWrite(), book.getPage(), book.getPublisher()});
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void search(JTable table, String name, String type, String writer, String publisher) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = ssFactory.getCurrentSession();
            session.beginTransaction();

            List<Books> listBooks = session.createQuery("from Books b WHERE b.name LIKE :name AND b.type LIKE :type AND b.write LIKE :write AND b.publisher LIKE :publisher", Books.class)
                    .setParameter("name", "%" + name + "%")
                    .setParameter("type", "%" + type + "%")
                    .setParameter("write", "%" + writer + "%")
                    .setParameter("publisher", "%" + publisher + "%")
                    .getResultList();

            for (Books book : listBooks) {
                model.addRow(new Object[]{book.getId(), book.getName(), book.getType(), book.getWrite(), book.getPage(), book.getPublisher()});
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void add(Books book) {
        try {
            session = ssFactory.getCurrentSession();
            session.beginTransaction();

            session.persist(book);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(int bookID, Books newBook) {
        try {
            session = ssFactory.getCurrentSession();
            session.beginTransaction();

            Books _book = session.getReference(Books.class, bookID);

            _book.setName(newBook.getName());
            _book.setPage(newBook.getPage());
            _book.setPublisher(newBook.getPublisher());
            _book.setType(newBook.getType());
            _book.setWrite(newBook.getWrite());

            session.merge(_book);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Books book) {
        try {
            session = ssFactory.getCurrentSession();
            session.beginTransaction();

            session.remove(book);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
