package rgr;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;

import java.awt.CardLayout;

import widgets.ChooseRandom;

import javax.swing.JTabbedPane;

import java.awt.Dimension;

import widgets.Diagram;
import widgets.ChooseData;

import javax.swing.JButton;

import process.Dispatcher;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Random;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

import rnd.Linear;
import rnd.Negexp;
import rnd.Triangular;
import rnd.Uniform;
import stat.Histo;

import javax.swing.JTextArea;

import myRegress.RegressExp;
import myRegress.RegressLn;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.swing.JComboBox;



import widgets.experiments.ExperimentManager;
//import widgets.experiments.ExperimentControl;
import widgets.regres.RegresAnaliser;
import widgets.regres.RegresTesters;
//import widgets.trans.TransMonitorView;
import widgets.trans.TransProcessManager;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.GridLayout;

public class Main extends JFrame {

	private JPanel contentPane;
	JButton btnNewButton;
	private Diagram diagram_2;
	private Diagram diagram;
	private Diagram diagram_1;
	private ChooseRandom chooseRandom_3;
	ChooseData chooseData_3;
	private ChooseRandom chooseRandom;
	private ChooseData chooseData;
	private ChooseData chooseData_1;
	private ChooseRandom chooseRandom_1;
	private ChooseRandom chooseRandom_2;
	private ChooseData chooseData_2;
	/**
	 * Launch the application.
	 */
	private final Histo histo = new Histo();
	private Histo hist_1 = new Histo();
	private Diagram diagram_stat;
	private JTextArea textArea;
	private JButton btnStat;
	private JComboBox comboBox;
	private Model model;
	private Diagram diagram_regres;
//	private TransMonitorView transMonitorView;
	private Diagram diagram_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Vibory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, "name_76206124039226");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);

		final JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("\u0422\u0435\u0441\u0442\u0443\u0432\u0430\u043D\u043D\u044F");
		tabbedPane
				.addTab("\u0422\u0435\u0441\u0442\u0443\u0432\u0430\u043D\u043D\u044F",
						null, panel_1, null);

		btnNewButton = new JButton("TEST");
		btnNewButton.setBounds(0, 420, 592, 55);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start_test();

			}

			private void start_test() {
				final Dispatcher dispatcher = new Dispatcher();
				Factory factory = new Factory(Main.this);
				Model model = factory.createModel(dispatcher);
				model.initForTest();
				btnNewButton.setEnabled(false);
				getDiagram().clear();
				getDiagram_1().clear();
				getDiagram_2().clear();
				dispatcher.start();
				new Thread() {
					public void run() {
						try {
							dispatcher.getThread().join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						btnNewButton.setEnabled(true);
					}
				}.start();

			}
		});
								panel_1.setLayout(null);
						
								diagram = new Diagram();
								diagram.setBounds(0, 0, 592, 140);
								diagram.setPainterColor(Color.RED);
								diagram.setTitleText("\u0427\u0435\u0440\u0433\u0430 \u0434\u043E \u0440\u0435\u0454\u0441\u0442\u0440\u0430\u0442\u043E\u0440\u0456\u0432");
								panel_1.add(diagram);
				
						diagram_2 = new Diagram();
						diagram_2.setBounds(0, 140, 592, 140);
						diagram_2.setPainterColor(Color.BLUE);
						diagram_2
								.setTitleText("\u0427\u0435\u0440\u0433\u0430 \u0434\u043E \u043A\u0430\u0431\u0456\u043D\u043E\u043A");
						panel_1.add(diagram_2);
		
				diagram_1 = new Diagram();
				diagram_1.setBounds(0, 280, 592, 140);
				diagram_1.setPainterColor(new Color(0, 128, 0));
				panel_1.add(diagram_1);
				diagram_1
						.setTitleText("\u0427\u0435\u0440\u0433\u0430 \u0434\u043E \u0443\u0440\u043D");
		panel_1.add(btnNewButton);
		String[] s = { "Черга на реєстрацію", "Черга до кабінок",
				"Черга до урн", "Час чекання на реєстрацію",
				"Час чекання кабінки", "Час чекання урни",
				"Час простою реєстратора" };
		DefaultComboBoxModel<String> mod = new DefaultComboBoxModel<>(s);
		
				JPanel panel_2 = new JPanel();
				tabbedPane.addTab(
						"\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0430",
						null, panel_2, null);
				panel_2.setLayout(null);
				
						diagram_stat = new Diagram();
						diagram_stat.setBounds(10, 11, 365, 197);
						panel_2.add(diagram_stat);
						
								textArea = new JTextArea();
								textArea.setBounds(10, 250, 365, 145);
								panel_2.add(textArea);
								
										btnStat = new JButton(
												"\u041F\u043E\u0447\u0430\u0442\u0438 \u0435\u043A\u0441\u043F\u0435\u0440\u0438\u043C\u0435\u043D\u0442");
										btnStat.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												stat();
											}

										});
										
												btnStat.setBounds(10, 417, 365, 23);
												panel_2.add(btnStat);
												
														comboBox = new JComboBox();
														comboBox.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent arg0) {
																if (comboBox.getSelectedIndex() == 0) {
																	getTextArea().setText(model.getHistoToReg().toString());
																	getTextArea().select(0, 0);
																	model.getHistoToReg().showRelFrec(getDiagram_stat());

																}
																if (comboBox.getSelectedIndex() == 1) {
																	getTextArea().setText(model.getHistoToCab().toString());
																	getTextArea().select(0, 0);
																	model.getHistoToCab().showRelFrec(getDiagram_stat());
																	
																}
																if (comboBox.getSelectedIndex() == 2) {
																	getTextArea().setText(model.getHistoToBox().toString());
																	getTextArea().select(0, 0);
																	model.getHistoToBox().showRelFrec(getDiagram_stat());
																}
																if (comboBox.getSelectedIndex() == 3) {
																	getTextArea().setText(model.getHistoWaitForBool().toString());
																	getTextArea().select(0, 0);
																	model.getHistoWaitForBool().showRelFrec(getDiagram_stat());

																}
																if (comboBox.getSelectedIndex() == 4) {
																	getTextArea().setText(model.getHistoWaitForCab().toString());
																	getTextArea().select(0, 0);
																	model.getHistoWaitForCab().showRelFrec(getDiagram_stat());
																}
																if (comboBox.getSelectedIndex() == 5) {
																	getTextArea().setText(model.getHistoWaitForBox().toString());
																	getTextArea().select(0, 0);
																	model.getHistoWaitForCab().showRelFrec(getDiagram_stat());
																}
																if (comboBox.getSelectedIndex() == 6) {
																	getTextArea().setText(model.getHistoWaitForViborets().toString());
																	getTextArea().select(0, 0);
																	model.getHistoWaitForViborets().showRelFrec(getDiagram_stat());

																}

															}
														});
														comboBox.setModel(mod);
														comboBox.setBounds(10, 219, 196, 20);
														panel_2.add(comboBox);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("\u0420\u0435\u0433\u0440\u0435\u0441\u0456\u044F", null, panel_3, null);
		panel_3.setLayout(new CardLayout(0, 0));
		
		ExperimentManager experimentManager = new ExperimentManager();
		experimentManager.getChooseDataFactors().setText("4 5 6 7");
		experimentManager.setFactory((d) -> new Model(d, this));
		panel_3.add(experimentManager, "name_763398340914985");
		
