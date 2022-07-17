package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUser(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUser(String model, int series) {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("select id FROM Car WHERE model=:model and series=:series")
                .setParameter("model", model).setParameter("series", series);
        Long id = query.getSingleResult();
        System.out.println(id);
        String HQL = "FROM User u LEFT JOIN FETCH u.car WHERE u.id=:car_id";
        return sessionFactory.getCurrentSession().createQuery(HQL, User.class)
                .setParameter("car_id", id).getSingleResult();
    }


}
