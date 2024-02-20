El siguiente proyecto simula un mercado donde se maneja una db de clientes, de productos y de ventas.

Dentro del proyecto se exporto la db y tambien se genero un archivo de postman en caso de necesitar asistencia para realizar las verificaciones.

A continuación especifico los endpoints y especifico que necesita cada cual para que el servicio responda correctamente.


GET Cliente
http://localhost:8080/clientes/all --> El siguiente endpoint devuelve la lista de usuarios cargados en la db

http://localhost:8080/clientes/{{id}} --> el id es un numero entero mayor a 0, si el id no esta en la db, devuelme el mensaje correspondiente

GET Producto

http://localhost:8080/productos/all --> El siguiente endpoint devuelve la lista de productos cargados en la db

http://localhost:8080/productos/{{id}} --> el id es un numero entero mayor a 0, si el id no esta en la db, devuelme el mensaje correspondiente



GET Venta

http://localhost:8080/ventas/all --> El siguiente endpoint devuelve la lista de ventas cargadas en la db

http://localhost:8080/ventas/{{id}} --> el id es un numero entero mayor a 0, si el id no esta en la db, devuelme el mensaje correspondiente


POST Cliente

http://localhost:8080/clientes/add --> JSON ejemplo:
{
  "nombre": "Carlos Ortiz",
  "email": "mail.prueba@gmail.com"
}



POST Producto

http://localhost:8080/productos/add --> JSON ejemplo:
{
  "nombre": "Nombre producto",
  "precio": 100.2,
  "stock": 100
}


POST Venta

http://localhost:8080/ventas/add --> JSON ejemplo:
{
   "clientId": 2, 
   "productIds": [1, 2, 3]
}

PUT Cliente

http://localhost:8080/clientes/update/{{id}} --> JSON ejemplo:

{
  "nombre": "Carlos",
  "email": "mailCambiado@example.com"
}



PUT Producto

http://localhost:8080/productos/update/{{id}} --> JSON ejemplo:

{
  "nombre": "Nombre producto",
  "precio": 100.2,
  "stock": 100
}




DELETE Cliente

http://localhost:8080/clientes/delete/{{id}} --> elimina el usuario con el id indicado, si no existe devuelve un mensaje indicandolo

DELETE Producto

http://localhost:8080/productos/delete/{{id}} --> elimina el producto con el id indicado, si no existe devuelve un mensaje indicandolo

 

