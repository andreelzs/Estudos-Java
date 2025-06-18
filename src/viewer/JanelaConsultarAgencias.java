package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import controller.agencia.CtrlManterAgencias;
import model.Agencia;

public class JanelaConsultarAgencias extends JanelaAbstrata<CtrlManterAgencias> {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Agencia[] listaAgencias;

    public JanelaConsultarAgencias(CtrlManterAgencias ctrl, Agencia[] agencias) {
        super(ctrl);
        setTitle("Manter Agências");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 415, 200);
        contentPane.add(scrollPane);

        atualizarDados(agencias);
        table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.setBounds(10, 225, 100, 25);
        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCtrl().iniciarIncluirAgencia();
            }
        });
        contentPane.add(btnIncluir);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(120, 225, 100, 25);
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCtrl().iniciarAlterarAgencia(obterLinhaSelecionada());
            }
        });
        contentPane.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(230, 225, 100, 25);
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCtrl().iniciarExcluirAgencia(obterLinhaSelecionada());
            }
        });
        contentPane.add(btnExcluir);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(340, 225, 85, 25);
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCtrl().encerrar();
            }
        });
        contentPane.add(btnSair);
    }

    public void atualizarDados(Agencia[] agencias) {
        this.listaAgencias = agencias;
        if(this.table != null) {
            HelperTableModel h = new HelperTableModel(this.listaAgencias);
            this.table.setModel(h.getTableModel());
        }
    }

    public Agencia obterLinhaSelecionada() {
        int linha = this.table.getSelectedRow();
        if (linha == -1) {
            return null;
        }
        return this.listaAgencias[linha];
    }
}