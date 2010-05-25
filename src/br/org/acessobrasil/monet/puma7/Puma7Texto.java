package br.org.acessobrasil.monet.puma7;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;

/**
 * Classe para tratar um arquivo impresso no HD e transform&aacute;-lo em bin&aacute;rio da Puma
 * @author Fabio Issamu Oshiro
 *
 */
public class Puma7Texto {
	
	private TabelaConversaoTexto tabelaConversao = new TabelaConversaoTexto();
	
	public void converter(File origem, File destino) throws Exception{
		FileOutputStream fw = null;
		try {
			String arq = FileUtils.readFileToString(origem, "ISO-8859-1");
			fw = new FileOutputStream(destino);
			for(int i=0;i<arq.length();i++){
				char c = arq.charAt(i);
				if(c=='\r') continue;//nao existe \r
				byte b = tabelaConversao.converter(Character.toLowerCase(c));
				fw.write(new byte[]{b}, 0, 1);
			}
		}finally{
			try{
				fw.close();
			}catch(Exception e){
				
			}
		}
	}

}
