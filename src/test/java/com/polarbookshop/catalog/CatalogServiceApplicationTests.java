package com.polarbookshop.catalog;

import com.polarbookshop.catalog.commons.Constants;
import com.polarbookshop.catalog.model.consumer.rest.AddBookResponseModel;
import com.polarbookshop.catalog.model.consumer.rest.BookRequestModel;
import com.polarbookshop.catalog.model.consumer.rest.BookResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
class CatalogServiceApplicationTests {

//	@Autowired
//	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

//	@Test
//	void whenPostRequestThenBookCreated() {
////		var expectedBook = BookRequestModel.of("1231231231", "Title", "Author", new BigDecimal("9.90"));
////
////		/*BookResponseModel.builder()
////				.isbn("12312312")
////				.title("Title")
////				.author("Author")
////				.price(new BigDecimal("9.90"))
////				.build();*/
////
////		Map<String, String> headerParams = new HashMap<>();
////		headerParams.put(Constants.X_B3_TRACEID, "43243");
////		headerParams.put(Constants.X_B3_SPANID, "34343245");
////
////		webTestClient
////				.post()
////				.uri("/api/v1/books")
////				.headers(headers -> {
////					headers.setAll(headerParams);
////				})
////				.bodyValue(expectedBook)
////				.exchange()
////				.expectStatus().is2xxSuccessful()
////				.expectBody(AddBookResponseModel.class).value(actualBook -> {
////					assertThat(actualBook).isNotNull();
////					assertThat(actualBook.getData().getIsbn()).isEqualTo(expectedBook.getIsbn());
////				});
//	}

}
