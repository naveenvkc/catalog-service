package com.polarbookshop.catalog.operation;

import com.polarbookshop.catalog.commons.Constants;
import com.polarbookshop.catalog.entity.PolarBookshopPageProjection;
import com.polarbookshop.catalog.persistence.BookRepository;
import com.polarbookshop.catalog.shared.rest.IOperation;
import com.polarbookshop.catalog.shared.rest.RestConsumerRequest;
import com.polarbookshop.catalog.shared.rest.RestConsumerResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.polarbookshop.catalog.shared.logging.Logger;

import java.util.Map;
import java.util.Optional;

@Component
public class GetPagableBooksOperation implements IOperation<RestConsumerRequest<Void>, RestConsumerResponse<Page<PolarBookshopPageProjection>>> {

    private static final Logger LOG = Logger.get(GetPagableBooksOperation.class);
    public static final String LIFECYCLE = "Lifecycle";

    private final BookRepository bookRepository;

    public GetPagableBooksOperation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public RestConsumerResponse<Page<PolarBookshopPageProjection>> handle(RestConsumerRequest<Void> consumerRequest) {
        LOG.info(e -> e.tag(LIFECYCLE).message("Entered GetPagableBooksOperation.handle Operation"));
        return prepareConsumerResponse(consumerRequest);
    }

    private RestConsumerResponse<Page<PolarBookshopPageProjection>> prepareConsumerResponse(RestConsumerRequest<Void> consumerRequest) {
        final Map<String, Object> queryParams = consumerRequest.getQueryParams();
        final String pageParam = Optional.ofNullable(queryParams.get(Constants.PAGE))
                .map(String::valueOf)
                .filter(StringUtils::isNotEmpty)
                .orElse(Constants.PAGE_DEFAULT);
        final String sizeParam = Optional.ofNullable(queryParams.get(Constants.SIZE))
                .map(String::valueOf)
                .filter(StringUtils::isNotEmpty)
                .orElse(Constants.SIZE_DEFAULT);

//        final boolean isAdvancedSearch = Optional.ofNullable(queryParams.get(Constants.FILTER_ADVANCE_SEARCH))
//                .map(String::valueOf)
//                .filter(StringUtils::isNotEmpty)
//                .map(Boolean::parseBoolean)
//                .orElse(false);

        final PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageParam), Integer.parseInt(sizeParam));
        final Page<PolarBookshopPageProjection> catalogPages =
                bookRepository.findCatalogAppPageList(pageRequest);
        return RestConsumerResponse.of(catalogPages, HttpStatus.OK);
    }
}
