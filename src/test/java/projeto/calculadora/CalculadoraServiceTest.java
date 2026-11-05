package projeto.calculadora;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculadoraServiceTest {

    @Mock
    private Calculadora calculadora;

    @Mock
    private HistoricoOperacoes historico;

    @InjectMocks
    private CalculadoraService service;

    @Test
    @DisplayName("CT06 - Somar usa a calculadora e registra no histórico")
    void somarDeveRegistrarNoHistorico() {
        when(calculadora.soma(2, 3)).thenReturn(5);

        int resultado = service.somar(2, 3);

        Assertions.assertEquals(5, resultado);
        verify(calculadora).soma(2, 3);
        verify(historico).registrar("2 + 3", 5.0);
    }

    @Test
    @DisplayName("CT07 - Subtrair registra a operação exatamente uma vez")
    void subtrairDeveRegistrarUmaVez() {
        when(calculadora.sub(10, 4)).thenReturn(6);

        service.subtrair(10, 4);

        verify(historico, times(1)).registrar("10 - 4", 6.0);
        verifyNoMoreInteractions(historico);
    }

    @Test
    @DisplayName("CT08 - Multiplicar envia a descrição correta (ArgumentCaptor)")
    void multiplicarDeveEnviarDescricaoCorreta() {
        when(calculadora.mult(4, 7)).thenReturn(28);
        ArgumentCaptor<String> operacao = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> resultado = ArgumentCaptor.forClass(Double.class);

        service.multiplicar(4, 7);

        verify(historico).registrar(operacao.capture(), resultado.capture());
        Assertions.assertEquals("4 * 7", operacao.getValue());
        Assertions.assertEquals(28.0, resultado.getValue(), 0.0001);
    }

    @Test
    @DisplayName("CT09 - Divisão por zero não registra no histórico")
    void dividirPorZeroNaoDeveRegistrar() {
        when(calculadora.divisao(12.0, 0.0))
                .thenThrow(new ArithmeticException("Divisão por zero não permitida"));

        Assertions.assertThrows(
                ArithmeticException.class,
                () -> service.dividir(12.0, 0.0)
        );

        verify(historico, never()).registrar(anyString(), anyDouble());
    }

    @Test
    @DisplayName("CT10 - O cálculo acontece antes do registro (InOrder)")
    void dividirDeveCalcularAntesDeRegistrar() {
        when(calculadora.divisao(12.0, 3.0)).thenReturn(4.0);

        double resultado = service.dividir(12.0, 3.0);

        Assertions.assertEquals(4.0, resultado, 0.0001);
        InOrder ordem = inOrder(calculadora, historico);
        ordem.verify(calculadora).divisao(12.0, 3.0);
        ordem.verify(historico).registrar("12.0 / 3.0", 4.0);
    }

    @Test
    @DisplayName("CT14 - Subtração resultando em zero registra no histórico")
    void subtrairComResultadoZeroDeveRegistrar() {
        when(calculadora.sub(7, 7)).thenReturn(0);

        int resultado = service.subtrair(7, 7);

        Assertions.assertEquals(0, resultado);
        verify(calculadora).sub(7, 7);
        verify(historico).registrar("7 - 7", 0.0);
    }

    @Test
    @DisplayName("CT15 - Chamadas múltiplas registram a quantidade correta de vezes")
    void somarDuasVezesDeveRegistrarDuasVezes() {
        when(calculadora.soma(anyInt(), anyInt())).thenReturn(10);

        service.somar(5, 5);
        service.somar(3, 7);

        verify(historico, times(2)).registrar(anyString(), anyDouble());
    }
}