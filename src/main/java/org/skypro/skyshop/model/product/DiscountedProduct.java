package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int price;
    private int discount;

    public DiscountedProduct(UUID id, String name, int price, int discount) {
        super(id, name);

        if (price <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть выше 0");
        }

        this.price = price;

        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        } else {
            throw new IllegalArgumentException("Введите корректную скидку от 0 до 100 включительно");
        }

    }

    @Override
    public int getPrice() {
        return  price - (price * discount / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";
    }
}
