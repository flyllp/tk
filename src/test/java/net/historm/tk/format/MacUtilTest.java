package net.historm.tk.format;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MacUtilTest {

	@Test
	public void testFormatStandard() {
		Assert.assertEquals("00661f78ae12", MacUtil.formatStandard("00:66:1f:78:ae:12"));
		Assert.assertEquals("00661f78ae12", MacUtil.formatStandard("00:66:1F:78:ae:12"));
		Assert.assertEquals("00661f78ae12", MacUtil.formatStandard("00:66:1F:78:AE:12"));
		Assert.assertEquals("00661f78ae12", MacUtil.formatStandard("00661f78ae12"));
		Assert.assertNull(MacUtil.formatStandard("00:66:1F:78:AG:12"));
		Assert.assertNull(MacUtil.formatStandard("00:66:1F:78:AE:12:77"));
		Assert.assertNull(MacUtil.formatStandard("00661f78ay12"));
		Assert.assertNull(MacUtil.formatStandard("00661f78ae123"));
	}

	@Test
	public void testFormatNormal() {
		Assert.assertEquals("00:66:1f:78:ae:12", MacUtil.formatNormal("00661f78ae12"));
		Assert.assertEquals("00:66:1f:78:ae:12", MacUtil.formatNormal("00:66:1F:78:ae:12"));
		Assert.assertNull(MacUtil.formatStandard("00:66:1F:78:AE:12:77"));
	}

	@Test
	public void testMacToLong() {
		Assert.assertEquals(438614666770L, MacUtil.macToLong("00:66:1f:78:ae:12"));
		Assert.assertEquals(-1L, MacUtil.macToLong("00:66:1f:78:ae:1k"));
	}

	@Test
	public void testLongToMac() {
		Assert.assertEquals("00000000000c", MacUtil.LongToMac(12L));
		Assert.assertEquals("00661f78ae12", MacUtil.LongToMac(438614666770L));
		Assert.assertNull(MacUtil.LongToMac(281474976710656L));
	}

}
