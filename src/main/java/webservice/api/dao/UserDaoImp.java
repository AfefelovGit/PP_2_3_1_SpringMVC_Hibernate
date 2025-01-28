package webservice.api.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import webservice.api.model.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        TypedQuery<User> query = em.createQuery("from User", User.class);
        users = query.getResultList();
        return users;
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }
}

