package com.github.orlobel.coupon.dto;

//
//import com.jb.taas.beans.ClientType;
//import com.jb.taas.beans.Task;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import com.github.orlobel.coupon.beans.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {


//    @Min(0)
//    private int id;
//    @NotBlank
//    private String email;
//    @NotBlank
//    private String password;
//
//    @NotBlank
//    private String type;
//    @Singular
//    private List<TaskDto> tasks = new ArrayList<>();

    @Min(0)
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @Singular
    private Set<Coupon> coupons = new HashSet<>();

}
