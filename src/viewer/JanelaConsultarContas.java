package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.conta.CtrlManterContas;
import model.Conta;

public class JanelaConsultarContas extends JanelaAbstrata {
	//
	// ATRIBUTOS
	//
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabela;
	private Conta[] listaContas;

	/**
	 * Create the frame.
	 */
	public JanelaConsultarContas(CtrlManterContas c, Conta[] conjContas) {
		super(c);
		setTitle("Agências");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btSair.setBounds(335, 227, 89, 23);
		contentPane.add(btSair);

		// A chamada a 'atualizarDados' precisa vir antes
		// da criação do JScrollPane
		this.atualizarDados(conjContas);

		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 11, 414, 200);
		contentPane.add(scrollPane);

		JButton btIncluir = new JButton("Incluir");
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notifico ao controlador que o usuário quer iniciar o caso de uso
				// Incluir Agência
				CtrlManterContas ctrl = (CtrlManterContas)getCtrl(); 
				ctrl.iniciarIncluirConta();
			}
		});
		btIncluir.setBounds(10, 227, 89, 23);
		contentPane.add(btIncluir);

		JButton btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando se o usuário selecionou alguma agência
				Conta a = obterLinhaSelecionada();
				// Se o usuário selecionou alguma agência
				if (a != null) {
					CtrlManterContas ctrl = (CtrlManterContas)getCtrl(); 
					ctrl.iniciarExcluirConta(a);
				} else
					notificar("Selecione uma agência para exclusão");
			}
		});
		btExcluir.setBounds(121, 227, 89, 23);
		contentPane.add(btExcluir);

		JButton btAlterar = new JButton("Alterar");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando se o usuário selecionou alguma agência
				Conta a = obterLinhaSelecionada();
				// Se o usuário selecionou alguma agência
				if (a != null) {
					CtrlManterContas ctrl = (CtrlManterContas)getCtrl(); 
					ctrl.iniciarAlterarConta(a);
				} else
					notificar("Selecione uma agência para alteração");
			}
		});
		btAlterar.setBounds(231, 227, 89, 23);
		contentPane.add(btAlterar);

		this.setVisible(true);
	}

	/**
	 * Atualiza os dados apresentados no JTable da janela
	 */
	public void atualizarDados(Conta[] conjContas) {
		this.listaContas = conjContas;
		HelperTableModel h = new HelperTableModel(this.listaContas);
		if (this.tabela == null)
			this.tabela = new JTable(h.getTableModel());
		else
			this.tabela.setModel(h.getTableModel());
	}

	/**
	 * Retorna qual objeto
	 * 
	 * @return
	 */
	public Conta obterLinhaSelecionada() {
		int numLinhaSelecionada = this.tabela.getSelectedRow();
		if (numLinhaSelecionada != -1)
			return this.listaContas[numLinhaSelecionada];
		return null;
	}
}


