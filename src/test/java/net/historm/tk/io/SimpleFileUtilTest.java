package net.historm.tk.io;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleFileUtilTest {

	@Test
	public void testWriteAndRead() {
		String testFile = "test_file.txt";
		String content = "i am test content";
		boolean writeResult = SimpleFileUtil.writeFile(testFile, content);
		Assert.assertTrue(writeResult);
		String txt = SimpleFileUtil.readFile(testFile);
		Assert.assertEquals(content, txt);
		new File(testFile).delete();
	}
}
