package main.controller;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public interface SwitchLayout {

	public void execute(JPanel panel, BoxLayout boxLayout);
	public void execute(JPanel panel, FlowLayout flowLayout);
	public void execute(JPanel panel, GridLayout gridLayout);
}