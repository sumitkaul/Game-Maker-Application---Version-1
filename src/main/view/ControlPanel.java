package main.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.controller.Controls;
import main.model.Constants;

public class ControlPanel extends Board {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controls controls;
	private ListPanel variousLists;
	private JButton layout;
	final GridLayout gl1;
	final GridLayout gl2;
	final GridLayout gl3;
	final GridLayout gl4;
	final GridLayout fl;

	private static ControlPanel controlPanel; 
	public static ControlPanel getInstance() {
		if(controlPanel==null)
			controlPanel = new ControlPanel();
		return controlPanel;
	}

	public ControlPanel() {
		super(new BorderLayout());
		Constants.CONTROLPANEL_WIDTH=(int)(0.5*Constants.WINDOW_WIDTH);
		Constants.CONTROLPANEL_HEIGHT=(int)(0.95*Constants.WINDOW_HEIGHT);
		super.setSize(Constants.CONTROLPANEL_WIDTH, Constants.CONTROLPANEL_HEIGHT);

		layout = new JButton("LAYOUT");
		controls = Controls.getInstance();
		variousLists = ListPanel.getInstance();
		super.add(variousLists.getPanel(), BorderLayout.CENTER);
		super.add(controls.getPanel(), BorderLayout.NORTH);
		super.add(layout,BorderLayout.SOUTH);
		layout.setVisible(false);
		gl1 = new GridLayout(4,1);
		gl2 = new GridLayout(2,5);
		gl3 = new GridLayout(1,4);
		gl4 = new GridLayout(4,1);
		fl = new GridLayout(4,1);

		layout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(variousLists.getPanel().getLayout()!=gl1 && variousLists.getPanel().getLayout()!=gl2 && variousLists.getPanel().getLayout()!=fl){
					variousLists.switchPanel(variousLists.getPanel(), gl1);
				}
				if(controls.getPanel().getLayout()!=gl3 && controls.getPanel().getLayout()!=gl4 && controls.getPanel().getLayout()!=fl){
					controls.switchPanel(controls.getPanel(), gl3);
				}

				else if(variousLists.getPanel().getLayout()==gl1 && controls.getPanel().getLayout()==gl3){
					variousLists.switchPanel(variousLists.getPanel(), gl2);
					controls.switchPanel(controls.getPanel(), gl4);
				}
				else if(variousLists.getPanel().getLayout()==gl2 && controls.getPanel().getLayout()==gl4){
					variousLists.switchPanel(variousLists.getPanel(),fl);
					controls.switchPanel(controls.getPanel(), fl);
				}
				else if(variousLists.getPanel().getLayout()==fl && controls.getPanel().getLayout()==fl){
					variousLists.switchPanel(variousLists.getPanel(),gl1);
					controls.switchPanel(controls.getPanel(), gl3);
				}


			}
		});

	}

}
