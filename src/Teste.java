
public class Teste {
	public static void main(String[] args) {
		int y = 0x0100;
		int yL = y & 0x0000FF00;
		yL = yL>>8;
		System.out.println(yL);
		
	}
}
