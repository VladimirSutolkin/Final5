package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery(" from User").getResultList();
    }
    public void createUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public User readUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(Long id, User user) {
        User edit = entityManager.find(User.class, id);
        edit.setUsername(user.getUsername());
        edit.setFirstname(user.getFirstname());
        edit.setSecondName(user.getSecondName());
        edit.setEmail(user.getEmail());
        edit.setPassword(user.getPassword());
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findByUsername(String username) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.username =:username").setParameter("username", username).getResultList();
    }
}

