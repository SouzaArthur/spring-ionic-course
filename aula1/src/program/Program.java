package program;

import domain.Person;

public class Program {

	public static void main(String[] args) {
		
		Person p1 = new Person(1, "Arthur", "arthur@fic.com");
		Person p2 = new Person(2, "Carolina", "carolina@fic.com");
		Person p3 = new Person(3, "Aurora", "aurora@fic.com");
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);

	}

}
