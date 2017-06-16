package net.historm.tk.encode;

import net.historm.tk.encode.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MD5UtilTest {

	@Test
	public void testMD5() {
		String result1 = MD5Util.md5("admin@123");
		String result2 = MD5Util.md5("");
		String result3 = MD5Util.md5(null);
		Assert.assertEquals(result1, "e6e061838856bf47e1de730719fb2609");
		Assert.assertEquals(result2, "d41d8cd98f00b204e9800998ecf8427e");
		Assert.assertNull(result3);
	}

}
