package controller;

import controller.agencia.CtrlManterAgencias;
import controller.conta.CtrlManterContas;
import controller.curso.CtrlManterCursos;
import model.dao.Serializador;
import viewer.JanelaPrincipal;

public class CtrlPrograma extends CtrlAbstrato {
    private JanelaPrincipal janela;
    private CtrlIncluirAluno ctrlIncluirAluno;
    private CtrlConsultarAlunos ctrlConsultarAlunos;
    private CtrlManterCursos ctrlConsultarCursos;
    private CtrlManterAgencias ctrlManterAgencias;
    private CtrlManterContas ctrlManterContas;

    public CtrlPrograma() {
        super(null);
        Serializador.recuperarObjetos();
        this.janela = new JanelaPrincipal(this);
        this.janela.setVisible(true);
    }

    @Override
    public Object getBemTangivel() {
        return null;
    }

    @Override
    public void encerrar() {
        this.janela.notificar("Encerrando a execução do sistema");
        this.janela.setVisible(false);
        Serializador.salvarObjetos();
        System.exit(0);
    }

    public void iniciarIncluirAluno() {
        this.ctrlIncluirAluno = new CtrlIncluirAluno(this);
    }

    public void fimIncluirAluno() {
        this.ctrlIncluirAluno = null;
    }

    public void iniciarConsultarAlunos() {
        this.ctrlConsultarAlunos = new CtrlConsultarAlunos(this);
    }

    public void fimConsultarAlunos() {
        this.ctrlConsultarAlunos = null;
    }

    public void iniciarConsultarCursos() {
        this.ctrlConsultarCursos = new CtrlManterCursos(this);
    }

    public void fimConsultarCursos() {
        this.ctrlConsultarCursos = null;
    }

    public void iniciarManterAgencias() {
        this.ctrlManterAgencias = new CtrlManterAgencias(this);
    }
    
    public void fimConsultarAgencias() {
        this.ctrlManterAgencias = null;
    }

    public void iniciarManterContas() {
        this.ctrlManterContas = new CtrlManterContas(this);
    }
    
    public void fimConsultarContas() {
        this.ctrlManterContas = null;
    }

    public static void main(String[] args) {
        new CtrlPrograma();
    }
}