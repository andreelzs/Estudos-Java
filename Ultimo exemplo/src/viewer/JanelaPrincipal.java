package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CtrlPrograma;

public class JanelaPrincipal extends JanelaAbstrata {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal(CtrlPrograma ctrl) {
		super(ctrl);
		setTitle("Janela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCtrl().encerrar();
			}
		});
		btSair.setBounds(149, 130, 119, 23);
		contentPane.add(btSair);
		
		JButton btManterAgencias = new JButton("Agências");
		btManterAgencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Recuperando quem é o controlador de caso de uso
				// vinculado a esta janela
				CtrlPrograma ctrl = (CtrlPrograma)getCtrl();
				// Aviso ao controlador que o usuário deseja iniciar
				// o caso de uso "Consultar Agências"				
				ctrl.iniciarConsultarAgencias();
			}
		});
		btManterAgencias.setBounds(20, 37, 119, 23);
		contentPane.add(btManterAgencias);
		
		JButton btContasCorrente = new JButton("Contas");
		btContasCorrente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Recuperando quem é o controlador de caso de uso
				// vinculado a esta janela
				CtrlPrograma ctrl = (CtrlPrograma)getCtrl();
				// Aviso ao controlador que o usuário deseja iniciar
				// o caso de uso "Consultar Contas"				
				ctrl.iniciarConsultarContas();
			}
		});
		btContasCorrente.setBounds(179, 37, 119, 23);
		contentPane.add(btContasCorrente);
	}
}
