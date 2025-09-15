Servicio de Ventas
Este repositorio contiene el código fuente del Servicio de Ventas, un microservicio diseñado para gestionar las transacciones de venta de productos. Como parte de una arquitectura de microservicios más grande, este servicio se comunica con el Servicio de Inventario para verificar la disponibilidad de stock antes de procesar un pedido.

Diagrama de Arquitectura
El siguiente diagrama ilustra la arquitectura de la prueba de concepto, mostrando la interacción entre los servicios de Ventas e Inventario.

El flujo de comunicación es el siguiente:

Un cliente envía una solicitud HTTP (POST /ventas/crear-pedido) al Servicio de Ventas.

El Servicio de Ventas procesa la solicitud y llama al Servicio de Inventario (GET /inventario/{productoId}) para verificar el stock.

El Servicio de Inventario devuelve el stock disponible al Servicio de Ventas.

Si hay stock, el Servicio de Ventas finaliza el pedido y devuelve una respuesta al cliente.

Requerimientos para Ejecutar el Código
Para poder compilar y ejecutar este proyecto, necesitas tener instaladas las siguientes herramientas en tu sistema:

Java Development Kit (JDK) 17 o superior: Asegúrate de tener la versión 17 (o una más reciente) instalada y configurada correctamente en tu PATH.

Apache Maven 3.9.11 o superior: Maven se utiliza para la gestión de dependencias y la compilación del proyecto.

IntelliJ IDEA (opcional): Es el entorno de desarrollo recomendado para este proyecto.

Instrucciones para Ejecutar el Programa
Sigue estos pasos para compilar, empaquetar y ejecutar la aplicación.

1. Compilar y Empaquetar el Proyecto
Abre una terminal y navega a la carpeta raíz del proyecto (/Ventas). Luego, ejecuta el siguiente comando para compilar el código y crear un archivo .jar ejecutable:

mvn clean package
Este comando descargará todas las dependencias, compilará el código y generará el archivo Ventas-0.0.1-SNAPSHOT.jar dentro de la carpeta /target.

2. Ejecutar el Servicio
Primero, debes asegurarte de que el Servicio de Inventario esté en ejecución, ya que el Servicio de Ventas depende de él. Una vez que el Servicio de Inventario esté activo, ejecuta este servicio abriendo una nueva terminal, navegando a la carpeta /target y ejecutando el siguiente comando:

java -jar Ventas-0.0.1-SNAPSHOT.jar
El servicio de Ventas se iniciará y estará listo para recibir peticiones en el puerto 8080.

3. Probar la Comunicación entre Microservicios
Para probar la interacción con el Servicio de Inventario, puedes enviar una petición HTTP al endpoint de Ventas usando una tercera terminal.

curl -X POST http://localhost:8080/ventas/crear-pedido \
-H "Content-Type: application/json" \
-d "{\"productoId\": \"LAPTOP-GAMER-123\"}"
Si todo está configurado correctamente, verás la respuesta Pedido creado exitosamente para el producto: LAPTOP-GAMER-123 en la terminal, lo que confirmará que la comunicación entre ambos servicios fue exitosa.
