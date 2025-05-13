package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.commons.Constants;
import com.polarbookshop.catalog.entity.BookEntity;
import com.polarbookshop.catalog.exception.BookNotFoundException;
import com.polarbookshop.catalog.mapper.PolarBookshopMapper;
import com.polarbookshop.catalog.model.consumer.rest.BookRequestModel;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
import com.polarbookshop.catalog.persistence.BookRepository;
import com.polarbookshop.catalog.shared.logging.Logger;
import com.polarbookshop.catalog.shared.rest.IOperation;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EditBookOperation implements IOperation<RestConsumerRequest<BookRequestModel>, RestConsumerResponse<BookResponseModel>> {

    private static final Logger LOG = Logger.get(EditBookOperation.class);
    public static final String LIFECYCLE = "Lifecycle";

    private final BookRepository bookRepository;

    public EditBookOperation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public RestConsumerResponse<BookResponseModel> handle(RestConsumerRequest<BookRequestModel> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered EditBookOperation.handle Operation"));
        return prepareConsumerResponse(consumerRequest);
    }

    private RestConsumerResponse<BookResponseModel> prepareConsumerResponse(
            RestConsumerRequest<BookRequestModel> consumerRequest
    ) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered EditBookOperation.prepareConsumerResponse Operation"));
        Map<String, Object> pathParamMap = consumerRequest.getPathParams();
        final String isbn = (String)pathParamMap.get(Constants.ISBN);

        BookRequestModel bookRequest = consumerRequest.getRequest();

        BookEntity existingBookEntity = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        BookEntity bookEntityToUpdate = BookEntity.builder()
                .id(existingBookEntity.getId())
                .isbn(existingBookEntity.getIsbn())
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .price(bookRequest.getPrice())
                .createdDate(existingBookEntity.getCreatedDate())
                .lastModifiedDate(existingBookEntity.getLastModifiedDate())
                .createdBy(existingBookEntity.getCreatedBy())
                .lastModifiedBy(existingBookEntity.getLastModifiedBy())
                .version(existingBookEntity.getVersion())
                .build();

        BookResponseModel consumerResponse  =
                PolarBookshopMapper.mapEntityToResponse(bookRepository.save(bookEntityToUpdate));
        return RestConsumerResponse.of(consumerResponse, HttpStatus.OK);
    }
}
