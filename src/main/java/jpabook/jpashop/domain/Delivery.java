package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    //공통된 것을 하나로 묶을때
    @Embedded
    private Address address;

    //Enum 을 쓸때
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP
}
