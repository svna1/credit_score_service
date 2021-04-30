package com.demo.creditscore.db.mapper;

import com.demo.creditscore.db.entity.Customer;
import com.demo.creditscore.model.CustomerDto;
import org.mapstruct.*;

import java.util.List;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto dto);

    @Mappings({ @Mapping(target = "id", expression = "java(entity.getId())") })
    void updateEntityFromDto(CustomerDto dto, @MappingTarget Customer entity);

    @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    List<CustomerDto> toDtoList(List<Customer> dtos);

}
