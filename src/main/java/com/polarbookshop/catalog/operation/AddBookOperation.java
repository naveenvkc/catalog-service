package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.model.consumer.rest.AddBookResponseModel;
import com.polarbookshop.catalog.model.consumer.rest.BookRequestModel;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
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

    @Override
    public RestConsumerResponse<BookResponseModel> handle(RestConsumerRequest<BookRequestModel> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered AddBookOperation.handle Operation"));
        return prepareConsumerResponse(consumerRequest);
    }

    private RestConsumerResponse<BookResponseModel> prepareConsumerResponse(
            RestConsumerRequest<BookRequestModel> consumerRequest
    ) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered AddBookOperation.prepareConsumerResponse Operation"));
        BookResponseModel consumerResponse = BookResponseModel.buildExample();
        return RestConsumerResponse.of(consumerResponse, HttpStatus.OK);
    }
}
