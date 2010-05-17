import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class GraficoPuma7 {
	static int fx=1,fy=25;//milimetros
	static int lastX=0;
	static FileOutputStream fw;
	public static void main(String[] args) {
		
		try {
			fw = new FileOutputStream(new File("grafico"));
			
			w(0x86);//Start of Graphic File
			
			String arq = FileUtils.readFileToString(new File("graf.grb"), "ISO-8859-1");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void yCodificado(int y) {
		//antes estava y*=8
		// e fy valia 3
		y*=fy;
		int yR = y & 0x000000FF;
		int yL = y & 0x0000FF00;
		yL = yL>>8;
		w(yL);
		w(yR);
	}

	private static void novaLinha() {
		w(0xFF);//FF
		w(0x0D);//FF
	}

	/**
	 * 
	 * @param x em mm
	 */
	private static void xCodificado(int x) {
		x*=83;//83*0.03 = 2.49
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
	
	private static void w(int i){
		try {
			fw.write(new byte[]{(byte)i},0,1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.write(new byte[]{(byte)i},0,1);// \n
	}
}
