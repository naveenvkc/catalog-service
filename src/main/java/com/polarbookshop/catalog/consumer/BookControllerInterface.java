package com.polarbookshop.catalog.consumer;

import com.polarbookshop.catalog.model.consumer.rest.*;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.*;


@Validated
public interface BookControllerInterface {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

//    @Operation(summary = "View all books per page",
//            security = @SecurityRequirement(name = "oAuth2ClientCredentials", scopes = {"application.automotive-credit-requests.bcvc.create-credit-requests.read"}),
//            responses = {@ApiResponse(content = {@Content(schema = @Schema(implementation = AddBookResponseModel.class))})}
//    )
//    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response.", content = {@Content(schema = @Schema(implementation = AddBookResponseModel.class))}),
//            @ApiResponse(responseCode = "400", description = "Indicates that the server could not understand the request. This is not the same as 422 which indicates a validation error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
//            @ApiResponse(responseCode = "401", description = "Unauthorized.  This will be returned when no authentication information is provided.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
//            @ApiResponse(responseCode = "403", description = "The principal associated with the request does not have sufficient rights to preform this operation on the requested resource.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
//            @ApiResponse(responseCode = "404", description = "The requested resource was not found.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
//            @ApiResponse(responseCode = "422", description = "The request was syntactically correct but was not semantically correct. Usually indicating a validation problem.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))})
//    })
//   @RequestMapping(value = "/books/pages", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.GET)
//    default ResponseEntity<RestConsumerResponse<Page<BookResponseModel>>> getPageableBooks(
//            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
//            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
//            @NotNull @Parameter(description = "Page number is 0-based") @Valid @RequestParam(value = "page", required = false) String page,
//            @NotNull @Parameter(description = "Number of items per page") @Valid @RequestParam(value = "size", required = false) String size
//
//            ){
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }

    @Operation(summary = "View all books",
            security = @SecurityRequirement(name = "oAuth2ClientCredentials", scopes = {"application.automotive-credit-requests.bcvc.create-credit-requests.read"}),
            responses = {@ApiResponse(content = {@Content(schema = @Schema(implementation = GetAllBookResponseModel.class))})}
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response.", content = {@Content(schema = @Schema(implementation = GetAllBookResponseModel.class))}),
            @ApiResponse(responseCode = "400", description = "Indicates that the server could not understand the request. This is not the same as 422 which indicates a validation error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized.  This will be returned when no authentication information is provided.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "403", description = "The principal associated with the request does not have sufficient rights to preform this operation on the requested resource.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The requested resource was not found.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "422", description = "The request was syntactically correct but was not semantically correct. Usually indicating a validation problem.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))})
    })
    @RequestMapping(value = "/books", produces = {"application/json"}, method = RequestMethod.GET)
    default ResponseEntity<RestConsumerResponse<Iterable<BookResponseModel>>> getAllBooks(
            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid
    ){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "View the book by isbn",
            security = @SecurityRequirement(name = "oAuth2ClientCredentials", scopes = {"application.automotive-credit-requests.bcvc.create-credit-requests.read"}),
            responses = {@ApiResponse(content = {@Content(schema = @Schema(implementation = AddBookResponseModel.class))})}
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response.", content = {@Content(schema = @Schema(implementation = AddBookResponseModel.class))}),
            @ApiResponse(responseCode = "400", description = "Indicates that the server could not understand the request. This is not the same as 422 which indicates a validation error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized.  This will be returned when no authentication information is provided.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "403", description = "The principal associated with the request does not have sufficient rights to preform this operation on the requested resource.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The requested resource was not found.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "422", description = "The request was syntactically correct but was not semantically correct. Usually indicating a validation problem.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))})
    })
    @RequestMapping(value = "/books/{isbn}", produces = {"application/json"}, method = RequestMethod.GET)
    default ResponseEntity<RestConsumerResponse<BookResponseModel>> getBookByISBN(
            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
            @NotNull @Parameter(description = "The isbn.", required = true) @Valid @PathVariable(value = "isbn", required = true) String isbn
    ){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Add a Book",
            security = @SecurityRequirement(name = "oAuth2ClientCredentials", scopes = {"application.automotive-credit-requests.bcvc.create-credit-requests.write"}),
            responses = {@ApiResponse(content = {@Content(schema = @Schema(implementation = AddBookResponseModel.class))})}
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response.", content = {@Content(schema = @Schema(implementation = AddBookResponseModel.class))}),
            @ApiResponse(responseCode = "400", description = "Indicates that the server could not understand the request. This is not the same as 422 which indicates a validation error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized.  This will be returned when no authentication information is provided.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "403", description = "The principal associated with the request does not have sufficient rights to preform this operation on the requested resource.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The requested resource was not found.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "422", description = "The request was syntactically correct but was not semantically correct. Usually indicating a validation problem.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))})
    })
    @RequestMapping(value = "/books", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    default ResponseEntity<RestConsumerResponse<BookResponseModel>> addBook(
            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
            @Valid @Parameter(description = "The Book information", required = true) @RequestBody BookRequestModel bookRequestModel
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Edit a Book",
            security = @SecurityRequirement(name = "oAuth2ClientCredentials", scopes = {"application.automotive-credit-requests.bcvc.create-credit-requests.write"}),
            responses = {@ApiResponse(content = {@Content(schema = @Schema(implementation = AddBookResponseModel.class))})}
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response.", content = {@Content(schema = @Schema(implementation = AddBookResponseModel.class))}),
            @ApiResponse(responseCode = "400", description = "Indicates that the server could not understand the request. This is not the same as 422 which indicates a validation error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized.  This will be returned when no authentication information is provided.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "403", description = "The principal associated with the request does not have sufficient rights to preform this operation on the requested resource.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The requested resource was not found.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "422", description = "The request was syntactically correct but was not semantically correct. Usually indicating a validation problem.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))})
    })
    @RequestMapping(value = "/books/{isbn}", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.PUT)
    default ResponseEntity<RestConsumerResponse<BookResponseModel>> editBook(
            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
            @NotNull @Parameter(description = "The isbn.", required = true) @Valid @PathVariable(value = "isbn", required = true) String isbn,
            @Valid @Parameter(description = "The Book information", required = true) @RequestBody BookRequestModel bookRequestModel
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Delete a Book",
            security = @SecurityRequirement(name = "oAuth2ClientCredentials", scopes = {"application.automotive-credit-requests.bcvc.create-credit-requests.write"}),
            responses = {@ApiResponse(content = {@Content(schema = @Schema(implementation = String.class))})}
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response.", content = {@Content(schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Indicates that the server could not understand the request. This is not the same as 422 which indicates a validation error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized.  This will be returned when no authentication information is provided.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "403", description = "The principal associated with the request does not have sufficient rights to preform this operation on the requested resource.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The requested resource was not found.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "422", description = "The request was syntactically correct but was not semantically correct. Usually indicating a validation problem.", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(implementation = EmptyDataResponse.class))})
    })

    @RequestMapping(value = "/books/{isbn}", produces = {"application/json"}, method = RequestMethod.DELETE)
    default ResponseEntity<RestConsumerResponse<String>> removeBook(
            @NotNull @Parameter(description = "Also known as a Correlation ID, is a unique identifier value that is attached to requests and Notifications that allow reference to a particular business transaction or event chain across multiple integration tiers.  x-b3-traceid header is automatically generated/passed through when Spring Cloud Sleuth is included as a dependency. Sleuth is a transitive dependency of Chassis.  If project team needs to create trace id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.", required = true) @Valid @RequestHeader(value = "x-b3-traceid", required = true) String xB3Traceid,
            @NotNull @Parameter(description = "This header represents root span id. The span represents a basic unit of work.  Span is the primary building block of a distributed trace, representing an individual unit of work done in a distributed system. Spans will contain ?References? to other spans, which allows multiple Spans to be assembled into one complete Trace - a visualization of the life of a request as it moves through a distributed system. The trace id (x-b3-traceid) contains a set of span ids, forming a tree-like structure. The trace id will remain the same as one micro service calls the next. Spans have parent-child relationships. This span id creates parent-child relationship with the child spans to generate a trace tree out of all the spans for a request. If project team needs to create span id  their own , it has to be 16 digit hexadecimal number(64 bit, since digit in hexadecimal represents 4 bits) e.g. 71200de3d3f82a83 as supported by Chassis.  ", required = true) @Valid @RequestHeader(value = "x-b3-spanid", required = true) String xB3Spanid,
            @NotNull @Parameter(description = "The isbn.", required = true) @Valid @PathVariable(value = "isbn", required = true) String isbn
            ){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
