package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // jpa의 필수 요소. 모든 것이 EntityManager를 통해 동작함. 사용하려면 주입받아야 함

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { // pk 기반의 조회는 findByName()처럼 query 작성을 하지 않아도 됨
        Member member = em.find(Member.class, id);// (조회할 타입, 식별자)
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // entity 자체를 조회함
                .getResultList();
    }
}
