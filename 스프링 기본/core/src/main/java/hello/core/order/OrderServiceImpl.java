package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // 필드 주입 방법. DI 프레임워크가 없으면 아무것도 할 수 없으며 외부에서 변경이 불가능하여 테스트가 어려움
    // 애플리케이션 코드와 관계 없는 테스트 코드나 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용해야 함
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository; // final : 반드시 값이 있어야 하기 때문에 사용함
    private final DiscountPolicy discountPolicy;

    @Autowired // 생성자가 1개만 있을 경우 생략해도 자동으로 적용
    // 대부분의 경우 생성자 주입 방식을 사용함
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
