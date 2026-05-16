package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    @DisplayName("Задание 1 Поиск при пустом хранилище")
    void searchWhenStorageIsEmpty() {
        Mockito.when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("Apple");

        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Задание 2 Поиск при непустом хранилище и отсутствии совпадений")
    void searchWhenNoMatches() {
        Product product = new SimpleProduct(UUID.randomUUID(), "Bread", 80);
        Article article = new Article(UUID.randomUUID(), "Milk Article", "Content");

        Mockito.when(storageService.getAllSearchable()).thenReturn(List.of(product, article));

        Collection<SearchResult> results = searchService.search("Apple");

        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Задание 3 Поиск с хранилищем, содержащим подходящий объект")
    void searchWhenMatchExists() {
        Product product = new SimpleProduct(UUID.randomUUID(), "TestProduct", 100);
        Mockito.when(storageService.getAllSearchable()).thenReturn(List.of(product));

        Collection<SearchResult> results = searchService.search("Test");

        assertThat(results).hasSize(1);
    }
}
