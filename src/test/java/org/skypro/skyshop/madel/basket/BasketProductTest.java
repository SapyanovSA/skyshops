package org.skypro.skyshop.madel.basket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BasketProductTest {

    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    @DisplayName("Задание 1 В тесте с добавлением в корзину несуществующего товара StorageService выбрасывает исключение")
    void shouldThrowExceptionWhenProductNotFound() {
        UUID invalidId = UUID.randomUUID();

        Mockito.when(storageService.getProductById(invalidId))
                .thenThrow(new NoSuchProductException("Продукт не найден"));

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(invalidId));
    }

    @Test
    @DisplayName("Задание 2 В тесте с добавлением в корзину существующего товара")
    void shouldAddExistingProduct() {
        UUID validId = UUID.randomUUID();
        Product product = new SimpleProduct(validId, "TestProduct", 100);

        Mockito.when(storageService.getProductById(validId)).thenReturn(product);

        basketService.addProduct(validId);

        Mockito.verify(productBasket, Mockito.times(1)).add(validId);
    }

    @Test
    @DisplayName("Задание 3 В тесте с получением корзины мок ProductBasket возвращает пустую мапу")
    void shouldReturnEmptyBasket() {
        Mockito.when(productBasket.printBasket()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertThat(userBasket.getItems()).isEmpty();

    }

    @Test
    @DisplayName("Задание 4В тесте с получением корзины мок ProductBasket возвращает мапу id -> количество")
    void shouldReturnFilledBasket() {
        UUID id = UUID.randomUUID();
        int quantity = 3;
        int price = 150;
        Product product = new SimpleProduct(id, "TestProduct", price);

        Mockito.when(productBasket.printBasket()).thenReturn(Map.of(id, quantity));
        Mockito.when(storageService.getProductById(id)).thenReturn(product);

        UserBasket userBasket = basketService.getUserBasket();

        assertThat(userBasket.getItems()).hasSize(1);

    }
}