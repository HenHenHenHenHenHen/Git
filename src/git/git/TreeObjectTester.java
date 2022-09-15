package git;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TreeObjectTester {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList <String> bt= new ArrayList <String> ();
		bt.add("blob : 22395720752039");
		bt.add("tree: 29847937030");
		bt.add("blob : 7937459834");
		
		TreeObject tree = new TreeObject (bt);
		

	}

}
