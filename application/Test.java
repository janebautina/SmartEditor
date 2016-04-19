package application;

public class Test {
	public static void main(String [] args){
		String text = "%one%%two%%%three%%%%";
		System.out.println(text.split("[one,two,three]")[3]);
	}
	public static int mystery(String s)
	{
	    char[] letters = s.toCharArray();
	    int x = 0;
	    for (int i = 0; i < letters.length; i++) {
	        if (letters[i] == ' ') {
	           letters[i] = '_';
	           x++;
	        }
	    }
	    return x;
	}
}
