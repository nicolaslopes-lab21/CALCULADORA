package projeto.calculadora;


public final class App {
    private App() {
    }

    public static void main(String[] args) {
        HistoricoEmMemoria historico = new HistoricoEmMemoria();
        CalculadoraService service = new CalculadoraService(new Calculadora(), historico);

        System.out.println("Calculadora");
        System.out.println("Soma: " + service.somar(10, 5));
        System.out.println("Subtração: " + service.subtrair(10, 5));
        System.out.println("Multiplicação: " + service.multiplicar(10, 5));
        System.out.println("Divisão: " + service.dividir(10, 5));
        System.out.println("Histórico: " + historico.getRegistros());
    }
}