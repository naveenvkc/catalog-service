package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.entity.BookEntity;
import com.polarbookshop.catalog.exception.BookAlreadyExistsException;
import com.polarbookshop.catalog.mapper.PolarBookshopMapper;
import com.polarbookshop.catalog.model.consumer.rest.AddBookResponseModel;
import com.polarbookshop.catalog.model.consumer.rest.BookRequestModel;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
import com.polarbookshop.catalog.persistence.BookRepository;
import com.polarbookshop.catalog.shared.logging.Logger;
import com.polarbookshop.catalog.shared.rest.IOperation;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AddBookOperation implements IOperation<RestConsumerRequest<BookRequestModel>, RestConsumerResponse<BookResponseModel>> {

    private static final Logger LOG = Logger.get(AddBookOperation.class);
    public static final String LIFECYCLE = "Lifecycle";

    private final BookRepository bookRepository;

    public AddBookOperation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public RestConsumerResponse<BookResponseModel> handle(RestConsumerRequest<BookRequestModel> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered AddBookOperation.handle Operation"));
        return prepareConsumerResponse(consumerRequest);
    }

    private RestConsumerResponse<BookResponseModel> prepareConsumerResponse(
            RestConsumerRequest<BookRequestModel> consumerRequest
    ) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered AddBookOperation.prepareConsumerResponse Operation"));
        BookRequestModel bookRequestModel = consumerRequest.getRequest();
        if (bookRepository.existsByIsbn(bookRequestModel.getIsbn())) {
            throw new BookAlreadyExistsException(bookRequestModel.getIsbn());
        }
        BookEntity bookEntity =
                PolarBookshopMapper.mapRequestToEntity(bookRequestModel);
        BookResponseModel consumerResponse  =
                PolarBookshopMapper.mapEntityToResponse(bookRepository.save(bookEntity));
        return RestConsumerResponse.of(consumerResponse, HttpStatus.OK);
    }
}
