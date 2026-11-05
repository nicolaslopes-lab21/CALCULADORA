package projeto.calculadora;


public class CalculadoraService {
    private final Calculadora calculadora;
    private final HistoricoOperacoes historico;

    public CalculadoraService(Calculadora calculadora, HistoricoOperacoes historico) {
        this.calculadora = calculadora;
        this.historico = historico;
    }

    public int somar(int a, int b) {
        int resultado = calculadora.soma(a, b);
        historico.registrar(a + " + " + b, resultado);
        return resultado;
    }

    public int subtrair(int a, int b) {
        int resultado = calculadora.sub(a, b);
        historico.registrar(a + " - " + b, resultado);
        return resultado;
    }

    public int multiplicar(int a, int b) {
        int resultado = calculadora.mult(a, b);
        historico.registrar(a + " * " + b, resultado);
        return resultado;
    }

    public double dividir(double a, double b) {
        double resultado = calculadora.divisao(a, b);
        historico.registrar(a + " / " + b, resultado);
        return resultado;
    }
}