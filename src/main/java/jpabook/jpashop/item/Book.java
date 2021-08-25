package jpabook.jpashop.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Item {

    @NotNull
    private String author;

    @NotNull
    private String isbn;
}
