package com.vn.bgshop.boardgameshop.repository;


import com.vn.bgshop.boardgameshop.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User findByEmail(String email) {
        String query = "select u from User u where u.email ='"+email+"'";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query, User.class);
        return userTypedQuery.getResultList().isEmpty()?null:userTypedQuery.getResultList().get(0);
    }

    @Override
    public User findByEmailNotBanned(String email) {
        String query = "select u from User u where u.email ='"+email+"' and (u.status is null or u.status = '')";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query, User.class);
        return userTypedQuery.getResultList().isEmpty()?null:userTypedQuery.getResultList().get(0);
    }

    @Override
    public List<User> findByRole(String roleName) {
        String query = "SELECT u FROM User u JOIN u.roles r WHERE r.name='"+roleName+"'";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query, User.class);
        return userTypedQuery.getResultList();
    }

    @Override
    public List<User> findByRoleSize(int size) {
        String query = "SELECT u FROM User u WHERE u.roles.size = "+size;
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query, User.class);
        return userTypedQuery.getResultList();
    }

    @Override
    public List<User> findAll() {
        String query = "select u from User u";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query, User.class);
        return userTypedQuery.getResultList();
    }

    @Override
    public User findById(int id) {
        String query = "select u from User u where u.id ='"+id+"'";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query, User.class);
        return userTypedQuery.getResultList().isEmpty()?null:userTypedQuery.getResultList().get(0);
    }

    @Override
    public void save(User model) {
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        if (String.valueOf(model.getId()) != null) {
            //update
            entityManager.merge(model);
        }else {
            //add new
            entityManager.persist(model);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, User model) {
        if (String.valueOf(id) != null) {
            //update
            entityManager.merge(model);
        }
    }
}
