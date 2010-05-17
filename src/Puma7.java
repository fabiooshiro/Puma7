public class Puma7 {
	public static void main(String[] args) {
		byte p[]=new byte[7];
		p[0]=(byte)0x80;
		p[1]=1;
		p[2]=2;
		p[3]=4;
		p[4]=8;
		p[5]=16;
		p[6]=32;
		
		byte c;
		c = (byte)(p[1]|p[2]|p[3]|p[4]);//p
		System.out.write(new byte[]{c},0,1);		
		
		c = (byte)(p[1]|p[3]|p[6]);//u
		System.out.write(new byte[]{c},0,1);		
		
		c = (byte)(p[1]|p[3]|p[4]);//m
		System.out.write(new byte[]{c},0,1);
		
		c = (byte)(p[1]);//a
		System.out.write(new byte[]{c},0,1);	
		
		c = (byte)(p[0]);//<espaco>
		System.out.write(new byte[]{c},0,1);
		
		c = (byte)(p[3]|p[4]|p[5]|p[6]);//<numero>
		System.out.write(new byte[]{c},0,1);
		
		c = (byte)(p[1]|p[2]|p[4]|p[5]);//7 ou G
		System.out.write(new byte[]{c},0,1);
		
		System.out.write(new byte[]{(byte)0x81},0,1);// \n
		
		c = (byte)(p[1]|p[2]|p[3]|p[4]);//p
		System.out.write(new byte[]{c},0,1);		
		
		c = (byte)(p[1]|p[3]|p[6]);//u
		System.out.write(new byte[]{c},0,1);		
		
		c = (byte)(p[1]|p[3]|p[4]);//m
		System.out.write(new byte[]{c},0,1);
		
		c = (byte)(p[1]);//a
		System.out.write(new byte[]{c},0,1);	
		
		c = (byte)(p[0]);//<espaco>
		System.out.write(new byte[]{c},0,1);
		
		c = (byte)(p[3]|p[4]|p[5]|p[6]);//<numero>
		System.out.write(new byte[]{c},0,1);
		
		c = (byte)(p[1]|p[2]|p[4]|p[5]);//7 ou G
		System.out.write(new byte[]{c},0,1);
	}
}
