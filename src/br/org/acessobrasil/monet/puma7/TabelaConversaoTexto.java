package br.org.acessobrasil.monet.puma7;


public class TabelaConversaoTexto {
	private byte p[]=new byte[7];
	public TabelaConversaoTexto() {
		initBytes();
	}
	
	private void initBytes(){
		p[0]=(byte)0x80;//espaco em branco, segundo o "Manual Puma VII" pg 49
		p[1]=1;
		p[2]=2;
		p[3]=4;
		p[4]=8;
		p[5]=16;
		p[6]=32;
	}
	
	public byte converter(char c) throws Exception{
		//TODO tratar as excessoes
		switch (c) {
		case ' ': return p[0];
		case 'a': return p[1];
		case 'b': return (byte) (p[1] | p[2]);
		case 'c': return (byte) (p[1] | p[4]);
		case 'd': return (byte) (p[1] | p[4] | p[5]);
		//case 'e': return (byte) (p[1] | p[5]);
		case 'e': return (byte) 0x91;//segundo o "Manual Puma VII" pg 49
		case 'f': return (byte) (p[1] | p[2] | p[4]);
		case 'g': return (byte) (p[1] | p[2] | p[4] | p[5]);
		//case 'h': return (byte) (p[1] | p[2] | p[5]);
		case 'h': return (byte) 0x93;//segundo o "Manual Puma VII" pg 49
		case 'i': return (byte) (p[1] | p[4]);
		case 'j': return (byte) (p[2] | p[4] | p[5]);
		case 'k': return (byte) (p[1] | p[3]);
		case 'l': return (byte) (p[1] | p[2] | p[3]);
		case 'm': return (byte) (p[1] | p[3] | p[4]);
		case 'n': return (byte) (p[1] | p[3] | p[4] | p[5]);
		case 'o': return (byte) (p[1] | p[3] | p[5]);
		case 'p': return (byte) (p[1] | p[2] | p[3] | p[4]);
		case 'q': return (byte) (p[1] | p[2] | p[3] | p[4] | p[5]);
		case 'r': return (byte) (p[1] | p[2] | p[3] | p[5]);
		case 's': return (byte) (p[2] | p[3] | p[4]);
		case 't': return (byte) (p[2] | p[3] | p[4] | p[5]);
		case 'u': return (byte) (p[1] | p[3] | p[6]);
		case 'v': return (byte) (p[1] | p[2] | p[3] | p[6]);
		case 'w': return (byte) (p[2] | p[4] | p[5] | p[6]);
		case 'x': return (byte) (p[1] | p[3] | p[4] | p[6]);
		case 'y': return (byte) (p[1] | p[3] | p[4] | p[5] | p[6]);
		case 'z': return (byte) (p[1] | p[3] | p[5] | p[6]);
		//Numeros
		case '0': return (byte) (p[3] | p[5] | p[6]);
		case '1': return p[2];
		case '2': return (byte) (p[2] | p[3]);
		case '3': return (byte) (p[2] | p[5]);
		case '4': return (byte) (p[2] | p[5] | p[6]);
		case '5': return (byte) (p[2] | p[6]);
		case '6': return (byte) (p[2] | p[3] | p[5]);
		case '7': return (byte) (p[2] | p[3] | p[5] | p[6]);
		case '8': return (byte) (p[2] | p[3] | p[6]);
		case '9': return (byte) (p[3] | p[5]);
		//outros
		case '!': return (byte) (p[2] | p[3] | p[4] | p[6]);
		case '"': return p[5];
		case '#': return (byte) (p[3] | p[4] | p[5] | p[6]);
		case '$': return (byte) (p[1] | p[2] | p[4] | p[6]);
		case '%': return (byte) (p[1] | p[4] | p[6]);
		case '&': return (byte) (p[1] | p[2] | p[3] | p[4] | p[6]);
		case '\'': return p[3];
		case '(': return (byte) (p[1] | p[2] | p[3] | p[5] | p[6]);
		case ')': return (byte) (p[2] | p[3] | p[4] | p[5] | p[6]);
		case '*': return (byte) (p[1] | p[6]);
		case '+': return (byte) (p[3] | p[4] | p[6]);
		case ',': return p[6];
		case '-': return (byte) (p[3] | p[6]);
		case '.': return (byte) (p[4] | p[6]);
		case '/': return (byte) (p[3] | p[4]);
		case ':': return (byte) (p[1] | p[5] | p[6]);
		case ';': return (byte) (p[5] | p[6]);
		case '<': return (byte) (p[1] | p[2] | p[6]);
		case '=': return (byte) (p[1] | p[2] | p[3] | p[4] | p[5] | p[6]);
		case '>': return (byte) (p[3] | p[4] | p[5]);
		case '?': return (byte) (p[1] | p[4] | p[5] | p[6]);
		case '@': return p[4];
		case '[': return (byte) (p[2] | p[4] | p[6]);
		case '\\': return (byte) (p[1] | p[2] | p[5] | p[6]);
		case ']': return (byte) (p[1] | p[2] | p[4] | p[5] | p[6]);
		case '^': return (byte) (p[4] | p[5]);
		case '_': return (byte) (p[4] | p[5] | p[6]);
		case '\n': return (byte) 0x81;
		case 0x0C: return (byte) 0x83;//Form Feed ou \f
		case 0x7F: return (byte) 0x7F;//Del, segundo a tabela ascii
		case 123: return (byte) 123;// { segundo a tabela ascii
		//case '~': return (byte) 0x81;//??
		
		default:
			throw new Exception("Character numero '" + (int)c + "' inexistente");
		}
	}
}
