package com.springbootreactive.springbootreactive.controller;

import com.springbootreactive.springbootreactive.domain.Item;
import com.springbootreactive.springbootreactive.repository.ItemRepository;
import com.springbootreactive.springbootreactive.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest(controllers = ItemController.class)
@AutoConfigureRestDocs
public class ItemControllerDocumentationTest {
    @Autowired private WebTestClient webTestClient;

    @MockBean
    InventoryService inventoryService;
    @MockBean
    ItemRepository itemRepository;

    @Test
    void findingAllItems(){
        when(itemRepository.findAll()).thenReturn(Flux.just(new Item("item-1","이름","설명",19.99)));

        this.webTestClient.get().uri("/api/items")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("findAll",preprocessResponse(prettyPrint())));
    }
}
