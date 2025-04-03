package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.commons.Constants;
import com.polarbookshop.catalog.entity.BookEntity;
import com.polarbookshop.catalog.exception.BookNotFoundException;
import com.polarbookshop.catalog.mapper.PolarBookshopMapper;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
import com.polarbookshop.catalog.persistence.BookRepository;
import com.polarbookshop.catalog.shared.logging.Logger;
import com.polarbookshop.catalog.shared.rest.IOperation;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class GetBookOperationByIsbn implements IOperation<RestConsumerRequest<Void>, RestConsumerResponse<BookResponseModel>> {

    private static final Logger LOG = Logger.get(GetBookOperationByIsbn.class);
    public static final String LIFECYCLE = "Lifecycle";

    private final BookRepository bookRepository;

    public GetBookOperationByIsbn(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public RestConsumerResponse<BookResponseModel> handle(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered GetBookOperationByIsbn.handle Operation"));
        return prepareConsumerResponse(consumerRequest);
    }

    private RestConsumerResponse<BookResponseModel> prepareConsumerResponse(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered GetBookOperationByIsbn.prepareConsumerResponse Operation"));
        Map<String, Object> pathParamMap = consumerRequest.getPathParams();
        final String isbn = (String)pathParamMap.get(Constants.ISBN);
        BookEntity bookEntity = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        BookResponseModel consumerResponse  =
                PolarBookshopMapper.mapEntityToResponse(bookEntity);
        return RestConsumerResponse.of(consumerResponse, HttpStatus.OK);
    }
}