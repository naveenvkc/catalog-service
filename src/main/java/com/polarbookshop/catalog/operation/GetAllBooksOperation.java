package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.entity.BookEntity;
import com.polarbookshop.catalog.mapper.PolarBookshopMapper;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
import com.polarbookshop.catalog.persistence.BookRepository;
import com.polarbookshop.catalog.shared.logging.Logger;
import com.polarbookshop.catalog.shared.rest.IOperation;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GetAllBooksOperation implements IOperation<RestConsumerRequest<Void>, RestConsumerResponse<Iterable<BookResponseModel>>> {

    private static final Logger LOG = Logger.get(GetAllBooksOperation.class);
    public static final String LIFECYCLE = "Lifecycle";

    private final BookRepository bookRepository;

    public GetAllBooksOperation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public RestConsumerResponse<Iterable<BookResponseModel>> handle(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered GetAllBooksOperation.handle Operation"));
        return prepareConsumerResponse(consumerRequest);
    }

    private RestConsumerResponse<Iterable<BookResponseModel>> prepareConsumerResponse(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered GetAllBooksOperation.prepareConsumerResponse Operation"));
        Iterable<BookResponseModel> consumerResponse =
                PolarBookshopMapper.mapEntitiesToResponse(bookRepository.findAll());
        return RestConsumerResponse.of(consumerResponse, HttpStatus.OK);
    }
}
