package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        addData();
    }

    private void addData() {

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id4 = UUID.randomUUID();

        Product product1 = new SimpleProduct(id1, "Apple", 50);
        Product product2 = new SimpleProduct(id2, "Bread", 80);
        Product product3 = new SimpleProduct(id3, "Meat", 400); // Исправлено на Meat
        Product product4 = new SimpleProduct(id4, "Milk", 150);

        productMap.put(id1, product1);
        productMap.put(id2, product2);
        productMap.put(id3, product3);
        productMap.put(id4, product4);

        UUID articleId1 = UUID.randomUUID();
        UUID articleId2 = UUID.randomUUID();
        UUID articleId3 = UUID.randomUUID();
        UUID articleId4 = UUID.randomUUID();

        Article article1 = new Article(articleId1, "Apple", "Product");
        Article article2 = new Article(articleId2, "Bread", "Product");
        Article article3 = new Article(articleId3, "Meat", "Product");
        Article article4 = new Article(articleId4, "Milk", "Product");

        articleMap.put(articleId1, article1);
        articleMap.put(articleId2, article2);
        articleMap.put(articleId3, article3);
        articleMap.put(articleId4, article4);
    }

    // Возвращает коллекцию всех статей
    public Collection<Article> getAllArticles() {
        return articleMap.values();
    }

    // Возвращает коллекцию всех продуктов
    public Collection<Product> getAllProducts() {
        return productMap.values();
    }

    public Collection<Searchable> getAllSearchable() {
        return Stream.concat(
                productMap.values().stream(),
                articleMap.values().stream()
        ).collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }
}
