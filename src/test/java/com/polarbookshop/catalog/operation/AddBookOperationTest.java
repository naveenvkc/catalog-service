package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.commons.Constants;
import com.polarbookshop.catalog.model.consumer.rest.BookRequestModel;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class AddBookOperationTest {

    @InjectMocks
    private AddBookOperation operation;



    @Test
    public void testAddBookOperation(){
//        var bookRequestModel = BookRequestModel.of("1231231231", "Title", "Author", new BigDecimal("9.90"));
//
//        Map<String, Object> headerParams = new HashMap<>();
//        headerParams.put(Constants.X_B3_TRACEID, "4545");
//        headerParams.put(Constants.X_B3_SPANID, "454545");
//
//        RestConsumerRequest<BookRequestModel> request = RestConsumerRequest.<BookRequestModel>builder()
//                .headerParams(headerParams)
//                .request(bookRequestModel)
//                .build();
//
//        RestConsumerResponse<BookResponseModel> consumerResponse = operation.handle(request);
//        assertNotNull(consumerResponse);
    }
}
