import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JSplitPane;


public class JOptionPane extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOptionPane frame = new JOptionPane();
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
	public JOptionPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDebugOptions = DefaultComponentFactory.getInstance().createLabel("Debug Options");
		lblDebugOptions.setBounds(10, 11, 92, 14);
		contentPane.add(lblDebugOptions);
		
		JCheckBox chckbxShowHitboxesf = new JCheckBox("Show Hitboxes (F3)");
		chckbxShowHitboxesf.setBounds(10, 35, 119, 23);
		contentPane.add(chckbxShowHitboxesf);
		
		JCheckBox chckbxInvincibile = new JCheckBox("Invincibile");
		chckbxInvincibile.setBounds(10, 61, 97, 23);
		contentPane.add(chckbxInvincibile);
		
		JCheckBox chckbxAllWeaponsUnlocked = new JCheckBox("All Weapons Unlocked");
		chckbxAllWeaponsUnlocked.setBounds(10, 87, 131, 23);
		contentPane.add(chckbxAllWeaponsUnlocked);
	}
}
