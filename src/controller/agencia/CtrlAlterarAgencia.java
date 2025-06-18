package controller.agencia;

import controller.CtrlAbstrato;
import model.Agencia;
import model.ModelException;
import model.dao.DaoAgencia;

public class CtrlAlterarAgencia extends CtrlAbstratoAgencia {
    public CtrlAlterarAgencia(CtrlAbstrato ctrlPai, Agencia agencia) {
        super(ctrlPai, agencia);
    }

    @Override
    public void efetuar(int numero, String bairro, String cidade) {
        try {
            this.agencia.setNumero(numero);
            this.agencia.setBairro(bairro);
            this.agencia.setCidade(cidade);
            DaoAgencia dao = new DaoAgencia();
            dao.alterar(this.agencia);
            this.janela.notificar("Agência alterada com sucesso!");
            this.encerrar();
        } catch (ModelException e) {
            this.janela.notificar(e.getMessage());
        }
    }
}