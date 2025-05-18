package com.dasad.empresa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Manipulador global de exceções para a aplicação REST.
 * <p>
 * Esta classe intercepta exceções lançadas pelos controllers do pacote
 * {@code com.dasad.empresa} e retorna respostas HTTP apropriadas,
 * padronizando o formato dos erros.
 * </p>
 */
@RestControllerAdvice("com.dasad.empresa")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Manipula exceções de e-mail já cadastrado.
     *
     * @param ex exceção lançada quando o e-mail já existe
     * @return resposta HTTP 409 (CONFLICT) com mensagem de erro
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> emailAlreadyExistsHandler(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado: " + ex.getMessage());
    }

    /**
     * Manipula exceções genéricas de tempo de execução.
     *
     * @param ex exceção de tempo de execução
     * @return resposta HTTP 500 (INTERNAL_SERVER_ERROR) com mensagem de erro
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeExceptionHandler(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Manipula exceções genéricas.
     *
     * @param ex exceção genérica
     * @return resposta HTTP 500 (INTERNAL_SERVER_ERROR) com mensagem de erro
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Manipula exceções de argumentos inválidos, retornando um ProblemDetail.
     *
     * @param ex exceção de argumento ilegal
     * @return resposta HTTP 400 (BAD_REQUEST) com detalhes do problema
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Requisição inválida");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }
}