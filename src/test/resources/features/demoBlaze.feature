#language: es
@DEMOBLAZE
Característica: demoblaze
  Yo como usuario no registrado
  Quiero comprar productos de la plataforma de demoblaze
  Para poder usarlos en mi vida diaria

  Antecedentes:
    Dado que estoy en la plataforma de demoblaze

  @HAPPY_PATH_CARRITO
  Esquema del escenario: agregar pedido a carrito de compras
    Cuando doy clic en categoria "Laptops"
    Y selecciono el producto "<producto1>"
    Cuando agrego el producto al carrito de compras
    Y regreso a la pagina principal
    Cuando doy clic en categoria "Phones"
    Y selecciono el producto "<producto2>"
    Cuando agrego el producto al carrito de compras
    Entonces valido que los productos se hayan agregado "<producto1>" "<producto2>"
    Y completo el formulario de compra
      | name    | country | city | creditCard       | mount | year |
      | Jose EZ | Brasil  | SP   | 4557445566778899 | Enero | 2024 |
    Entonces se deberia validar el mensaje exitoso "Thank you for your purchase!"

    Ejemplos:
      | producto1   | producto2         |
      | MacBook air | Samsung galaxy s6 |
