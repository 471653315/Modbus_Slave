package cn.rxsi.comtest.util;


public class CalculateUtils {

	private String data;
	private String gnm;
	private String[] datas;

	CRCUtils crcUtils = new CRCUtils();

	public CalculateUtils(String data) {
		this.data = data;

	}

	public String GNM() {
		datas = data.split("-");
		// System.out.println(datas.length);
		/*for (int i = 0; i < datas.length; i++) {
			 System.out.println(datas[i]);
		}*/
		if(!datas[0].equals("01111110")&&datas.length<4) {
			return "数据结构出错";
		}

		gnm = datas[1];
		//System.out.println(gnm);
		switch (gnm) {
		case "02":
			return GNM_02();
		// return gnm;
		case "0f":
			return GNM_0f();
		// return gnm;
		case "03":
			return GNM_03();
		// return gnm;
		case "10":
			return GNM_10();
		// return gnm;
		default:
			return "未定义此功能码";

		}

	}

	private String GNM_10() {
		// TODO Auto-generated method stub
		if(datas.length<8) {
			return "10：写多个寄存器" + "\r\n" + "结构为：地址码(1B)+功能码(1B)+起始寄存器地址(2B)+寄存器个数(2B)+(数据个数)+(数据)";
		}
		
		String cString=data.substring(0,17);
		
		String [] aStrings=cString.split("-");
		
		String bString=aStrings[0];
		for(int i=1;i<aStrings.length;i++) {
			bString+=aStrings[i];
		}
		
		String aString = crcUtils.getCrc(bString.getBytes());

		return "Rx:" + data.substring(0, 17) + aString.substring(0, 2) + "-" + aString.substring(2, 4);
	}

	private String GNM_03() {
		// TODO Auto-generated method stub
		if (datas.length < 8) {
			return "03：读多个寄存器" + "\r\n" + "结构为：地址码(1B)+功能码(1B)+起始寄存器地址(2B)+寄存器个数(2B)";
		} else {
			String num = datas[5];
			//System.out.println(num);
			String aString = num.substring(0, 1);
			String bString = num.substring(1, 2);
			String back = "-00";
			int num2;
			int num3;

			num2 = Integer.parseInt(aString, 16) * 16 + Integer.parseInt(bString, 16);
			// System.out.println(num2);
			// System.out.println(Integer.toHexString(num2));
			num3 = num2 * 2;

			for (int i = 0; i < num3 - 1; i++) {
				back += "-00";
			}
			
			String aString2 = "01-03-0" + Integer.toHexString(num2) + back;
			String []aStrings=aString2.split("-");
			String string=aStrings[0];
			for(int i=1;i<aStrings.length;i++) {
				string+=aStrings[i];
			}
			//System.out.println("aaaaa+  "+string);
			//String string2=string.toUpperCase();
			//System.out.println("bbbbb "+string2);
			String bString2 = crcUtils.getCrc(string.getBytes());

			return "Rx:" + aString2 + "-" + bString2.substring(0, 2) + "-" + bString2.substring(2, 4);
		}

	}

	private String GNM_0f() {
		// TODO Auto-generated method stub
		if(datas.length<8) {
			return "0f：写多个输出继电器的状态" + "\r\n" + "结构为：地址码(1B)+功能码(1B)+起始寄存器地址(2B)+寄存器个数(2B)+(数据)";
		}

		String cString=data.substring(0,17);
		//System.out.println(cString);
		
		String [] aStrings=cString.split("-");
		
		String bString=aStrings[0];
		for(int i=1;i<aStrings.length;i++) {
			bString+=aStrings[i];
		}
		//System.out.println(bString);
		
		String aString = crcUtils.getCrc(bString.getBytes());
	//	String aString = crcUtils.getCrc(data.substring(0, 17).getBytes());
		return "Rx:" + data.substring(0, 17) + "-" + aString.substring(0, 2) + "-" + aString.substring(2, 4);
	}

	private String GNM_02() {

		if (datas.length < 8) {
			return "02：读取输入继电器的状态" + "\r\n" + "结构为：地址码(1B)+功能码(1B)+起始寄存器地址(2B)+寄存器个数(2B)";
		} else {
			String aString1 = datas[0];
			String aString = aString1 + "-02-02-00-00";
			
			String []aStrings=aString.split("-");
			String bString=aStrings[0];
			
			for(int i=1;i<aStrings.length;i++) {
				bString+=aStrings[i];
			}
			
			//System.out.println("cccc+  "+bString);
			
			//System.out.println("dddd+  "+bString.toUpperCase());

			String cString = crcUtils.getCrc(bString.getBytes());

			return "Rx:" + aString + "-" + cString.substring(0, 2) + "-" + cString.substring(2, 4);

		}

	}

}
