package jpabook.jpashop.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("A")
@Getter @Setter
public class Album extends Item {

    @NotNull
    private String artist;

    @NotNull
    private String etc;
}
