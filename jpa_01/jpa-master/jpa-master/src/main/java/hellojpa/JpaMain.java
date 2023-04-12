package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // table에 insert하기
//        try {
//            Member member = new Member();
//
//            member.setId(1L);
//            member.setName("helloA");
//            Member member1 = new Member(2L, "helloB");
//            Member member2 = new Member(3L, "helloC");
//
//            em.persist(member);
//            em.persist(member1);
//            em.persist(member2);
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        //수정
//        try {
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("helloJPA");
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        // jpql
//        try {
//            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(5)
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member = " + member.getName());
//            }
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        try {
            Member member = new Member();
            member.setUsername("A");
            em.persist(member);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
