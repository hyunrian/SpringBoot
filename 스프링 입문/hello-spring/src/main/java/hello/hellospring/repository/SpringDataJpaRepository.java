package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRepository extends JpaRepository<Member, Long>, MemberRepository {
    // JpaRepository를 상속받은 interface는 자동적으로 구현체를 만들어 내기 때문에 스프링 빈에 따로 등록할 필요가 없음

    @Override
    Optional<Member> findByName(String name);
    // JpaRepository에서 공통 메서드로 제공되지 않는 것만 만들어 줌
    // findByXXXX로 작성하면 select m from Member m where m.XXXX = ?로 쿼리가 자동 작성됨
}
