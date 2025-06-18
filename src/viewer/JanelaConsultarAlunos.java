package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.CtrlPrograma;

public class JanelaPrincipal extends JanelaAbstrata<CtrlPrograma> {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public JanelaPrincipal(CtrlPrograma ctrl) {
        super(ctrl);
        setTitle("Janela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 228);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btDepartamento = new JButton("Departamento");
        btDepartamento.setBounds(149, 84, 119, 23);
        btDepartamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JanelaDepartamento().setVisible(true);
            }
        });
        contentPane.add(btDepartamento);

        JButton btSair = new JButton("Sair");
        btSair.setBounds(278, 130, 119, 23);
        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCtrl().encerrar();
            }
        });
        contentPane.add(btSair);

        JButton btDisciplina = new JButton("Disciplina");
        btDisciplina.setBounds(20, 84, 119, 23);
        btDisciplina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JanelaDisciplina().setVisible(true);
            }
        });
        contentPane.add(btDisciplina);

        JButton btIncluirEmpregado = new JButton("Empregado");
        btIncluirEmpregado.setBounds(278, 84, 119, 23);
        btIncluirEmpregado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JanelaEmpregado().setVisible(true);
            }
        });
        contentPane.add(btIncluirEmpregado);

        JButton btConsultarAlunos = new JButton("Alunos");
        btConsultarAlunos.setBounds(149, 37, 119, 23);
        btConsultarAlunos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCtrl().iniciarConsultarAlunos();
            }
        });
        contentPane.add(btConsultarAlunos);

        JButton btConsultarCursos = new JButton("Cursos");
        btConsultarCursos.setBounds(20, 37, 119, 23);
        btConsultarCursos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCtrl().iniciarConsultarCursos();
            }
        });
        contentPane.add(btConsultarCursos);
        
        JButton btnManterAgencias = new JButton("Agências");
        btnManterAgencias.setBounds(20, 130, 119, 23);
        btnManterAgencias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCtrl().iniciarManterAgencias();
            }
        });
        contentPane.add(btnManterAgencias);

        JButton btnManterContas = new JButton("Contas");
        btnManterContas.setBounds(149, 130, 119, 23);
        btnManterContas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCtrl().iniciarManterContas();
            }
        });
        contentPane.add(btnManterContas);
    }
}