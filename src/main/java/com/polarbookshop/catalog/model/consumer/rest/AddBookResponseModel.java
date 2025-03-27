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
public class AddBookResponseModel {

    @JsonProperty("data")
    private BookResponseModel data;

    @JsonProperty("notifications")
    private List<NotificationResponse> notifications = null;

    public static AddBookResponseModel buildExample() {
        AddBookResponseModel responseObject = new AddBookResponseModel();
        responseObject.setData(BookResponseModel.buildExample());
        responseObject.setNotifications(new ArrayList<>());
        return responseObject;
    }
}
