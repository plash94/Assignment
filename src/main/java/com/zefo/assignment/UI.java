package com.zefo.assignment;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

//import org.elasticsearch.action.ActionListener;

public class UI {
	public static void main(String[] args) {
		final HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
		final ArrayList<String> arrBrand = new ArrayList<String>();
		final ArrayList<String> arrWaist = new ArrayList<String>();
		final ArrayList<String> arrColor = new ArrayList<String>();

		JFrame fr = new JFrame();
		JLabel la = new JLabel("Filter");

		JPanel pn = new JPanel();
		final JCheckBox w1 = new JCheckBox("28");
		w1.setMnemonic(KeyEvent.VK_C);

		final JCheckBox w2 = new JCheckBox("30");
		w2.setMnemonic(KeyEvent.VK_G);
		

		final JCheckBox w3 = new JCheckBox("32");
		w3.setMnemonic(KeyEvent.VK_G);

		final JCheckBox w4 = new JCheckBox("34");
		w4.setMnemonic(KeyEvent.VK_G);

		pn.add(w1);
		pn.add(w2);
		pn.add(w3);
		pn.add(w4);

		

		JPanel pn2 = new JPanel();
		final JCheckBox b1 = new JCheckBox("Wrangler");
		b1.setMnemonic(KeyEvent.VK_C);

		final JCheckBox b2 = new JCheckBox("Lee");
		b2.setMnemonic(KeyEvent.VK_G);
		

		final JCheckBox b3 = new JCheckBox("Levis");
		b3.setMnemonic(KeyEvent.VK_G);

		final JCheckBox b4 = new JCheckBox("Killer");
		b4.setMnemonic(KeyEvent.VK_G);

		pn2.add(b1);
		pn2.add(b2);
		pn2.add(b3);
		pn2.add(b4);

		JPanel pn3 = new JPanel();
		final JCheckBox c1 = new JCheckBox("blue");
		c1.setMnemonic(KeyEvent.VK_C);

		final JCheckBox c2 = new JCheckBox("black");
		c2.setMnemonic(KeyEvent.VK_G);
		// glassesButton.setSelected(true);

		final JCheckBox c3 = new JCheckBox("green");
		c3.setMnemonic(KeyEvent.VK_G);

		pn3.add(c1);
		pn3.add(c2);
		pn3.add(c3);
		fr.add(pn);
		fr.add(pn2);
		fr.add(pn3);

		JButton submit = new JButton("Filter");
		JPanel pn4 = new JPanel();
		pn4.add(submit);

		fr.add(pn4);

		fr.setLayout(new GridLayout(4, 4));

		fr.setVisible(true);
		fr.setSize(300, 500);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c1.isSelected()) {
					
					arrColor.add(c1.getText().toString());	
				}
				if(c2.isSelected()) {
					arrColor.add(c2.getText().toString());
					
				}
				if(c3.isSelected()) {
					arrColor.add(c3.getText().toString());
					
				}
				if (b1.isSelected()) {
					
						
				}
				if(b2.isSelected()) {
					arrBrand.add(b2.getText().toString());
					
				}
				if(b3.isSelected()) {
					arrBrand.add(b3.getText().toString());
					
				}
				if (b4.isSelected()) {
					
					arrBrand.add(b4.getText().toString());	
				}
				if(w1.isSelected()) {
					arrWaist.add(w1.getText().toString());
					
				}
				if(w2.isSelected()) {
					arrWaist.add(w2.getText().toString());
					
				}
				if(w3.isSelected()) {
					arrWaist.add(w3.getText().toString());
					
				}
				if(w4.isSelected()) {
					arrWaist.add(w4.getText().toString());
					
				}
				hash.put("color", arrColor);
				hash.put("waist", arrWaist);
				hash.put("brand", arrBrand);
				
				try {
					Filter callFilter = new Filter();
					String result = callFilter.searchQuery(hash);
					//System.out.println(result);
					Main ma = new Main();
					
					try {
						ma.parseResult(result);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				arrColor.clear();
				arrBrand.clear();
				arrWaist.clear();
	           	        hash.clear();
	            
			}
		});
	}

	public static void uiEntrance1(HashMap<String, ArrayList<String>> hash) {
		// TODO Auto-generated method stub

	}

}
