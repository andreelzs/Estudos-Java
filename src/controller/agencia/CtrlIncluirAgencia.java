package controller.agencia;

import controller.CtrlAbstrato;
import model.Agencia;
import model.ModelException;
import model.dao.DaoAgencia;

public class CtrlIncluirAgencia extends CtrlAbstratoAgencia {
    public CtrlIncluirAgencia(CtrlAbstrato ctrlPai) {
        super(ctrlPai);
    }

    @Override
    public void efetuar(int numero, String bairro, String cidade) {
        try {
            Agencia novaAgencia = new Agencia(numero, bairro, cidade);
            DaoAgencia dao = new DaoAgencia();
            dao.incluir(novaAgencia);
            this.agencia = novaAgencia;
            this.janela.notificar("Agência incluída com sucesso!");
            this.encerrar();
        } catch (ModelException e) {
            this.janela.notificar(e.getMessage());
        }
    }
}