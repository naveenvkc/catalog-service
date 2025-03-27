package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.shared.logging.Logger;
import com.polarbookshop.catalog.shared.rest.IOperation;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.springframework.stereotype.Component;

@Component
public class RemoveBookOperation implements IOperation<RestConsumerRequest<Void>, RestConsumerResponse<String>> {

    private static final Logger LOG = Logger.get(RemoveBookOperation.class);
    public static final String LIFECYCLE = "Lifecycle";

    @Override
    public RestConsumerResponse<String> handle(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered RemoveBookOperation.handle Operation"));
        return prepareConsumerResponse(consumerRequest);
    }

    private RestConsumerResponse<String> prepareConsumerResponse(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered RemoveBookOperation.prepareConsumerResponse Operation"));
        return RestConsumerResponse.emptyResponse();
    }
}
