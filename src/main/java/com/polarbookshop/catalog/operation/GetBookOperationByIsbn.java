package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
import com.polarbookshop.catalog.shared.logging.Logger;
import com.polarbookshop.catalog.shared.rest.IOperation;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class GetBookOperationByIsbn implements IOperation<RestConsumerRequest<Void>, RestConsumerResponse<BookResponseModel>> {

    private static final Logger LOG = Logger.get(GetBookOperationByIsbn.class);
    public static final String LIFECYCLE = "Lifecycle";

    @Override
    public RestConsumerResponse<BookResponseModel> handle(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered GetBookOperationByIsbn.handle Operation"));
        return prepareConsumerResponse(consumerRequest);
    }

    private RestConsumerResponse<BookResponseModel> prepareConsumerResponse(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered GetBookOperationByIsbn.prepareConsumerResponse Operation"));
        BookResponseModel consumerResponse = BookResponseModel.buildExample();
        return RestConsumerResponse.of(consumerResponse, HttpStatus.OK);
    }
}