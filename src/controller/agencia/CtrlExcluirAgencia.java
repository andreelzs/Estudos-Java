package controller.agencia;

import controller.CtrlAbstrato;
import model.Agencia;
import model.dao.DaoAgencia;

public class CtrlExcluirAgencia extends CtrlAbstratoAgencia {
    public CtrlExcluirAgencia(CtrlAbstrato ctrlPai, Agencia agencia) {
        super(ctrlPai, agencia);
    }

    @Override
    public void efetuar(int numero, String bairro, String cidade) {
        DaoAgencia dao = new DaoAgencia();
        if (dao.remover(this.agencia)) {
            this.janela.notificar("Agência excluída com sucesso!");
        } else {
            this.janela.notificar("Não foi possível excluir a agência.");
        }
        this.encerrar();
    }
}