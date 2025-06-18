package controller.conta;

import controller.CtrlAbstrato;
import model.Agencia;
import model.Conta;
import model.ModelException;
import model.dao.DaoConta;

public class CtrlAlterarConta extends CtrlAbstratoConta {
    public CtrlAlterarConta(CtrlAbstrato ctrlPai, Conta conta) {
        super(ctrlPai, conta);
    }

    @Override
    public void efetuar(int numero, String nomeCorrentista, String chavePix, double saldo, double limite, Agencia agencia) {
        try {
            this.conta.setNumero(numero);
            this.conta.setNomeCorrentista(nomeCorrentista);
            this.conta.setChavePix(chavePix, false); // Validação de alteração
            this.conta.setSaldo(saldo);
            this.conta.setLimiteCredito(limite);
            this.conta.setAgencia(agencia);

            DaoConta dao = new DaoConta();
            dao.alterar(this.conta);
            this.janela.notificar("Conta alterada com sucesso!");
            this.encerrar();
        } catch (ModelException e) {
            this.janela.notificar(e.getMessage());
        }
    }
}