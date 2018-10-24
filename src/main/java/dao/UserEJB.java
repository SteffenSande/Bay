package dao;

import entities.Group;
import entities.Users;
import setup.Configuration;
import util.AuthenticationUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
public class UserEJB {

	@PersistenceContext(unitName= "dat250psql")
	private EntityManager em;
	
	public Users createUser(Users users) {
		try {
			users.setPassword(AuthenticationUtils.encodeSHA256(users.getPassword()));
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}

		Group group = new Group();
		group.setEmail(users.getEmail());
		group.setGroupname(Group.USERS_GROUP);

		em.persist(users);
		em.persist(group);
		
		return users;
	}

	public Users findUserById(String id) {
		TypedQuery<Users> query = em.createNamedQuery("findUserById", Users.class);
		query.setParameter("email", id);
		Users users = null;
		try {
			users = query.getSingleResult();
		} catch (Exception e) {
			// getSingleResult throws NoResultException in case there is no users in DB
			// ignore exception and return NULL for users instead
		}
		return users;
	}
}
