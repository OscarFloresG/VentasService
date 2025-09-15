package org.estructuraserp.ventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@SpringBootApplication
@RestController
public class VentasServiceApplication {

    private final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(VentasServiceApplication.class, args);
        System.out.println("VentasService iniciado. Escuchando en el puerto 8080.");
    }

    @PostMapping("/ventas/crear-pedido")
    public String crearPedido(@RequestBody Map<String, String> pedido) {
        String productoId = pedido.get("productoId");

        System.out.println(" [✓] Petición de nuevo pedido recibida para el producto: " + productoId);

        // Llamada síncrona al servicio de inventario
        String inventarioUrl = "http://localhost:8081/inventario/" + productoId;
        int stockDisponible = restTemplate.getForObject(inventarioUrl, Integer.class);

        if (stockDisponible > 0) {
            System.out.println(" [✓] Stock verificado. Unidades disponibles: " + stockDisponible);
            return "Pedido creado exitosamente para el producto: " + productoId;
        } else {
            System.out.println(" [!] Stock insuficiente.");
            return "Error: No hay stock suficiente para el producto: " + productoId;
        }
    }
}