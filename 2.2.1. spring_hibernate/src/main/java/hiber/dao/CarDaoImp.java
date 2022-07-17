package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listUsers() {
        TypedQuery<Car> typedQuery = sessionFactory.getCurrentSession().createQuery("FROM Car");
        return typedQuery.getResultList();
    }

    @Override
    public Car getCar(Long id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }
}
