package com.servinetcomputers.api.core.page;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PaginationMapper {
    @Mapping(target = "totalPages", expression = "java(page.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(page.getTotalElements())")
    @Mapping(target = "currentPage", expression = "java(page.getNumber())")
    @Mapping(target = "size", expression = "java(page.getSize())")
    PageResponse.Pagination toPagination(Page<?> page);
}
