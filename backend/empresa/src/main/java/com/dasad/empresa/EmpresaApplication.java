package com.dasad.empresa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.dasad.empresa"}
)
public class EmpresaApplication {

    /**
     * Construtor padrão sem parâmetros.
     * Utilizado pela injeção de dependências do Spring.
     */
    public EmpresaApplication() {
        // Construtor padrão necessário para injeção de dependências
    }

    public static void main(String[] args) {
        SpringApplication.run(EmpresaApplication.class, args);
    }
}
