package cn.rxsi.comtest.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ByteUtilsTest {

	@Test
	public void testHexStr2Byte() {

	ByteUtils byteUtils=new ByteUtils();
	//byteUtils.hexStr2Byte("11");
	System.out.println(byteUtils.hexStr2Byte("11"));
	
	}

	@Test
	public void testByteArrayToHexString() {

	ByteUtils byteUtils=new ByteUtils();

	}

	@Test
	public void testByteToHex() {
		fail("Not yet implemented");
	}

}
