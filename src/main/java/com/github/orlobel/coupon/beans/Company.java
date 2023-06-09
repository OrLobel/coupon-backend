package com.github.orlobel.coupon.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

@Data
@JsonSerialize
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "companies")
@Entity(name = "company")
@EqualsAndHashCode(exclude = {"coupons"})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @Singular
    @ToString.Exclude
    @JsonBackReference
    private Set<Coupon> coupons = new HashSet<>();
}
