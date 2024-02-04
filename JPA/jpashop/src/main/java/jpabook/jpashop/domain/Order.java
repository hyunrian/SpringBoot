package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") //FK
    private Member member;

    @OneToMany(mappedBy = "order") //OrderItem의 order 필드에 의해 변경됨
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문 시간

    @Enumerated(EnumType.STRING) //Enum 타입 지정: Ordinal로 하면 숫자로 지정되기 때문에 순서가 바뀌면 에러남
    private OrderStatus status; //주문 상태 [ORDER, CANCEL]

}
