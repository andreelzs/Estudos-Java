package controller.agencia;

import controller.CtrlAbstrato;
import controller.CtrlPrograma;
import model.Agencia;
import model.dao.DaoAgencia;
import viewer.JanelaConsultarAgencias;

public class CtrlManterAgencias extends CtrlAbstrato {
    private JanelaConsultarAgencias janela;
    private CtrlAbstratoAgencia ctrlAgencia;

    public CtrlManterAgencias(CtrlAbstrato ctrlPai) {
        super(ctrlPai);
        DaoAgencia dao = new DaoAgencia();
        this.janela = new JanelaConsultarAgencias(this, dao.obterTodos());
        this.janela.setVisible(true);
    }

    public void iniciarIncluirAgencia() {
        this.ctrlAgencia = new CtrlIncluirAgencia(this);
    }

    public void iniciarAlterarAgencia(Agencia agencia) {
        if (agencia == null) {
            this.janela.notificar("Selecione uma agência para alterar.");
            return;
        }
        this.ctrlAgencia = new CtrlAlterarAgencia(this, agencia);
    }

    public void iniciarExcluirAgencia(Agencia agencia) {
        if (agencia == null) {
            this.janela.notificar("Selecione uma agência para excluir.");
            return;
        }
        this.ctrlAgencia = new CtrlExcluirAgencia(this, agencia);
    }

    public void fimEditarAgencia() {
        this.ctrlAgencia = null;
        DaoAgencia dao = new DaoAgencia();
        this.janela.atualizarDados(dao.obterTodos());
    }

    @Override
    public void encerrar() {
        this.janela.dispose();
        CtrlPrograma ctrl = (CtrlPrograma) this.getCtrlPai();
        ctrl.fimConsultarAgencias();
    }

    @Override
    public Object getBemTangivel() {
        return new DaoAgencia().obterTodos();
    }
}