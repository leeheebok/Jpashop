package jpabook.jpashop.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @NotBlank
    private String name;

    @Range(min = 1000, max = 100000)
    @NotNull
    private int price;

    @NotNull
    @Max(value = 9999)
    private int stockQuantity;

    //== 비지니스 로직 ==//
    /*
        stock 증가
    * */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }
    /*
        stock 감소
    * */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if (restStock <0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
