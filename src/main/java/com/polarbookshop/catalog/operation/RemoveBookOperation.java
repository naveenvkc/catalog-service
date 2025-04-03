package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.commons.Constants;
import com.polarbookshop.catalog.entity.BookEntity;
import com.polarbookshop.catalog.exception.BookNotFoundException;
import com.polarbookshop.catalog.model.consumer.rest.BookRequestModel;
import com.polarbookshop.catalog.persistence.BookRepository;
import com.polarbookshop.catalog.shared.logging.Logger;
import com.polarbookshop.catalog.shared.rest.IOperation;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RemoveBookOperation implements IOperation<RestConsumerRequest<Void>, RestConsumerResponse<String>> {

    private static final Logger LOG = Logger.get(RemoveBookOperation.class);
    public static final String LIFECYCLE = "Lifecycle";

    private final BookRepository bookRepository;

    public RemoveBookOperation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public RestConsumerResponse<String> handle(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered RemoveBookOperation.handle Operation"));
        return prepareConsumerResponse(consumerRequest);
    }

    private RestConsumerResponse<String> prepareConsumerResponse(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered RemoveBookOperation.prepareConsumerResponse Operation"));
        Map<String, Object> pathParamMap = consumerRequest.getPathParams();
        final String isbn = (String)pathParamMap.get(Constants.ISBN);

        BookEntity existingBookEntity = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        bookRepository.deleteByIsbn(existingBookEntity.getIsbn());
        return RestConsumerResponse.emptyResponse();
    }
}
