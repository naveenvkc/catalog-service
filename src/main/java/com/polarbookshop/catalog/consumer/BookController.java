package com.polarbookshop.catalog.consumer;

import com.polarbookshop.catalog.entity.PolarBookshopPageProjection;
import com.polarbookshop.catalog.model.consumer.rest.AddBookResponseModel;
import com.polarbookshop.catalog.commons.Constants;
import com.polarbookshop.catalog.model.consumer.rest.BookRequestModel;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Book", description = "Book management APIs")
public class BookController implements BookControllerInterface{

    private final BookRequestDelegate delegate;

    public BookController(BookRequestDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public ResponseEntity<RestConsumerResponse<Page<PolarBookshopPageProjection>>> getPageableBooks(
            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
            @NotNull @Parameter(description = "Page number is 0-based") @Valid @RequestParam(value = "page", required = false) String page,
            @NotNull @Parameter(description = "Number of items per page") @Valid @RequestParam(value = "size", required = false) String size
    ) {
        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put(Constants.X_B3_TRACEID, xB3Traceid);
        headerParams.put(Constants.X_B3_SPANID, xB3Spanid);

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constants.PAGE, page);
        queryParams.put(Constants.SIZE, size);

        RestConsumerRequest<Void> restRequest = RestConsumerRequest.<Void>builder()
                .headerParams(headerParams)
                .queryParams(queryParams)
                .build();

        return this.delegate.getPageableBookList(restRequest);
    }

    @Override
    public ResponseEntity<RestConsumerResponse<Iterable<BookResponseModel>>> getAllBooks(
            @NotNull @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid
    ) {
        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put(Constants.X_B3_TRACEID, xB3Traceid);
        headerParams.put(Constants.X_B3_SPANID, xB3Spanid);

        RestConsumerRequest<Void> restRequest = RestConsumerRequest.<Void>builder()
                .headerParams(headerParams)
                .build();

        return this.delegate.getAllBooks(restRequest);
    }

    @Override
    public ResponseEntity<RestConsumerResponse<BookResponseModel>> getBookByISBN(
            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
            @NotNull @Parameter(description = "The isbn.", required = true) @Valid @PathVariable(value = "isbn", required = true) String isbn
    ) {
        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put(Constants.X_B3_TRACEID, xB3Traceid);
        headerParams.put(Constants.X_B3_SPANID, xB3Spanid);

        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put(Constants.ISBN, isbn);

        RestConsumerRequest<Void> restRequest = RestConsumerRequest.<Void>builder()
                .headerParams(headerParams)
                .pathParams(pathParams)
                .build();
        return this.delegate.getBookByIsbn(restRequest);
    }

    @Override
    public ResponseEntity<RestConsumerResponse<BookResponseModel>> addBook(
            @NotNull @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
            @Valid @RequestBody BookRequestModel bookRequestModel
    ) {

        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put(Constants.X_B3_TRACEID, xB3Traceid);
        headerParams.put(Constants.X_B3_SPANID, xB3Spanid);

        RestConsumerRequest<BookRequestModel> restRequest = RestConsumerRequest.<BookRequestModel>builder()
                .headerParams(headerParams)
                .request(bookRequestModel)
                .build();

        return this.delegate.addBook(restRequest);
    }

    @Override
    public ResponseEntity<RestConsumerResponse<BookResponseModel>> editBook(
            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
            @NotNull @Parameter(description = "The isbn.", required = true) @Valid @PathVariable(value = "isbn", required = true) String isbn,
            @Valid @Parameter(description = "The Book information", required = true) @RequestBody BookRequestModel bookRequestModel
    ){
        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put(Constants.X_B3_TRACEID, xB3Traceid);
        headerParams.put(Constants.X_B3_SPANID, xB3Spanid);

        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put(Constants.ISBN, isbn);

        RestConsumerRequest<BookRequestModel> restRequest = RestConsumerRequest.<BookRequestModel>builder()
                .headerParams(headerParams)
                .pathParams(pathParams)
                .request(bookRequestModel)
                .build();
        return this.delegate.editBook(restRequest);
    }

    @Override
    public ResponseEntity<RestConsumerResponse<String>> removeBook(
            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
            @NotNull @Parameter(description = "The isbn.", required = true) @Valid @PathVariable(value = "isbn", required = true) String isbn
    ){
        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put(Constants.X_B3_TRACEID, xB3Traceid);
        headerParams.put(Constants.X_B3_SPANID, xB3Spanid);

        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put(Constants.ISBN, isbn);

        RestConsumerRequest<Void> restRequest = RestConsumerRequest.<Void>builder()
                .headerParams(headerParams)
                .pathParams(pathParams)
                .build();
        return this.delegate.removeBook(restRequest);
    }
}
