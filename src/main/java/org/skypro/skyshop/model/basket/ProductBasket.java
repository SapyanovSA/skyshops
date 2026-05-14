package org.skypro.skyshop.model.basket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {

    private final Map<UUID, Integer> mapBasket = new HashMap<>();

    public void add(UUID id) {
        mapBasket.computeIfAbsent(id, key -> 0);
        mapBasket.put(id, mapBasket.get(id) + 1);
    }

    public Map<UUID, Integer> printBasket() {
        return Collections.unmodifiableMap(mapBasket);
    }

}
