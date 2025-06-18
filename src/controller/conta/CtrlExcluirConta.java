package controller.conta;

import controller.CtrlAbstrato;
import model.Agencia;
import model.Conta;
import model.dao.DaoConta;

public class CtrlExcluirConta extends CtrlAbstratoConta {
    public CtrlExcluirConta(CtrlAbstrato ctrlPai, Conta conta) {
        super(ctrlPai, conta);
    }

    @Override
    public void efetuar(int numero, String nomeCorrentista, String chavePix, double saldo, double limite, Agencia agencia) {
        DaoConta dao = new DaoConta();
        if (dao.remover(this.conta)) {
            this.janela.notificar("Conta excluída com sucesso!");
        } else {
            this.janela.notificar("Não foi possível excluir a conta.");
        }
        this.encerrar();
    }
}