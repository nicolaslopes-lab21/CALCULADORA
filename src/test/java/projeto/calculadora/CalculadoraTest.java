package projeto.calculadora;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    private Calculadora calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new Calculadora();
    }

    @Test
    @DisplayName("CT01 - Soma de dois inteiros positivos")
    void somar() {
        int resultado = calculadora.soma(1, 2);
        Assertions.assertEquals(3, resultado);
    }

    @Test
    @DisplayName("CT02 - Subtração de dois inteiros")
    void subtrair() {
        int resultado = calculadora.sub(2, 1);
        Assertions.assertEquals(1, resultado);
    }

    @Test
    @DisplayName("CT03 - Multiplicação de dois inteiros")
    void multiplicar() {
        int resultado = calculadora.mult(4, 7);
        Assertions.assertEquals(28, resultado);
    }

    @Test
    @DisplayName("CT04 - Divisão com resultado exato")
    void divisao() {
        double resultado = calculadora.divisao(12.0, 3.0);
        Assertions.assertEquals(4.0, resultado, 0.0001);
    }

    @Test
    @DisplayName("CT05 - Divisão por zero lança ArithmeticException")
    void divisaoPorZero() {
        ArithmeticException erro = Assertions.assertThrows(
                ArithmeticException.class,
                () -> calculadora.divisao(12.0, 0.0)
        );
        Assertions.assertEquals(
                "Divisão por zero não permitida",
                erro.getMessage()
        );
    }

    @Test
    @DisplayName("CT11 - Soma de dois números negativos")
    void somarNegativos() {
        int resultado = calculadora.soma(-5, -3);
        Assertions.assertEquals(-8, resultado);
    }

    @Test
    @DisplayName("CT12 - Multiplicação por zero")
    void multiplicarPorZero() {
        int resultado = calculadora.mult(5, 0);
        Assertions.assertEquals(0, resultado);
    }

    @Test
    @DisplayName("CT13 - Divisão com resultado decimal")
    void divisaoDecimal() {
        double resultado = calculadora.divisao(10.0, 4.0);
        Assertions.assertEquals(2.5, resultado, 0.0001);
    }
}