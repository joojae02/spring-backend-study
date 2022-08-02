package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {



    @Test
    void createOrder() {
        // 생성자 의존성 주입을 사용하지 않으면 코드를 뜯어봐야 함
        // final 을 사용할 수 있음 -> 실수를 막을 수 있다
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "item1", 1000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}