package controller.agencia;

import controller.CtrlAbstrato;
import model.Agencia;
import viewer.JanelaAgencia;

public abstract class CtrlAbstratoAgencia extends CtrlAbstrato {
    protected JanelaAgencia janela;
    protected Agencia agencia;

    public CtrlAbstratoAgencia(CtrlAbstrato ctrlPai, Agencia agencia) {
        super(ctrlPai);
        this.agencia = agencia;
        this.janela = new JanelaAgencia(this, agencia);
        this.janela.setVisible(true);
    }

    public CtrlAbstratoAgencia(CtrlAbstrato ctrlPai) {
        this(ctrlPai, null);
    }

    public abstract void efetuar(int numero, String bairro, String cidade);

    public void encerrar() {
        this.janela.dispose();
        CtrlManterAgencias ctrl = (CtrlManterAgencias) this.getCtrlPai();
        ctrl.fimEditarAgencia();
    }

    @Override
    public Object getBemTangivel() {
        return this.agencia;
    }
}