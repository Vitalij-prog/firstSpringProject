package org.vital.hibdao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.vital.models.Product;
import org.vital.models.User;
import org.vital.util.HibernateSessionFactoryUtil;

import java.util.List;

public class ProductDao {

    public List<Product> findAll() {
        List<Product> products = (List<Product>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Product").list();
        return products;
    }

    public Product findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Product.class, id);
    }

    public void save(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(product);
        tx1.commit();
        session.close();
    }
}
