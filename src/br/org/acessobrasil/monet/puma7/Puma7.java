package br.org.acessobrasil.monet.puma7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Classe para converter arquivos para serem impressos na Puma VII
 * @author Fabio Issamu Oshiro
 *
 */
public class Puma7 {
	private static int fx = 83;// medida alema mm/0,03 vai dar 2,49
	private static int fy = 25; //milimetros/10 ou seja 2,5
	static FileOutputStream fw;
	
	/**
	 * Converte um arquivo do braille facil impresso no HD para o bin&aacute;rio da puma 7
	 * @param origem arquivo do braille facil impresso no HD
	 * @param destino arquivo binario de destino, n&atilde;o pergunta se deseja sobreescrever
	 * @throws IOException caso ocorra algum erro, file not found, etc
	 */
	public static void converterTxtImpresso(File origem, File destino) throws IOException{
		
	}
	
	/**
	 * Converte um gbr para o bin&aacute;rio da Puma VII
	 * @param origem arquivo *.grb 
	 * @param destino arquivo sem extens&atilde;o.
	 * 	n&atilde;o pergunta se deseja sobreescrever
	 * @throws IOException caso o arquivo de origem nao exista                          
	 */
	public static void converterGrb(File origem, File destino) throws IOException{
		if(!origem.exists()){
			throw new FileNotFoundException("arquivo " + origem.getName() + " inexistente.");
		}
		
		fw = new FileOutputStream(destino);
		
		w(0x86);//Start of Graphic File
		
		String arq = FileUtils.readFileToString(origem, "ISO-8859-1");
		String linhas[]=arq.split("\n");
		int y=0;
		for (int i = 0; i < linhas.length; i++) {
			boolean iniciadoY=false;
			int x = 0;
			for(int c=0;c<linhas[i].length();c++){
				if(linhas[i].charAt(c)=='*'){
					if(!iniciadoY){//inicia o Y FF 0D YY YY
						iniciadoY=true;
						novaLinha();
						yCodificado(y);
						y=0;
					}
					xCodificado(x);
					x=0;
				}
				x+=1;
			}
			y+=1;
		}
		novaLinha();
		w(0xFF);//FF
		w(0x84);//FF
		fw.close();
	}
	
	private static void yCodificado(int y) throws IOException {
		//antes estava y*=8
		// e fy valia 3
		y*=fy;//multiplicador de Y 2,5mm atualmente
		int yR = y & 0x000000FF;//mascara para pegar somente os bytes da direita
		int yL = y & 0x0000FF00;//mascara para pegar somente os bytes da esquerda
		yL = yL>>8;
		w(yL);//escreve o byte da esquerda
		w(yR);//escreve o byte da direita
	}

	private static void novaLinha() throws IOException {
		w(0xFF);//FF
		w(0x0D);//FF
	}

	/**
	 * 
	 * @param x em mm
	 * @throws IOException 
	 */
	private static void xCodificado(int x) throws IOException {
		x*=fx;//83*0.03 = 2.49
		int xR = x%100;
		int xL = x/100;
		int res=0;
		res+=xL%10;
		res+=(xL/10)*16;
		w(res);
		res=xR%10;
		res+=(xR/10)*16;
		w(res);
	}
	
	private static void w(int i) throws IOException{
		fw.write(new byte[]{(byte)i},0,1);
	}
}
