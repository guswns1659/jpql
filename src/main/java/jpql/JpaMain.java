package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(teamA);
            member.setAge(10);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamB);
            member2.setAge(10);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setTeam(teamA);
            member3.setAge(10);
            em.persist(member3);

//            em.flush();
//            em.clear();

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            System.out.println("resultCount = " + resultCount);

            em.clear();
            Member findMember = em.find(Member.class, member.getId());

            System.out.println("findMember = " + findMember.getAge());

            System.out.println("=================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
