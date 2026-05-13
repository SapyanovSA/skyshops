package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name);
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Цена продукта не может быть отрицательной и равной 0");
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
    @Override
    public String toString() {
        return getName() + ": " + price;
    }

}
