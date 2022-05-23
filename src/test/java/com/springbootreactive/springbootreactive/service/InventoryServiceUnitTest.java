package com.springbootreactive.springbootreactive.service;

import com.springbootreactive.springbootreactive.domain.Cart;
import com.springbootreactive.springbootreactive.domain.CartItem;
import com.springbootreactive.springbootreactive.domain.Item;
import com.springbootreactive.springbootreactive.repository.CartRepository;
import com.springbootreactive.springbootreactive.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class InventoryServiceUnitTest {

    // 테스트 대상이 되는 클래스 CUT : Class Under Test
    InventoryService inventoryService;

    @MockBean
    private ItemRepository itemRepository;
    @MockBean
    private CartRepository cartRepository;

    Item sampleItem = new Item("item1","TV tray","Alf TV tray",19.99);
    @BeforeEach
    void setUp(){
        // 테스트 데이터 정의
        CartItem sampleCartItem = new CartItem(sampleItem);
        Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

        // 협력자와의 상호작용 정의
        when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
        when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
        when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

        inventoryService = new InventoryService(cartRepository,itemRepository);
    }

    @Test
    void addItemToEmptyCartShouldProduceOneCartItem() {
        inventoryService.addItemToCart("My Cart","Item1")
                .as(StepVerifier::create) // 구독을 담당
                .expectNextMatches(cart -> {
                    assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
                            .containsExactlyInAnyOrder(1);

                    assertThat(cart.getCartItems()).extracting(CartItem::getItem)
                            .containsExactly(sampleItem);
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void alternativeWayToTest() { // 의도가 명확히 드러나지 않음.
        StepVerifier.create(
                inventoryService.addItemToCart("My Cart","item1"))
                .expectNextMatches(cart -> {
                    assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
                            .containsExactlyInAnyOrder(1);

                    assertThat(cart.getCartItems()).extracting(CartItem::getItem)
                            .containsExactly(sampleItem);
                    return true;
                })
                .verifyComplete();
    }

}
