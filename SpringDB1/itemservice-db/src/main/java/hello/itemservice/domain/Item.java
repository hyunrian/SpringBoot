package hello.itemservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity //JPA가 사용하는 객체라는 뜻. JPA 객체로 인식할 수 있도록 함
//@Table(name = "item") //객체명과 같으면 생략 가능
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //테이블의 pk. pk 생성을 IDENTITY 전략으로 함
    private Long id;

    @Column(name = "item_name", length = 10) //컬렴명이 필드명과 같으면 생략 가능. camel -> snake로 자동 변환 가능
    private String itemName;
    private Integer price;
    private Integer quantity;

    // JPA는 public 또는 protected의 기본 생성자가 반드시 필요함
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
