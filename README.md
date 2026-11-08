```CSS (1.1. Список названий товаров)
.product-card h4
```
```CSS (1.2. Список цен товаров)
.products-grid >.product-card > [style="color:var(--primary); font-weight:bold"]
```
```CSS (1.3. Название товара с ценой 25)
.products-grid > [data-price="25"] >h4
```
```Xpath (1.4. Цена товара с названием «Стакан»)
//*[contains(@data-price,"159")]/h4
```
```Xpath (1.5. Список карточек товаров в корзине)
//*[@class="cart-item"]
```
```Xpath (1.6. Кнопка корзины)
//*[@class="btn btn-cart"]
```
