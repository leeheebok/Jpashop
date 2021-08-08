package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
public class Address {

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
