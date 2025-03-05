# ProjectShoes - Dokumentacja API

## Opis projektu
**ProjectShoes** to aplikacja backendowa do zarządzania produktami w sklepie obuwniczym. Umożliwia tworzenie, pobieranie oraz usuwanie produktów. System obsługuje cacheowanie danych przy użyciu mechanizmu **Spring Cache**, co zwiększa wydajność API.

---

## Technologie
- **Spring Boot** – główny framework aplikacji
- **Spring Cache** – wykorzystywane do przechowywania produktów w pamięci podręcznej
- **Jakarta Persistence API (JPA)** – obsługa bazy danych
- **Lombok** – redukcja boilerplate kodu
- **Spring Validation** – walidacja danych wejściowych

---

---

## Endpoints API

### 1. Pobranie wszystkich produktów
**GET** `/api/v1/products/all`

#### Odpowiedzi:
- **200 OK** – Zwraca listę produktów w JSON
- **404 NOT FOUND** – Jeśli brak produktów w bazie

Przykładowa odpowiedź:
```json
{
  "status": "OK",
  "statusCode": 200,
  "data": {
    "products": [
      {
        "productCode": "SHOES001",
        "category": "Sport",
        "brand": "Nike",
        "price": 199.99
      }
    ]
  }
}
```

---

### 2. Pobranie produktu po kodzie
**GET** `/api/v1/products/{productCode}`

#### Odpowiedzi:
- **200 OK** – Zwraca szczegóły produktu
- **404 NOT FOUND** – Jeśli produkt nie istnieje

Przykładowa odpowiedź:
```json
{
  "status": "OK",
  "statusCode": 200,
  "data": {
    "product": {
      "productCode": "SHOES001",
      "category": "Sport",
      "brand": "Nike",
      "price": 199.99
    }
  }
}
```

---

### 3. Tworzenie nowego produktu
**POST** `/api/v1/products/create`

#### Nagłówek:
- **Content-Type:** `application/json`

#### Przykładowe zapytanie:
```json
{
  "productCode": "SHOES002",
  "category": "Casual",
  "brand": "Adidas",
  "price": 149.99,
  "size": 42,
  "isAvailable": true
}
```

#### Odpowiedzi:
- **201 CREATED** – Produkt został utworzony
- **400 BAD REQUEST** – Błąd walidacji lub produkt już istnieje

Przykładowa odpowiedź:
```json
{
  "status": "CREATED",
  "statusCode": 201,
  "developerMessage": "Product was added to repository!"
}
```

---

### 4. Usunięcie produktu
**DELETE** `/api/v1/products/delete/{productCode}`

#### Odpowiedzi:
- **200 OK** – Produkt został usunięty
- **404 NOT FOUND** – Jeśli produkt nie istnieje

Przykładowa odpowiedź:
```json
{
  "status": "OK",
  "statusCode": 200,
  "developerMessage": "Product was deleted from repository!"
}
```

---

## Cacheowanie danych

Aplikacja wykorzystuje **Spring Cache** do przechowywania produktów w pamięci podręcznej, co przyspiesza operacje odczytu:
- **`@Cacheable("productByProductCode")`** – zapisuje produkt do cache przy pierwszym pobraniu
- **`@CachePut("productByProductCode")`** – aktualizuje cache przy dodaniu produktu
- **`@CacheEvict("productByProductCode")`** – usuwa produkt z cache przy usunięciu

---


