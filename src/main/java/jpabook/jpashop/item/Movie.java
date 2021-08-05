package jpabook.jpashop.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("M")
@Getter @Setter
public class Movie extends Item {

    @NotNull
    private String director;

    @NotNull
    private String actor;
}
