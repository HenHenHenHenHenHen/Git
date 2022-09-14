import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import git.Blob;
import git.Index;


class MainTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Path p = Paths.get("test.txt");
        try {
            Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public void testIndex() throws IOException {
		//checks if index file is created
		Index i = new Index();
		i.start();
		File indexFile = new File ("./Testing/index.txt");
		assertTrue (indexFile.exists());
	
		//Tests if objects folder is created
		File objectsFolder = new File ("./Testing/objects");
		assertTrue (objectsFolder.exists());
		
		//Tests if add works
		Path p = Paths.get("test2.txt");
        try {
            Files.writeString(p, "testing add blob", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

		i.add("test2.txt");
		Path indexPath = Path.of("./Testing/index.txt");
		String indexContents = Files.readString(indexPath);
		System.out.println ("indexContents " + indexContents);
		

		assertTrue (indexContents.contains("test2.txt : 177bf6896e0182bd0fa0147ce38c00942a7379eb"));
		
		//Test if remove works
		File sha1BlobFile = new File ("./Testing/objects/177bf6896e0182bd0fa0147ce38c00942a7379eb.txt");
		assertTrue (sha1BlobFile.exists());
		
		i.remove("test2.txt");
		Path indexPath2 = Path.of("./Testing/index.txt");
		String indexContents2 = Files.readString(indexPath2);
		
		assertTrue (!indexContents2.contains("test2.txt : 177bf6896e0182bd0fa0147ce38c00942a7379eb"));
	
		
	}
	@Test
	public void testsBlob() throws IOException
	{
		Blob b = new Blob ("test.txt");
		File sha1File = new File ("./Testing/objects/c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt");
		assertTrue (sha1File.exists());
		//assertTrue (readFile ("c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt",StandardCharsets.ISO_8859_1).equals(readFile ("test.txt",StandardCharsets.ISO_8859_1)));
		
		Path originalPath = Path.of("test.txt");
		String originalContents = Files.readString(originalPath);
		
		Path newPath = Path.of("./Testing/objects/c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt");
		String newContents = Files.readString(newPath);
		
		assertTrue (originalContents.equals (newContents));
	}
	
		
		//Blob
		//1: checks file content
		//2: checks file location/name
		
		//Index
		//1: checks "index" and "objects"
		//2: Add Blob to index file
		//3: Removes Blob from index file
		//4: multiple adds and at least 1 remove
		

		
		
		//fail("Not yet implemented");
	}

	

