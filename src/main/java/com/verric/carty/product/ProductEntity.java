package com.verric.carty.product;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="products")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of ="id")
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue
    private long id;

    @Basic
    private String name;

    @Basic
    private long price;

    @Basic
    private String description;

}
