package br.org.acessobrasil.monet.puma7ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.org.acessobrasil.monet.puma7.Puma7;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	private JButton btnGrafico;
	private JButton btnTexto;
	private JButton btnConfig;
	public MainFrame() {
		this.setTitle("Conversor de Arquivo para Puma VII");
		this.setLayout(new GridLayout(2, 2));
		
		btnGrafico = new JButton("Grafico *.grb");
		btnTexto = new JButton("Impresso do Braille Facil *.txt");
		btnConfig = new JButton("Configurar");
		
		btnConfig.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String strX = JOptionPane.showInputDialog(rootPane, "Espacamento X", Puma7.getFx());
				Puma7.setFx(Integer.parseInt(strX));
				String strY = JOptionPane.showInputDialog(rootPane, "Espacamento Y", Puma7.getFy());
				Puma7.setFy(Integer.parseInt(strY));
			}
		});
		
		btnGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					converterGrafico();
				}catch(Exception e){
					JOptionPane.showMessageDialog(MainFrame.this, "Erro: " + e.getMessage());
				}
			}
		});
		btnTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					converterTexto();
				}catch(Exception e){
					JOptionPane.showMessageDialog(MainFrame.this, "Erro: " + e.getMessage());
				}
			}
		});
		
		this.add(btnGrafico);
		this.add(btnTexto);
		this.add(btnConfig);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(400,300);
		
		this.setVisible(true);
		
	}
	private void converterTexto() throws Exception{
		JFileChooser fileChooser = new JFileChooser();
		int res = fileChooser.showOpenDialog(this);
		if(res==JFileChooser.APPROVE_OPTION){
			File origem = fileChooser.getSelectedFile();
			if(!origem.exists()){
				throw new Exception("Arquivo inexistente!");
			}
			//escolher arquivo de destino
			res = fileChooser.showSaveDialog(this);
			if(res== JFileChooser.APPROVE_OPTION){
				File destino = fileChooser.getSelectedFile();
				//TODO futuramente perguntar se deseja sobreescrever
				Puma7.converterTxtImpresso(origem, destino);
			}
		}
	}
	private void converterGrafico() throws Exception{
		JFileChooser fileChooser = new JFileChooser();
		int res = fileChooser.showOpenDialog(this);
		if(res==JFileChooser.APPROVE_OPTION){
			File origem = fileChooser.getSelectedFile();
			if(!origem.exists()){
				throw new Exception("Arquivo inexistente!");
			}
			//escolher arquivo de destino
			res = fileChooser.showSaveDialog(this);
			if(res== JFileChooser.APPROVE_OPTION){
				File destino = fileChooser.getSelectedFile();
				//TODO futuramente perguntar se deseja sobreescrever
				Puma7.converterGrb(origem, destino);
			}
		}
	}
	
}
