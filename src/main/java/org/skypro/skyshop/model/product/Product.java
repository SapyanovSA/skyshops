package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable, Comparable<Product> {

    private final UUID id;
    private String name;

    public Product(UUID id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Введите корректное название продукта");
        }
        this.id = id;
        this.name = name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return name;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product p)) return false;
        return Objects.equals(name, p.name);
    }

    @Override
    public int compareTo(Product pr) {
        return name.compareTo(pr.name);
    }
}
