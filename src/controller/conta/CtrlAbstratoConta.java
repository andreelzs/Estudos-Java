package controller.conta;

import controller.CtrlAbstrato;
import model.Agencia;
import model.Conta;
import viewer.JanelaConta;

public abstract class CtrlAbstratoConta extends CtrlAbstrato {
    protected JanelaConta janela;
    protected Conta conta;

    public CtrlAbstratoConta(CtrlAbstrato ctrlPai, Conta conta) {
        super(ctrlPai);
        this.conta = conta;
        this.janela = new JanelaConta(this, conta);
        this.janela.setVisible(true);
    }

    public CtrlAbstratoConta(CtrlAbstrato ctrlPai) {
        this(ctrlPai, null);
    }

    public abstract void efetuar(int numero, String nomeCorrentista, String chavePix, double saldo, double limite, Agencia agencia);

    public void encerrar() {
        this.janela.dispose();
        CtrlManterContas ctrl = (CtrlManterContas) this.getCtrlPai();
        ctrl.fimEditarConta();
    }

    @Override
    public Object getBemTangivel() {
        return this.conta;
    }
}