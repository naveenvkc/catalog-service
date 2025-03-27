package com.polarbookshop.catalog.consumer;

import com.polarbookshop.catalog.model.consumer.rest.AddBookResponseModel;
import com.polarbookshop.catalog.model.consumer.rest.BookRequestModel;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
import com.polarbookshop.catalog.operation.*;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BookRequestDelegate {

    private final AddBookOperation addBookOperation;
    private final GetAllBooksOperation getAllBooksOperation;
    private final GetBookOperationByIsbn getBookOperationByIsbn;
    private final EditBookOperation editBookOperation;
    private final RemoveBookOperation removeBookOperation;

    public BookRequestDelegate(
            @Qualifier("addBookOperation") AddBookOperation addBookOperation,
            @Qualifier("getAllBooksOperation") GetAllBooksOperation getAllBooksOperation,
            @Qualifier("getBookOperationByIsbn") GetBookOperationByIsbn getBookOperationByIsbn,
            @Qualifier("editBookOperation") EditBookOperation editBookOperation,
            @Qualifier("removeBookOperation") RemoveBookOperation removeBookOperation) {
        this.addBookOperation = addBookOperation;
        this.getAllBooksOperation = getAllBooksOperation;
        this.getBookOperationByIsbn = getBookOperationByIsbn;
        this.editBookOperation = editBookOperation;
        this.removeBookOperation = removeBookOperation;
    }

    public ResponseEntity<RestConsumerResponse<BookResponseModel>> addBook(RestConsumerRequest<BookRequestModel> restConsumerRequest) {
        return addBookOperation.handle(restConsumerRequest).entity();
    }

    public ResponseEntity<RestConsumerResponse<Iterable<BookResponseModel>>> getAllBooks(RestConsumerRequest<Void> restConsumerRequest) {
        return getAllBooksOperation.handle(restConsumerRequest).entity();
    }

    public ResponseEntity<RestConsumerResponse<BookResponseModel>> getBookByIsbn(RestConsumerRequest<Void> restConsumerRequest) {
        return getBookOperationByIsbn.handle(restConsumerRequest).entity();
    }

    public ResponseEntity<RestConsumerResponse<BookResponseModel>> editBook(RestConsumerRequest<BookRequestModel> restConsumerRequest) {
        return editBookOperation.handle(restConsumerRequest).entity();
    }

    public ResponseEntity<RestConsumerResponse<String>> removeBook(RestConsumerRequest<Void> restConsumerRequest) {
        return removeBookOperation.handle(restConsumerRequest).entity();
    }
}
