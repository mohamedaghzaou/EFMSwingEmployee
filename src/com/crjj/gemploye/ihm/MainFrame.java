package com.crjj.gemploye.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.crjj.gemploye.ihm.AbstractTableModel.EmployeTableModel;
import com.crjj.gemploye.metier.IMetier;
import com.crjj.gemploye.metier.MetierEmploye;
import com.crjj.gemploye.model.Employe;
import com.ismo.brevets.ihm.util.DoubleDocument;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtSalaire;
	private JTable table;
	private EmployeTableModel employeModel;
	private static final String DATABASE_NAME = "dbEemexamen";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	IMetier<Employe> metier;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBorder(new TitledBorder(null, "Nouveau Employe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 621, 122);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(" Nom :");
		lblNewLabel.setBounds(29, 25, 46, 14);
		panel.add(lblNewLabel);

		txtNom = new JTextField();
		txtNom.setBounds(29, 50, 115, 20);
		panel.add(txtNom);
		txtNom.setColumns(10);

		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(197, 50, 115, 20);
		panel.add(txtPrenom);

		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setBounds(197, 25, 46, 14);
		panel.add(lblPrenom);

		txtSalaire = new JTextField();
		txtSalaire.setColumns(10);
		txtSalaire.setBounds(484, 50, 115, 20);
		txtSalaire.setDocument(new DoubleDocument());
		panel.add(txtSalaire);

		JLabel lblNewLabel_1_1 = new JLabel("Salaire :");
		lblNewLabel_1_1.setBounds(484, 25, 46, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Departement :");
		lblNewLabel_1_2.setBounds(340, 25, 100, 14);
		panel.add(lblNewLabel_1_2);

		JComboBox<String> cmbDepartement = new JComboBox<String>();
		cmbDepartement.setModel(new DefaultComboBoxModel<String>(
				new String[] { "RH", "COMPATABLE", "DIRECTION", "FINANCE", "MARKETING " }));
		cmbDepartement.setBounds(340, 49, 100, 22);
		panel.add(cmbDepartement);

		JButton btnAjouter = new JButton("Ajouter");

		btnAjouter.setBounds(484, 88, 115, 23);
		panel.add(btnAjouter);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.MAGENTA);
		panel_1.setBounds(10, 144, 621, 202);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 601, 151);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		metier = new MetierEmploye();
		employeModel = new EmployeTableModel(metier.findAll());

		table.setModel(employeModel);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "selectioner un line");
					return;
				}
				int code = employeModel.getEmployeByIndex(table.getSelectedRow()).getCode();
				metier.delete(code);
				employeModel.remove(table.getSelectedRow());

			}
		});
		btnSupprimer.setBounds(496, 173, 115, 23);
		panel_1.add(btnSupprimer);

		JButton btnstatistique = new JButton("statistique");
		btnstatistique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openChart();
			}
		});
		btnstatistique.setBounds(118, 357, 115, 23);
		contentPane.add(btnstatistique);

		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Open();
			}
		});
		btnImprimer.setBounds(317, 357, 115, 23);
		contentPane.add(btnImprimer);

		JButton btnQuiter = new JButton("Quiter");
		btnQuiter.setBounds(516, 357, 115, 23);
		contentPane.add(btnQuiter);

		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtNom.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Nom est vide");
					return;
				}
				if (txtPrenom.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Prenom est vide");
					return;
				}
				if (txtSalaire.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "saliare est vide");
					return;
				}

				Employe emp = new Employe(txtNom.getText(), txtPrenom.getText(),
						String.valueOf(cmbDepartement.getSelectedItem()), Double.parseDouble(txtSalaire.getText()));
				metier.save(emp);
				employeModel.add(emp);
			}
		});

	}

	public void Open() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE_NAME, USERNAME, PASSWORD);

			JasperPrint print = JasperFillManager.fillReport("rptEmployees.jasper", null, conn);
			JRViewer viewer = new JRViewer(print);
			JFrame frame = new JFrame("");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().add(viewer);
			frame.setSize(new Dimension(750, 650));
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}

	}

	private void openChart() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		List<Object[]> list = metier.employerParDep();
		for (Object[] e : list) {

			dataset.setValue(String.valueOf(e[0]), Integer.parseInt(String.valueOf(e[1])));

		}

		JFrame frame = new JFrame("Employee Par Departement");
		frame.setSize(new Dimension(750, 650));
		frame.setLocationRelativeTo(null);

		JFreeChart chart1 = ChartFactory.createPieChart("Employee Par Departement", dataset, true, true, true);

		JPanel p = new JPanel();
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));

		frame.add(new ChartPanel(chart1));
		frame.setVisible(true);

	}
}
