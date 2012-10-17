package main.controller;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class LayoutGrid implements SwitchLayout {

	public JPanel panel;

	public LayoutGrid(JPanel upanel) {
		this.panel=upanel;
	}

	@Override
	public void execute(JPanel panel, BoxLayout boxLayout) {
	}

	@Override
	public void execute(JPanel panel, FlowLayout flowLayout) {
	}

	@Override
	public void execute(JPanel upanel, GridLayout ugridLayout) {
		upanel.setLayout(ugridLayout);
		upanel.revalidate();
	}

}