//		diagram_regres = new Diagram();
//		diagram_regres.setVerticalMaxText("1");
//		diagram_regres.setHorizontalMaxText("10");
//		diagram_regres.setTitleText("\u0417\u0430\u043B\u0435\u0436\u043D\u0456\u0441\u0442\u044C \u0447\u0430\u0441\u0443 \u043F\u0440\u043E\u0441\u0442\u043E\u044E \u0440\u0435\u0433\u0456\u0441\u0442\u0440\u0430\u0442\u043E\u0440\u0430 \u0432\u0456\u0434 \u043A\u0456\u043B\u044C\u043A\u043E\u0441\u0442\u0456 \u0432\u0438\u0431\u043E\u0440\u0446\u0456\u0432");
//		diagram_regres.setBounds(10, 11, 365, 140);
//		panel_3.add(diagram_regres);
//		
//		ExperimentControl experimentControl = new ExperimentControl();
//		experimentControl.setTextFactors("4 5 6 7");
//		experimentControl.setBounds(10, 151, 365, 82);
//		experimentControl.setFactory(new Factory(this));
//		experimentControl.setDiagram(diagram_regres);
//		panel_3.add(experimentControl);
//		
//		RegresAnaliser regresAnaliser = new RegresAnaliser();
//		regresAnaliser.setBounds(10, 231, 365, 213);
//		regresAnaliser.setDiagram(diagram_regres);
//		regresAnaliser.setIRegresable(experimentControl);
//		regresAnaliser.addFunction(new RegressExp());
//		regresAnaliser.addFunction(new RegressLn());
//		panel_3.add(regresAnaliser);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "\u041D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438 \u043F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u043E\u0432", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setPreferredSize(new Dimension(250, 10));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);

		chooseData = new ChooseData();
		chooseData.setText("3");
		chooseData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (panel_1.isShowing()) {
				}
			}
		});
		chooseData
				.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0440\u0435\u0454\u0441\u0442\u0440\u0430\u0442\u043E\u0440\u0456\u0432");
		chooseData.setBounds(20, 270, 245, 46);
		panel.add(chooseData);

		chooseData_2 = new ChooseData();
		chooseData_2.setText("2");
		chooseData_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel_1.isShowing()) {
					String n = chooseData_2.getText();
					diagram_1.setVerticalMaxText(n);
				}
			}
		});
		chooseData_2
				.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0443\u0440\u043D");
		chooseData_2.setBounds(20, 373, 245, 46);
		panel.add(chooseData_2);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("\u041F\u0435\u0440\u0435\u0445\u0456\u0434\u043D\u0438\u0439 \u043F\u0440\u043E\u0446\u0435\u0441", null, panel_4, null);
		panel_4.setLayout(new CardLayout(0, 0));
		
		TransProcessManager transProcessManager = new TransProcessManager();
		transProcessManager.setFactory((d)->new Model(d, this));
		panel_4.add(transProcessManager, "name_763356898877712");
		
