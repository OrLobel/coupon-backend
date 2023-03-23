package com.github.orlobel.coupon.mapper;

//import com.jb.taas.beans.ClientType;
//import com.jb.taas.beans.User;
//import com.jb.taas.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;
import com.github.orlobel.coupon.beans.Company;
import com.github.orlobel.coupon.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by kobis on 19 May, 2022
 */
@Component
@RequiredArgsConstructor
public class CompanyMapper implements Mapper<Company, CompanyDto> {

//    private final TaskMapper taskMapper;
//    @Override
//    public User toDao(UserDto userDto) {
//        return User.builder()
//            .id(userDto.getId())
//            .type(ClientType.valueOf(userDto.getType()))
//            .email(userDto.getEmail())
//            .password(userDto.getPassword())
//            .tasks(taskMapper.toDaoList(userDto.getTasks()))
//            .build();
//    }
//
//    @Override
//    public UserDto toDto(User user) {
//        return UserDto.builder()
//            .id(user.getId())
//            .type(user.getType().name())
//            .email(user.getEmail())
//            .password(user.getPassword())
//            .tasks(taskMapper.toDtoList(user.getTasks()))
//            .build();
//    }
//
//    @Override
//    public List<User> toDaoList(List<UserDto> userDtos) {
//        return userDtos.stream().map(this::toDao).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDto> toDtoList(List<User> userDaos) {
//        return userDaos.stream().map(this::toDto).collect(Collectors.toList());
//    }

    @Override
    public Company toDao(CompanyDto companyDto) {
        return Company.builder()
            .id(companyDto.getId())
            .name(companyDto.getName())
            .email(companyDto.getEmail())
            .password(companyDto.getPassword())
            .coupons(companyDto.getCoupons())
            .build();
    }

    @Override
    public CompanyDto toDto(Company company) {
        return CompanyDto.builder()
            .id(company.getId())
            .name(company.getName())
            .email(company.getEmail())
            .password(company.getPassword())
            .coupons(company.getCoupons())
            .build();
    }

    @Override
    public List<Company> toDaoList(List<CompanyDto> companyDtos) {
        return companyDtos.stream().map(this::toDao).collect(Collectors.toList());
    }

    @Override
    public List<CompanyDto> toDtoList(List<Company> companies) {
        return companies.stream().map(this::toDto).collect(Collectors.toList());
    }
}
