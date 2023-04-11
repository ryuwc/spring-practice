package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //EntityManagerFactory는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //EntityManager는 쓰레드간에 공유X(사용하고 버리자!)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member member = new Member();
            member.setUsername("junghyun");
            member.setCreatedBy("park");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

            tx.commit();

//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.changeTeam(team); //**연관관계의 주인에 값 설정
//            em.persist(member);
//
//            //**양방향 매핑 시 순수 객체 상태를 고려해서 양쪽에 값을 설정해주자!!
//            //team.getMembers().add(member);
//            //--> 연관관계 편의 메소드를 생성하자!!
//            //--> Member의 changeTeam(setTeam)에 team.getMembers().add(this); 을 추가해주자!
//
//            em.flush();
//            em.clear();
//
//            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시
//            List<Member> members = findTeam.getMembers();
//
//            for(Member m : members){
//                System.out.println("m = " + m.getUsername());
//            }

            //===============================================================

            //영속성 컨텍스트 : 엔티티를 영구 저장하는 환경(EntityManager를 통해)
            //영속성 컨텍스트의 이점 : 1차 캐시, 동일성 보장, 트랜잭션을 지원하는 쓰기 지연, 변경 감지, 지연 로딩
            
            //비영속
            //Member member = new Member();
            //member.setId(101L);
            //member.setName("HelloJPA");

            //영속(DB에 저장되는 것은 아님)
            //em.persist(member); //JPA에 member 저장

            //em.detach(member); //회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
            //em.remove(member); //엔티티 삭제

            //===============================================================

            //Member findMember = em.find(Member.class, 101L);
            //em.remove(findMember); //member 삭제

            //===============================================================

            //Member member1 = new Member(150L, "A");
            //Member member2 = new Member(160L, "B");

            //em.persist(member1);
            //em.persist(member2);
            //여기까지 INSERT SQL을 DB에 보내지 않음

            //커밋하는 시점에 DB에 INSERT SQL을 보냄
            //tx.commit();

            //===============================================================

            //영속 엔티티 조회
            //Member member = em.find(Member.class, 150L);
            //영속 엔티티 데이터 수정
            //member.setName("ZZZ");
            //em.persist(member); --> 필요없는 코드!!
            //tx.commit(); //Entity와 스냅샷을 비교 후 UPDATE쿼리를 생성

            //플러시 : 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
            //1. 영속성 컨텍스트를 비우지 않음
            //2. 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
            //3. 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면 됨
            //Member member = new Member(200L, "member200");
            //em.persist(member);
            //em.flush(); //직접 호출
            //tx.commit(); //플러시 자동 호출

            //Member member = em.find(Member.class, 150L);
            //member.setName("AAA");
            //em.detach(member); //특정 엔티티만 준영속 상태로 전환
            //em.clear(); //영속성 컨텍스트를 완전히 초기화
            //em.close(); //영속성 컨텍스트를 종료
            //tx.commit();

            //===============================================================

            //Member findMember = em.find(Member.class,1L);
            //findMember.setName("HelloJPA"); //member 수정
            //따로 em.persist를 통해 수정된 값을 다시 저장하지 않아도 됨
            //JPA를 통해 Entity를 가져오면 JPA가 값을 관리하게 되는데
            //JPA가 트랜잭션을 커밋하는 시점에 변경된 부분이 있으면 UPDATE쿼리를 생성
            //JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

            //JPQL은 Entity 객체를 대상으로 쿼리 -> 객체 지향 SQL
            //SQL은 데이터베이스 테이블을 대상으로 쿼리
            //List<Member> result = em.createQuery("select m from Member as m", Member.class)
            //        .setFirstResult(1)
            //        .setMaxResults(10)
            //        .getResultList();

            //for(Member member : result){
            //    System.out.println("member.name = " + member.getName());
            //}
        } catch (Exception e){
            tx.rollback();

        } finally {
            em.close(); //사용 후 EntityManager를 꼭 닫아주자!
        }
        emf.close();
    }
}
