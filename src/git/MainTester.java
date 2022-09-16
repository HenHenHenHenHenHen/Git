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
        
        Path p2 = Paths.get("test2.txt");
        try {
            Files.writeString(p2, "testing add blob", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p3 = Paths.get("test3.txt");
        try {
            Files.writeString(p3, "addFirst\ntest3", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p4 = Paths.get("test4.txt");
        try {
            Files.writeString(p4, "addSecond", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p5 = Paths.get("test5.txt");
        try {
            Files.writeString(p5, "addThird", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        
	}
	//\n
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	
	@Test
	public void testIndex() throws IOException {
		//checks if index file is created
		
		Index i = new Index();
		
		//IMPORTANT: your i.start does not create the Testing folder, make sure to create a new Folder!
		i.start();
		File indexFile = new File ("./Testing/index.txt");
		assertTrue (indexFile.exists());
	
		//Tests if objects folder is created
		File objectsFolder = new File ("./Testing/objects");
		assertTrue (objectsFolder.exists());
		
		//Tests if add works
		

		i.add("test2.txt");
		Path indexPath = Path.of("./Testing/index.txt");
		String indexContents = Files.readString(indexPath);
		System.out.println ("indexContents " + indexContents);
		

		assertTrue (indexContents.contains("test2.txt : 177bf6896e0182bd0fa0147ce38c00942a7379eb"));
		
		
		File sha1BlobFile = new File ("./Testing/objects/177bf6896e0182bd0fa0147ce38c00942a7379eb.txt");
		assertTrue (sha1BlobFile.exists());
		
		//Test if remove works
		i.remove("test2.txt");
		String indexContents2 = Files.readString(indexPath);
		
		assertFalse (indexContents2.contains("test2.txt : 177bf6896e0182bd0fa0147ce38c00942a7379eb"));
		
		assertFalse (sha1BlobFile.exists());

	}
	@Test
	public void addAgain() throws IOException
	{
		
		//Tests adding and removing multiple times
		Index i = new Index();
		Path indexPath = Path.of("./Testing/index.txt");
		System.out.println ("made it to here");
		i.add("test3.txt");
		System.out.println ("made it to here too");
		String indexContents3 = Files.readString(indexPath);
		assertTrue (indexContents3.contains("test3.txt : 59f1beb4a7716a32fe5036062242b58d7f6c40da"));
		File test3sha1 = new File ("./Testing/objects/59f1beb4a7716a32fe5036062242b58d7f6c40da.txt");
		assertTrue (test3sha1.exists());
	}
	@Test
	public void addAdditional() throws IOException
	{
		Index i = new Index();
		Path indexPath = Path.of("./Testing/index.txt");
		i.add ("test4.txt");
		String indexContents4 = Files.readString(indexPath);
		assertTrue (indexContents4.contains("test4.txt : 299656d2de3bc5d4a3858c57a506833ba35991f6"));
		File test4sha1 = new File ("./Testing/objects/299656d2de3bc5d4a3858c57a506833ba35991f6.txt");
		assertTrue (test4sha1.exists());
	}
	@Test
	public void removeAgain() throws IOException
	{
		Index i = new Index();
		Path indexPath = Path.of("./Testing/index.txt");
		i.remove("test3.txt");
		String indexContents5 = Files.readString(indexPath);
		File test3sha1 = new File ("./Testging/objects/59f1beb4a7716a32fe5036062242b58d7f6c40da");
		assertFalse (indexContents5.contains("test3.txt : 59f1beb4a7716a32fe5036062242b58d7f6c40da"));
		assertFalse (test3sha1.exists());
	}
	
	@Test
	public void addYetAgain() throws IOException
	{
		Index i = new Index();
		Path indexPath = Path.of("./Testing/index.txt");
		i.add ("test5.txt");
		String indexContents6 = Files.readString(indexPath);
		File test5sha1 = new File ("./Testing/objects/08b6092e338cbe4046012586470495a4fa0f7379.txt");
		assertTrue (indexContents6.contains("test5.txt : 08b6092e338cbe4046012586470495a4fa0f7379"));
		assertTrue (test5sha1.exists());
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
	

	}

	

