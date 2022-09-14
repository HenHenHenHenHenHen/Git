import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import git.Blob;

class JUnitTester {

	@Test
	static void setUpBefore ()
	{
		Path p = Paths.get("test.txt");
        try {
            Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	static void tearDownAter()
	{
		
	}
	
	@Test
	void test() throws IOException {
		
		
		Blob b = new Blob ("test.txt");
		File sha1File = new File ("c3499c2729730a7f807efb8676a92dcb6f8a3f8f");
		assertTrue (sha1File.exists());
		//assertTrue (b.getSHAString().equals("c3499c2729730a7f807efb8676a92dcb6f8a3f8f"));
//		if (!sha1File.exists())
//			fail("Blob file not created :(");
	}

}
