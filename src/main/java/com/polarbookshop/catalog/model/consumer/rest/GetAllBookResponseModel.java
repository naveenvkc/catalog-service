package com.polarbookshop.catalog.model.consumer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBookResponseModel {

    @JsonProperty("data")
    private List<BookResponseModel> data;

    @JsonProperty("notifications")
    private List<NotificationResponse> notifications = null;

    public static GetAllBookResponseModel buildExample() {
        GetAllBookResponseModel responseObject = new GetAllBookResponseModel();
        responseObject.setData(List.of(BookResponseModel.buildExample()));
        responseObject.setNotifications(new ArrayList<>());
        return responseObject;
    }
}
