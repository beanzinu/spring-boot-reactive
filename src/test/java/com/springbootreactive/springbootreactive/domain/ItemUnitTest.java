package com.springbootreactive.springbootreactive.domain;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemUnitTest {
    @Test
    void itemBasicsShouldWork() {
        Item sampleItem = new Item("item1","TV tray" ,"TV tray desc" , 19.99);


        assertThat(sampleItem.getId()).isEqualTo("item1");
        assertThat(sampleItem.getName()).isEqualTo("TV tray");
        assertThat(sampleItem.getDescription()).isEqualTo("TV tray desc");
        assertThat(sampleItem.getPrice()).isEqualTo(19.99);

        assertThat(sampleItem.toString()).isEqualTo("Item{" +
                "id='" + sampleItem.getId() + '\'' +
                ", name='" + sampleItem.getName() + '\'' +
                ", description='" + sampleItem.getDescription() + '\'' +
                ", price=" + sampleItem.getPrice() +
                '}');

        Item sampleItem2 = new Item("item1","TV tray" ,"TV tray desc" , 19.99);
        assertThat(sampleItem).isEqualTo(sampleItem2);
    }
}
