package com.springbootreactive.springbootreactive.domain;

import org.springframework.data.annotation.Id;

public class Dish {
    private @Id String id;
    private String description;
    private boolean delivered = false;

    public static Dish deliver(Dish dish){
        Dish deliveredDish = new Dish(dish.description);
        deliveredDish.delivered = true;
        return deliveredDish;
    }

    public Dish(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDelivered() {
        return delivered;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "description='" + description + '\'' +
                ", delivered=" + delivered +
                '}';
    }
}
