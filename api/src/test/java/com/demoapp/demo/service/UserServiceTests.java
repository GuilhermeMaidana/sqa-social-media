package com.demoapp.demo.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

  @Test
  @DisplayName("Aceita senha v├ílida: possui ao menos 8 caracteres, uma mai├║scula, uma min├║scula, um d├¡gito e um caractere especial")
  void deveAceitarSenhaValidaQuandoAtendeTodosOsRequisitos() {
    String password = "Password123!";
    UserService userService = new UserService(null);
    boolean isValid = userService.isPasswordValid(password);
    assertTrue(isValid, "Esperava que 'Password123!' fosse considerada v├ílida (>=8 caracteres, 1 mai├║scula, 1 min├║scula, 1 d├¡gito e 1 caractere especial).");
  }

  @Test
  @DisplayName("Rejeita senha inv├ílida: n├úo cont├®m letra mai├║scula (apenas min├║sculas e d├¡gitos), portanto n├úo atende ├á pol├¡tica")
  void deveRejeitarSenhaQuandoNaoContemMaiuscula() {
    String password = "password123";
    UserService userService = new UserService(null);
    boolean isValid = userService.isPasswordValid(password);
    assertFalse(isValid, "Esperava rejeitar 'password123' porque falta ao menos uma letra mai├║scula e/ou caractere especial conforme a pol├¡tica.");
  }
}
