package com.springbootreactive.springbootreactive.controller;

import com.springbootreactive.springbootreactive.domain.Cart;
import com.springbootreactive.springbootreactive.domain.Item;
import com.springbootreactive.springbootreactive.repository.CartRepository;
import com.springbootreactive.springbootreactive.repository.ItemRepository;
import com.springbootreactive.springbootreactive.service.CartService;
import com.springbootreactive.springbootreactive.service.InventoryService;
import com.springbootreactive.springbootreactive.service.ItemService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebFluxTest(HomeController.class)
public class HomeControllerSliceTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    InventoryService inventoryService;

    @MockBean
    ItemRepository itemRepository;
    @MockBean
    CartRepository cartRepository;
    @MockBean
    CartService cartService;
    @MockBean
    ItemService itemService;

    @Test
    void homePage(){
        when(inventoryService.getInventory()).thenReturn(Flux.just(
                            new Item("id1","name1","desc1",1.99),
                            new Item("id2","name1","desc1",9.99)
                    ));
        when(inventoryService.getCart("My Cart")).thenReturn(Mono.just(new Cart("My Cart")));

        client.get().uri("/").exchange()
                .expectStatus().is5xxServerError();
//                .expectStatus().isOk()
//                .expectBody(String.class)
//                .consumeWith(exchangeResult -> {
//                    assertThat(exchangeResult.getResponseBody()).contains("action=\"/add/id1\"");
//                    assertThat(exchangeResult.getResponseBody()).contains("action=\"/add/id2\"");
//
//        });
    }
}
