package com.polarbookshop.catalog.mapper;

import com.polarbookshop.catalog.entity.BookEntity;
import com.polarbookshop.catalog.model.consumer.rest.BookRequestModel;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PolarBookshopMapper {

    private PolarBookshopMapper() {}

    public static BookEntity mapRequestToEntity(BookRequestModel request) {
        return BookEntity.builder()
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .price(request.getPrice())
                .author(request.getAuthor())
                .build();
    }

    public static BookResponseModel mapEntityToResponse(BookEntity entity) {
        return BookResponseModel.builder()
                .id(entity.getId())
                .isbn(entity.getIsbn())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .author(entity.getAuthor())
                .createdBy(entity.getCreatedBy())
                .modifiedBy(entity.getLastModifiedBy())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getLastModifiedDate())
                .build();
    }

    public static Iterable<BookResponseModel> mapEntitiesToResponse(Iterable<BookEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(PolarBookshopMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }
}
