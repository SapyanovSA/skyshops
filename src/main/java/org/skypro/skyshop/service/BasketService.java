package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID uuid) {

        storageService.getProductById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Товар с ID " + uuid + " не найден в магазине."));

        productBasket.add(uuid);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> rawBasket = productBasket.printBasket();

        List<BasketItem> basketItems = rawBasket.entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(id)
                            .orElseThrow(() -> new IllegalStateException("Товар с ID " + id + " пропал!"));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());

        return new UserBasket(basketItems);
    }
}
