package controller.conta;

import controller.CtrlAbstrato;
import controller.CtrlPrograma;
import model.Conta;
import model.dao.DaoConta;
import viewer.JanelaConsultarContas;

public class CtrlManterContas extends CtrlAbstrato {
    private JanelaConsultarContas janela;
    private CtrlAbstratoConta ctrlConta;

    public CtrlManterContas(CtrlAbstrato ctrlPai) {
        super(ctrlPai);
        DaoConta dao = new DaoConta();
        this.janela = new JanelaConsultarContas(this, dao.obterTodos());
        this.janela.setVisible(true);
    }

    public void iniciarIncluirConta() {
        this.ctrlConta = new CtrlIncluirConta(this);
    }

    public void iniciarAlterarConta(Conta conta) {
        if (conta == null) {
            this.janela.notificar("Selecione uma conta para alterar.");
            return;
        }
        this.ctrlConta = new CtrlAlterarConta(this, conta);
    }

    public void iniciarExcluirConta(Conta conta) {
        if (conta == null) {
            this.janela.notificar("Selecione uma conta para excluir.");
            return;
        }
        this.ctrlConta = new CtrlExcluirConta(this, conta);
    }

    public void fimEditarConta() {
        this.ctrlConta = null;
        DaoConta dao = new DaoConta();
        this.janela.atualizarDados(dao.obterTodos());
    }

    @Override
    public void encerrar() {
        this.janela.dispose();
        CtrlPrograma ctrl = (CtrlPrograma) this.getCtrlPai();
        ctrl.fimConsultarContas();
    }

    @Override
    public Object getBemTangivel() {
        return new DaoConta().obterTodos();
    }
}