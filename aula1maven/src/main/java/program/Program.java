package program;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Person;

public class Program {

	public static void main(String[] args) {
		
		Person p1 = new Person(null, "Arthur", "arthur@fic.com");
		Person p2 = new Person(null, "Carolina", "carolina@fic.com");
		Person p3 = new Person(null, "Aurora", "aurora@fic.com");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
//		em.persist(p1);
//		em.persist(p2);
//		em.persist(p3);
		
		//Finding and deleting an item in table
		// It is necessary to find an obj, otherwise, hibernate will note delete it
		
		//Creating a obj to be deleted
		
		Person p4 = new Person(null, "Kate Edmonson", "kate@fic.com");
		
		em.persist(p4);
		
		Person toRemove = em.find(Person.class, 4);
				
		em.remove(toRemove);
				
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
