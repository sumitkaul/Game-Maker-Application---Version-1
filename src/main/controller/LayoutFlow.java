package main.controller;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class LayoutFlow implements SwitchLayout {

	public JPanel panel;

	public LayoutFlow(JPanel upanel) {
		this.panel=upanel;
	}

	@Override
	public void execute(JPanel upanel, BoxLayout uboxLayout) {
	}

	@Override
	public void execute(JPanel upanel, FlowLayout uflowLayout) {
		upanel.setLayout(uflowLayout);
		upanel.revalidate();
	}

	@Override
	public void execute(JPanel upanel, GridLayout ugridLayout) {
	}

}

