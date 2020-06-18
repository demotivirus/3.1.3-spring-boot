package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> listUsers() {
        Session session = entityManager.unwrap(Session.class);
        System.err.println("Select all users");
        return session.createQuery("from User").list();
    }

    @Override
    @Transactional
    public void add(User user) {
        Session session = entityManager.unwrap(Session.class);
        System.err.println("Create user " + user);
        session.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(getUserFromId(id));
    }

    @Override
    @Transactional
    public void edit(User user) {
        Session session = entityManager.unwrap(Session.class);
        System.err.println("Edit user " + user);
        session.merge(user);
    }

    @Override
    @Transactional
    public User getUserFromId(Long id) {
        Query query = entityManager.unwrap(Session.class).createQuery("from User where id=:id");
        query.setParameter("id", id);
        System.err.println("Find user by id");
        return (User) query.uniqueResult();
    }

    @Override
    @Transactional
    public User getUserByName(String userName) {
        Query query = (Query) entityManager.createQuery("FROM User where login = :paramName");
        query.setParameter("paramName", userName);
        return (User) query.getSingleResult();
    }

    @Override
    public User findUserByName(String theUserName) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // now retrieve/read from database using username
        org.hibernate.query.Query<User> theQuery = currentSession.createQuery("from User where login = :uName", User.class);
        theQuery.setParameter("uName", theUserName);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
            System.err.println("Find user by name " + theUserName);
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }
}
