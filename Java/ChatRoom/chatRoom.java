import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class chatRoom extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private boolean isServer = false; //false for server side

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			chatRoom dialog = new chatRoom();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * choose to be server side or client side
	 */
	public chatRoom() {
		setBounds(100, 100, 215, 158);
		setTitle("Char Room");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{175, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		ButtonGroup buttonGroup = new ButtonGroup();
		{
			JRadioButton sradio = new JRadioButton("Be the Server side");
			sradio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					isServer = false;
				}
			});
			sradio.setSelected(true);
			buttonGroup.add(sradio);
			sradio.setHorizontalAlignment(SwingConstants.CENTER);
			sradio.setFont(new Font("新細明體", Font.BOLD, 15));
			GridBagConstraints gbc_sradio = new GridBagConstraints();
			gbc_sradio.fill = GridBagConstraints.BOTH;
			gbc_sradio.insets = new Insets(0, 0, 5, 0);
			gbc_sradio.gridx = 0;
			gbc_sradio.gridy = 0;
			contentPanel.add(sradio, gbc_sradio);
		}
		{
			JRadioButton cradio = new JRadioButton("Be the Client side");
			cradio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					isServer = true;
				}
			});
			buttonGroup.add(cradio);
			cradio.setHorizontalAlignment(SwingConstants.CENTER);
			cradio.setFont(new Font("新細明體", Font.BOLD, 15));
			GridBagConstraints gbc_cradio = new GridBagConstraints();
			gbc_cradio.fill = GridBagConstraints.BOTH;
			gbc_cradio.gridx = 0;
			gbc_cradio.gridy = 1;
			contentPanel.add(cradio, gbc_cradio);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(!isServer){
								Server s = new Server();	//new a server
								dispose();
							}
							else{
								Client c = new Client();	//new a client
								dispose();
							}
						} catch (Exception ex) {
							// TODO: handle exception
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