//		diagram_3 = new Diagram();
//		diagram_3.setTitleText("\u041F\u0435\u0440\u0435\u0445\u0456\u0434\u043D\u0438\u0439 \u043F\u0440\u043E\u0446\u0435\u0441 \u0434\u043B\u044F \u0440\u043E\u0437\u043C\u0456\u0440\u0443 \u0447\u0435\u0440\u0433\u0438 \u0434\u043E \u0440\u0435\u0433\u0456\u0441\u0442\u0440\u0430\u0442\u043E\u0440\u0430");
//		diagram_3.setBounds(10, 241, 365, 199);
//		diagram_3.setPainterColor(Color.red);
//		panel_4.add(diagram_3);
//
//		transMonitorView = new TransMonitorView();
//		transMonitorView.setBounds(92, 11, 202, 219);
//		transMonitorView.setFactory(new Factory(this));
//		transMonitorView.setDiagram(diagram_3);
//		panel_4.add(transMonitorView);

		chooseData_1 = new ChooseData();
		chooseData_1.setText("2");
		chooseData_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel_1.isShowing()) {
					String n = chooseData_1.getText();
					diagram_2.setVerticalMaxText(n);
				}
			}
		});
		chooseData_1
				.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043A\u0430\u0431\u0456\u043D\u043E\u043A");
		chooseData_1.setBounds(20, 316, 245, 46);
		panel.add(chooseData_1);

		chooseRandom = new ChooseRandom();
		chooseRandom.setBounds(10, 11, 290, 63);
		chooseRandom.setTitle("\u0427\u0430\u0441 \u0440\u0435\u0454\u0441\u0442\u0440\u0430\u0446\u0456\u0457");
		chooseRandom.setRandom(new Triangular(5,7,10));
		panel.add(chooseRandom);

		chooseRandom_1 = new ChooseRandom();
		chooseRandom_1.setTitle("\u0427\u0430\u0441 \u0437\u0430\u043F\u043E\u0432\u043D\u0435\u043D\u043D\u044F \u0431\u044E\u043B\u0435\u0442\u0435\u043D\u044F");
		chooseRandom_1.setBounds(10, 85, 290, 56);
		chooseRandom_1.setRandom(new Triangular(1, 3, 5));
		panel.add(chooseRandom_1);

		chooseRandom_2 = new ChooseRandom();
		chooseRandom_2.setTitle("\u0427\u0430\u0441 \u0437\u0430\u043A\u0438\u0434\u0430\u043D\u043D\u044F \u0434\u043E \u0443\u0440\u043D\u0438");
		chooseRandom_2.setBounds(10, 152, 290, 56);
		chooseRandom_2.setRandom(new Uniform(0.1, 1.0));
		panel.add(chooseRandom_2);

		chooseRandom_3 = new ChooseRandom();
		chooseRandom_3
				.setTitle("\u0406\u043D\u0442\u0435\u0440\u0432\u0430\u043B \u043F\u043E\u044F\u0432\u0438 \u0432\u0438\u0431\u043E\u0440\u0446\u0456\u0432");
		chooseRandom_3.setBounds(10, 206, 280, 56);
		chooseRandom_3.setRandom(new Negexp(2));
		panel.add(chooseRandom_3);

		chooseData_3 = new ChooseData();
		chooseData_3.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if (panel_1.isShowing()) {
					String n = chooseData_3.getText();
					diagram.setHorizontalMaxText(n);
					diagram_1.setHorizontalMaxText(n);
					diagram_2.setHorizontalMaxText(n);
				}
			}
		});

		chooseData_3.setText("1000");
		chooseData_3
				.setTitle("\u0427\u0430\u0441 \u043C\u043E\u0434\u0435\u043B\u044E\u0432\u0430\u043D\u043D\u044F");
		chooseData_3.setBounds(20, 415, 245, 46);
		panel.add(chooseData_3);

	}

	protected void stat() {

		final Dispatcher dispatcher = new Dispatcher();
		Factory factory = new Factory(Main.this);
		model = factory.createModel(dispatcher);
		model.initForStat();
		getDiagram_stat().clear();
		getTextArea().setText("");
		getBtnStat().setEnabled(false);
		dispatcher.start();
		new Thread() {
			public void run() {
				try {
					dispatcher.getThread().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getBtnStat().setEnabled(true);
				model.getHistoToReg().showRelFrec(getDiagram_stat());
				getTextArea().setText(model.getHistoToReg().toString());
				getTextArea().select(0, 0);

			}
		}.start();
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public Diagram getDiagram_2() {
		return diagram_2;
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public Diagram getDiagram_1() {
		return diagram_1;
	}

	public ChooseRandom getChooseRandom_3() {
		return chooseRandom_3;
	}

	public ChooseData getChooseData_3() {
		return chooseData_3;
	}

	public ChooseRandom getChooseRandom() {
		return chooseRandom;
	}

	public ChooseData getChooseData() {
		return chooseData;
	}

	public ChooseData getChooseData_1() {
		return chooseData_1;
	}

	public ChooseRandom getChooseRandom_1() {
		return chooseRandom_1;
	}

	public ChooseRandom getChooseRandom_2() {
		return chooseRandom_2;
	}

	public ChooseData getChooseData_2() {
		return chooseData_2;
	}

	public Diagram getDiagram_stat() {
		return diagram_stat;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getBtnStat() {
		return btnStat;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
	public Diagram getDiagram_regres() {
		return diagram_regres;
	}
//	public TransMonitorView getTransMonitorView() {
//		return transMonitorView;
//	}
	public Diagram getDiagram_3() {
		return diagram_3;
	}
}
