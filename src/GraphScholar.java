import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font; 
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class GraphScholar {

	private JFrame frmGraphscholar; 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4; 
	private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private GoogleScholar_Author gsa;
    private String fText;
    private String radio_value;
    private String socialNetwork_value;
     	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphScholar window = new GraphScholar();
					window.frmGraphscholar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public GraphScholar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		
		frmGraphscholar = new JFrame();
		frmGraphscholar.setTitle("GraphScholar 1.0");
		frmGraphscholar.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\husseiny.hazimeh\\Downloads\\sparql.png"));
		frmGraphscholar.setResizable(false);
		frmGraphscholar.setBounds(100, 100, 822, 569);
		frmGraphscholar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JTextArea display = new JTextArea (10,48);
		
		JMenuBar menuBar = new JMenuBar();
		frmGraphscholar.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmClose = new JMenuItem("Exit");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmHome = new JMenuItem("Home");
		
		mnFile.add(mntmHome);
		 
		mnFile.add(mntmClose);
		
		JMenu mnNew = new JMenu("New");
		menuBar.add(mnNew);
		
		JMenuItem mntmKnowledgeGraph = new JMenuItem("Knowledge Graph");
		
		mntmKnowledgeGraph.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\152060807319498508.png"));
		mnNew.add(mntmKnowledgeGraph);
		
		JMenuItem mntmKnowledgeGraphEmbedding = new JMenuItem("Knowledge Graph Embedding");
		
		mntmKnowledgeGraphEmbedding.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\kge2.png"));
		mnNew.add(mntmKnowledgeGraphEmbedding);
		
		JMenuItem mntmLocalSparqlQuery = new JMenuItem("Local SPARQL Query");
		mntmLocalSparqlQuery.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\query.png"));
		mnNew.add(mntmLocalSparqlQuery);
		
		JMenuItem mntmMetaphactsQuery = new JMenuItem("Metaphacts Query");
		mntmMetaphactsQuery.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\metaphacts$.png"));
		mnNew.add(mntmMetaphactsQuery);
		
		JMenu mnLoad = new JMenu("Load");
		menuBar.add(mnLoad);
		
		JMenuItem mntmSystemKnowledgeGraph = new JMenuItem("System Knowledge Graph");
		
		mntmSystemKnowledgeGraph.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\skg2.png"));
		mnLoad.add(mntmSystemKnowledgeGraph);
		
		JMenu mnMachineLearningZone = new JMenu("Machine Learning Zone");
		menuBar.add(mnMachineLearningZone);
		
		JMenuItem mntmAddInstancesTo = new JMenuItem("Add Instances to the Learning Set");
		mntmAddInstancesTo.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\set.png"));
		mnMachineLearningZone.add(mntmAddInstancesTo);
		frmGraphscholar.getContentPane().setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 819, 522);
		frmGraphscholar.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(295, 164, 207, 59);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\logo.png"));
		
		JLabel lblByHusseinHazimeh = new JLabel("By: Hussein Hazimeh");
		lblByHusseinHazimeh.setBounds(339, 234, 171, 14);
		panel.add(lblByHusseinHazimeh);
		lblByHusseinHazimeh.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblToStartPlease = new JLabel("To start, please chose one of the actions from the above menu!");
		lblToStartPlease.setBounds(228, 356, 363, 14);
		panel.add(lblToStartPlease);
		lblToStartPlease.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 819, 522);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder ( new TitledBorder ( new EtchedBorder (), "New Knowledge Graph (KG)" ) );
		
		JLabel label = new JLabel("Please create a new KG by following the steps below");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(45, 25, 478, 14);
		panel_1.add(label);
		
		JPanel panel_121 = new JPanel();
		panel_121.setBounds(0, 85, 570, 200);
		panel_121.setBorder ( new TitledBorder ( new EtchedBorder (), "Information" ) );
		
		
		JLabel label_1 = new JLabel("Entity name (Author):");
		label_1.setBounds(38, 65, 133, 14);
		panel_121.add(label_1);
		
		textField = new JTextField(); 
		textField.setColumns(15);
		textField.setBounds(159, 62, 185, 20);
		panel_121.add(textField);
		
		JLabel label_11 = new JLabel("URI:");
		label_11.setBounds(100, 65, 133, 14);
		panel_121.add(label_11);
		
		textField_1 = new JTextField();
		textField_1.setColumns(15);
		textField_1.setBounds(159, 110, 185, 20);
		panel_121.add(textField_1);
		
		JLabel lblChooseTheClassification = new JLabel("Chose the classification method");
		lblChooseTheClassification.setBounds(45, 200, 212, 20);
		panel_121.add(lblChooseTheClassification);
		
		
		final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
			
			JRadioButton radio_BNC = new JRadioButton("BNC");
			radio_BNC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					radio_value = "bnc"; 
					display.append("[" + dateFormat.format(new Date()) + "] Selected choice: BNC \n");
					System.out.println(radio_value);
				}
			});
			
			radio_BNC.setToolTipText("Naive Bayesian Classifier");
			radio_BNC.setBounds(250, 200, 59, 20);
			panel_121.add(radio_BNC);
			 
			JRadioButton radio_SVM = new JRadioButton("SVM");
			radio_SVM.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					radio_value = "svm"; 
					display.append("[" + dateFormat.format(new Date()) + "] Selected choice: SVM \n");
					System.out.println(radio_value);
				}
			});
			
			radio_SVM.setToolTipText("Support Vector Machine");
			radio_SVM.setBounds(320, 200, 70, 20);
			panel_121.add(radio_SVM);
			
			JRadioButton radio_DT = new JRadioButton("Decision Trees");
			radio_DT.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					radio_value = "dt"; 
					display.append("[" + dateFormat.format(new Date()) + "] Selected choice: Decisoin Trees \n");
					System.out.println(radio_value);
				}
			});
			
			radio_DT.setToolTipText("Decision Trees: J48");
			radio_DT.setBounds(390, 200, 180, 20);
			panel_121.add(radio_DT);
			
			ButtonGroup group = new ButtonGroup();
		    group.add(radio_BNC);
		    group.add(radio_DT);
		    group.add(radio_SVM); 
			
		 
		
		JLabel label_2 = new JLabel("Chose which social network(s) you want to interlink: \t \t");
		label_2.setBounds(45, 113, 113, 14);
		panel_121.add(label_2);
		
		final JCheckBox checkBox = new JCheckBox("Facebook");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				socialNetwork_value = "fb";
				if(checkBox.isSelected())
					display.append("[" + dateFormat.format(new Date()) + "] Selected social network: Facebook \n");
				else
					display.append("[" + dateFormat.format(new Date()) + "] Unselected social network: Facebook \n");
				System.out.println(socialNetwork_value);
			}
		});
		
		checkBox.setBounds(44, 149, 97, 23);
		panel_121.add(checkBox);
		
		final JCheckBox checkBox_1 = new JCheckBox("Twitter");
		checkBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				socialNetwork_value = "tw";
				if(checkBox_1.isSelected())
					display.append("[" + dateFormat.format(new Date()) + "] Selected social network: Twitter \n");
				else
					display.append("[" + dateFormat.format(new Date()) + "] Unselected social network: Twitter \n");
				System.out.println(socialNetwork_value);
			}
		});
		
		checkBox_1.setBounds(144, 149, 97, 23);
		panel_121.add(checkBox_1);
		
		panel_1.add(panel_121);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\152060807319498508.png"));
		label_4.setBounds(20, 25, 28, 19);
		panel_1.add(label_4);
		
		final JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 819, 522);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder ( new TitledBorder ( new EtchedBorder (), "Local SPARQL query" ) );
		
		JLabel lblNewLabel_1 = new JLabel("Local SPARQL query");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_1.setBounds(30, 25, 223, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\query.png"));
		lblNewLabel_2.setBounds(10, 15, 46, 30);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblSparqlQuery = new JLabel("SPARQL query");
		lblSparqlQuery.setBounds(40, 55, 570, 14);
		panel_2.add(lblSparqlQuery);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBounds(0, 80, 570, 153);
		panel_18.setBorder ( new TitledBorder ( new EtchedBorder (), "Enter a query" ) );
		final JTextArea textArea_1 = new JTextArea (7,48);
		
		textArea_1.setEditable ( true ); // set textArea non-editable
	    JScrollPane scroll15 = new JScrollPane ( textArea_1 );
	    scroll15.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    panel_18.add(scroll15);
	    
	  
	     
		panel_2.add(panel_18);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(40, 283, 46, 14);
		panel_2.add(lblResult);
		
		
		JPanel panel_17 = new JPanel();
		panel_17.setBounds(0, 316, 570, 195);
		panel_17.setBorder ( new TitledBorder ( new EtchedBorder (), "Resutlts Console" ) );
		final JTextArea textArea_2 = new JTextArea (10,48);
		
		textArea_2.setEditable ( false ); // set textArea non-editable
	    JScrollPane scroll14 = new JScrollPane ( textArea_2 );
	    scroll14.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    panel_17.add(scroll14);
		panel_2.add(panel_17);
		 
		JButton btnRun = new JButton("Run");
		btnRun.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\start.png"));
		btnRun.setBounds(462, 244, 89, 23);
		panel_2.add(btnRun);
		
		
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GraphDB gdb = new GraphDB();
				String result;
				try {
					result = gdb.query(textArea_1.getText().replace("\n", " "));
					
					textArea_2.setText(result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 819, 522);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Wikidata Metaphacts query ");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_3.setBounds(47, 11, 281, 14);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\metaphacts$.png"));
		lblNewLabel_4.setBounds(23, 0, 46, 27);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblEntityNameauthor = new JLabel("Entity name (Author)");
		lblEntityNameauthor.setBounds(43, 58, 135, 14);
		panel_3.add(lblEntityNameauthor);
		
		textField_2 = new JTextField();
		textField_2.setBounds(156, 55, 193, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnRun_1 = new JButton("Run");
		btnRun_1.setBounds(256, 97, 89, 23);
		panel_3.add(btnRun_1);
		
		JLabel lblResultNtriples = new JLabel("Result - N-Triples");
		lblResultNtriples.setBounds(47, 133, 107, 14);
		panel_3.add(lblResultNtriples);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setWrapStyleWord(true);
		textArea_3.setBounds(47, 158, 504, 244);
		panel_3.add(textArea_3);
		
		final JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 819, 522);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblLoadExistingSystem = new JLabel("Load existing system KG(s) using the panel below");
		lblLoadExistingSystem.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLoadExistingSystem.setBounds(45, 25, 478, 14);
		panel_4.add(lblLoadExistingSystem);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\skh2.png"));
		lblNewLabel_5.setBounds(10, 20, 35, 25);
		panel_4.add(lblNewLabel_5);
		
		JLabel lblGivenEntityName = new JLabel("Given entity name: ");
		lblGivenEntityName.setBounds(38, 65, 133, 14);
		panel_4.add(lblGivenEntityName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(152, 64, 196, 20);
		panel_4.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnRun_2 = new JButton("Run");
		btnRun_2.setBounds(259, 95, 89, 23);
		panel_4.add(btnRun_2);
		
		 
		final JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 819, 550);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setBorder ( new TitledBorder ( new EtchedBorder (), "KG embedding" ) );
		
		JPanel panel_123 = new JPanel();
		panel_123.setBounds(0, 85, 570, 100);
		panel_123.setBorder ( new TitledBorder ( new EtchedBorder (), "Information" ) );
		 
		
		JLabel lblEmbeddingExistingOnline = new JLabel("Embedding existing online KG(s) - Wikidata Metaphacts available");
		lblEmbeddingExistingOnline.setFont(new Font("Dialog", Font.BOLD, 13));
		lblEmbeddingExistingOnline.setBounds(45, 25, 478, 14);
		panel_5.add(lblEmbeddingExistingOnline);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\kge2.png"));
		lblNewLabel_6.setBounds(20, 25, 35, 25);
		panel_5.add(lblNewLabel_6);
		
		JLabel lblGivenEntityName_1 = new JLabel("Given entity name:");
		lblGivenEntityName_1.setBounds(38, 65, 133, 14);
		panel_123.add(lblGivenEntityName_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(170, 64, 188, 20);
		panel_123.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(256, 95, 89, 23);
		panel_123.add(btnStart);
		
		panel_5.add(panel_123);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(0, 316, 570, 195);
		panel_15.setBorder ( new TitledBorder ( new EtchedBorder (), "Resutlts Console" ) );
		final JTextArea display3 = new JTextArea (10,48);
		
	    display3.setEditable ( false ); // set textArea non-editable
	    JScrollPane scroll11 = new JScrollPane ( display3 );
	    scroll11.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    panel_15.add(scroll11);
		panel_5.add(panel_15);
		
		
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 316, 570, 195);
		panel_6.setBorder ( new TitledBorder ( new EtchedBorder (), "Resutlts Console" ) );
		
		
	    display.setEditable ( false ); // set textArea non-editable
	    JScrollPane scroll = new JScrollPane ( display );
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    panel_6.add(scroll);
		panel_1.add(panel_6);
		
		
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(0, 316, 570, 195);
		panel_7.setBorder ( new TitledBorder ( new EtchedBorder (), "Resutlts Console" ) );
		final JTextArea display2 = new JTextArea (10,48);
		
	    display.setEditable ( false ); // set textArea non-editable
	    JScrollPane scroll2 = new JScrollPane ( display2 );
	    scroll2.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    panel_7.add(scroll2);
		panel_4.add(panel_7);
		 
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setToolTipText("Save");
		lblNewLabel_12.setBounds(539, 269, 59, 74);
		panel_5.add(lblNewLabel_12);
		lblNewLabel_12.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\save.png"));
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setToolTipText("Clear");
		lblNewLabel_13.setBounds(500, 269, 59, 74);
		panel_5.add(lblNewLabel_13);
		lblNewLabel_13.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\throw.png"));
		
		
		
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setToolTipText("Save");
		lblNewLabel_7.setBounds(539, 269, 59, 74);
		panel_1.add(lblNewLabel_7);
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\save.png"));
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setToolTipText("Clear");
		lblNewLabel_14.setBounds(500, 269, 59, 74);
		panel_1.add(lblNewLabel_14);
		lblNewLabel_14.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\throw.png"));
		
		final JButton btnCreate = new JButton("Create");
		btnCreate.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\start.png"));
		btnCreate.setBounds(256, 247, 500, 23);
		panel_121.add(btnCreate);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\logohead.png"));
		lblNewLabel_8.setBounds(203, 221, 225, 51);
		panel_121.add(lblNewLabel_8);
		
		btnCreate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				GetDefinedGSProfile gdgsp = new GetDefinedGSProfile();
				try {
					
					btnCreate.setText("Creating...");
					display.append("[" + dateFormat.format(new Date()) + "] Crawling successfully starts \n");
					gsa = gdgsp.crawl(textField_1.getText().toString(), textField.getText().toString(), radio_value);
				
					Formats f = new Formats();
					fText = f.object_to_ntriples(gsa);
					display.setText(fText);
					btnCreate.setText("Create");
					JOptionPane.showMessageDialog(null, "This instance will be added to the training set to enhance it's performance!");
					display.append("[" + dateFormat.format(new Date()) + "] Crawling successfully finished \n"); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});		
		
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				BufferedWriter bw = null;
				FileWriter fw = null;

				try {
					
					@SuppressWarnings("resource")
					FileWriter file = new FileWriter("D:\\Knowledge_Graphs" + "\\Created_KGs\\" + gsa.get_author_name().replace(" ", "_") + ".nt");
					file.write(fText);
					file.flush();
					File file1 = new File("D:\\Knowledge_Graphs" + "\\Created_KGs\\Main_KG.nt");
					fw = new FileWriter(file1, true);
					bw = new BufferedWriter(fw);
					bw.write("\n" + fText);
					
					JOptionPane.showMessageDialog(null, "Successfully saved");
					
				} catch (IOException e) {
					e.printStackTrace();
				}finally {

					try {

						if (bw != null)
							bw.close();

						if (fw != null)
							fw.close();

					} catch (IOException ex) {

						ex.printStackTrace();

					}
				}
			}
		});
		
		
	    
		
		File fileRoot = new File("D:/Knowledge_Graphs");
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);
		
		final JPanel panel_8 = new JPanel();
		panel_8.setBounds(568, 0, 251, 511);
		panel_8.setBorder ( new TitledBorder ( new EtchedBorder (), "KG Explorer" ) );
		
		final JTree tree = new JTree(treeModel);
		tree.setShowsRootHandles(true);
		tree.setBounds(568, 0, 251, 600);
		tree.setVisibleRowCount(24);
		
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        Icon closedIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\folder_close.png");
        Icon openIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\folder_open.png");
        Icon leafIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\document.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
        
		JScrollPane scroll3 = new JScrollPane ( tree );
		scroll3.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panel_8.add(scroll3);
		panel_1.add(panel_8); 
			
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(568, 0, 251, 600);
		panel_9.setBorder ( new TitledBorder ( new EtchedBorder (), "KG Explorer" ) );
		final JTree tree2 = new JTree(treeModel);
		tree2.setShowsRootHandles(true);
		tree2.setBounds(568, 0, 251, 600);
		tree2.setVisibleRowCount(24);
		
		JScrollPane scroll4 = new JScrollPane ( tree2 );
		scroll4.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panel_9.add(scroll4);
		panel_2.add(panel_9); 

		JPanel panel_13 = new JPanel();
		panel_13.setBounds(568, 0, 251, 511);
		panel_13.setBorder ( new TitledBorder ( new EtchedBorder (), "KG Explorer" ) );
		final JTree tree3 = new JTree(treeModel);
		tree3.setShowsRootHandles(true);
		tree3.setBounds(568, 0, 251, 511);
		tree3.setVisibleRowCount(24);
		
		JScrollPane scroll5 = new JScrollPane ( tree3 );
		scroll5.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panel_13.add(scroll5);
		panel_4.add(panel_13); 
		
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(568, 0, 277, 498);
		panel_14.setBorder ( new TitledBorder ( new EtchedBorder (), "KG Explorer" ) );
		final JTree tree4 = new JTree(treeModel);
		tree4.setShowsRootHandles(true);
		tree4.setBounds(568, 0, 251, 511);
		tree4.setVisibleRowCount(24);
		
		JScrollPane scroll6 = new JScrollPane ( tree4 );
		scroll6.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panel_14.add(scroll6);
		panel_5.add(panel_14); 
		
		DefaultTreeCellRenderer _renderer = (DefaultTreeCellRenderer) tree2.getCellRenderer();
        Icon _closedIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\folder_close.png");
        Icon _openIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\folder_open.png");
        Icon _leafIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\document.png");
        _renderer.setClosedIcon(_closedIcon);
        _renderer.setOpenIcon(_openIcon);
        _renderer.setLeafIcon(_leafIcon);
        
        DefaultTreeCellRenderer __renderer = (DefaultTreeCellRenderer) tree3.getCellRenderer();
        Icon __closedIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\folder_close.png");
        Icon __openIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\folder_open.png");
        Icon __leafIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\document.png");
        __renderer.setClosedIcon(__closedIcon);
        __renderer.setOpenIcon(__openIcon);
        __renderer.setLeafIcon(__leafIcon);
        
        DefaultTreeCellRenderer ___renderer = (DefaultTreeCellRenderer) tree4.getCellRenderer();
        Icon ___closedIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\folder_close.png");
        Icon ___openIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\folder_open.png");
        Icon ___leafIcon = new ImageIcon("C:\\Users\\husseiny.hazimeh\\Downloads\\document.png");
        ___renderer.setClosedIcon(___closedIcon);
        ___renderer.setOpenIcon(___openIcon);
        ___renderer.setLeafIcon(___leafIcon);
		
		//panel_1.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );
		
		
        lblNewLabel_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					
					display.setText("");
				}
			});
		
		lblNewLabel_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					
				treeModel.reload();
				 
					 
					
					tree.setModel(treeModel);
					display.setText("");
				 
				}
			});
        
		CreateChildNodes ccn = 
                new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
        
        tree.addMouseListener(new MouseAdapter() {
            @SuppressWarnings("resource")
			public void mouseClicked(MouseEvent me) {
            	 TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
            	 //DefaultMutableTreeNode _root = (DefaultMutableTreeNode)treeModel.getRoot();
            	  
            	 //treeModel.reload(_root);
            	    if (tp != null)
            	    {
            	    	try
            	    	{
            	    		String path = "D:/" + tp.toString().replace(",","/").replace("[", "").replace("]", "").replace(" ", "");
            	    	InputStream is = new FileInputStream(path); 
            	    	BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
            	    	String line = buf.readLine(); 
            	    	StringBuilder sb = new StringBuilder(); 
            	    	while(line != null)
            	    	{ 
            	    		sb.append(line).append("\n"); 
            	    		line = buf.readLine(); 
            	    	} 
            	    	String fileAsString = sb.toString(); 
            	    	
            	    	display.setText(fileAsString);
            	    	}catch(IOException e){}
            	    }
            }
          });
        
		mntmKnowledgeGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_2.hide();
				panel_3.hide();
				panel_4.hide();
				panel_5.hide();
				panel_1.show();
				System.out.println("Action selected: New Knowledge Graph");
				frmGraphscholar.getContentPane().removeAll();
			    frmGraphscholar.getContentPane().add(panel_1, BorderLayout.CENTER);
			    frmGraphscholar.getContentPane().doLayout();
			    tree.expandRow(0);
			    frmGraphscholar.getContentPane().update(frmGraphscholar.getGraphics());
			}
		});
		
		mntmLocalSparqlQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 panel_1.hide();
				 panel_3.hide();
				 panel_4.hide();
				 panel_5.hide();
				 panel_2.show();
				System.out.println("Action selected: New Local SPARQL Query");
				frmGraphscholar.getContentPane().removeAll();
				 frmGraphscholar.getContentPane().add(panel_2, BorderLayout.CENTER);
			    frmGraphscholar.getContentPane().doLayout();
			    frmGraphscholar.getContentPane().update(frmGraphscholar.getGraphics());
			}
		});
		
		mntmMetaphactsQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.hide();
				panel_2.hide();
				panel_4.hide();
				panel_5.hide();
				panel_3.show();
				System.out.println("Action selected: New Wikidata Query");
				frmGraphscholar.getContentPane().removeAll();
			    frmGraphscholar.getContentPane().add(panel_3, BorderLayout.CENTER);
			    frmGraphscholar.getContentPane().doLayout();
			    frmGraphscholar.getContentPane().update(frmGraphscholar.getGraphics());
			}
		});
		
		mntmSystemKnowledgeGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.hide();
				panel_2.hide();
				panel_3.hide();
				panel_5.hide();
				panel_4.show();
				System.out.println("Action selected: New Knowledge Graph");
				frmGraphscholar.getContentPane().removeAll();
			    frmGraphscholar.getContentPane().add(panel_4, BorderLayout.CENTER);
			    frmGraphscholar.getContentPane().doLayout();
			    frmGraphscholar.getContentPane().update(frmGraphscholar.getGraphics());
			}
		});
		
		mntmKnowledgeGraphEmbedding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.hide();
				panel_2.hide();
				panel_3.hide();
				panel_4.hide();
				panel_5.show();
				System.out.println("Action selected: New Knowledge Graph Embedding");
				frmGraphscholar.getContentPane().removeAll();
			    frmGraphscholar.getContentPane().add(panel_5, BorderLayout.CENTER);
			    frmGraphscholar.getContentPane().doLayout();
			    frmGraphscholar.getContentPane().update(frmGraphscholar.getGraphics());
			}
		});
		
		
		
		panel_1.hide();
	}
	
 
}


class FileNode {

    private File file;

    public FileNode(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        String name = file.getName();
        if (name.equals("")) {
            return file.getAbsolutePath();
        } else {
            return name;
        }
    }
}


class CreateChildNodes implements Runnable {

    private DefaultMutableTreeNode root;

    private File fileRoot;

    public CreateChildNodes(File fileRoot, 
            DefaultMutableTreeNode root) {
        this.fileRoot = fileRoot;
        this.root = root;
    }

    @Override
    public void run() {
        createChildren(fileRoot, root);
    }

    private void createChildren(File fileRoot, 
            DefaultMutableTreeNode node) {
        File[] files = fileRoot.listFiles();
        if (files == null) return;

        for (File file : files) {
            DefaultMutableTreeNode childNode = 
                    new DefaultMutableTreeNode(new FileNode(file));
            node.add(childNode);
            if (file.isDirectory()) {
                createChildren(file, childNode);
            }
        }
    }
    
    

}