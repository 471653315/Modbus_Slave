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
		for (int i = 0; i < datas.length; i++) {
			 System.out.println(datas[i]);
		}
		if(datas.length<4) {
			return "���ݽṹ����";
		}

		gnm = datas[1];
		System.out.println(gnm);
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
			return "δ����˹�����";

		}

	}

	private String GNM_10() {
		// TODO Auto-generated method stub
		if(datas.length<8) {
			return "10��д����Ĵ���" + "\r\n" + "�ṹΪ����ַ��(1B)+������(1B)+��ʼ�Ĵ�����ַ(2B)+�Ĵ�������(2B)+(���ݸ���)+(����)";
		}
		
		String aString = crcUtils.getCrc(data.substring(0, 17).getBytes());

		return "Rx:" + data.substring(0, 17) + aString.substring(0, 2) + "-" + aString.substring(2, 4);
	}

	private String GNM_03() {
		// TODO Auto-generated method stub
		if (datas.length < 8) {
			return "03��������Ĵ���" + "\r\n" + "�ṹΪ����ַ��(1B)+������(1B)+��ʼ�Ĵ�����ַ(2B)+�Ĵ�������(2B)";
		} else {
			String num = datas[5];
			System.out.println(num);
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
			String bString2 = crcUtils.getCrc(aString2.getBytes());

			return "Rx:" + aString2 + "-" + bString2.substring(0, 2) + "-" + bString2.substring(2, 4);
		}

	}

	private String GNM_0f() {
		// TODO Auto-generated method stub
		if(datas.length<8) {
			return "0f��д�������̵�����״̬" + "\r\n" + "�ṹΪ����ַ��(1B)+������(1B)+��ʼ�Ĵ�����ַ(2B)+�Ĵ�������(2B)+(����)";
		}

		String aString = crcUtils.getCrc(data.substring(0, 17).getBytes());
		return "Rx:" + data.substring(0, 17) + "-" + aString.substring(0, 2) + "-" + aString.substring(2, 4);
	}

	private String GNM_02() {

		if (datas.length < 8) {
			return "02����ȡ����̵�����״̬" + "\r\n" + "�ṹΪ����ַ��(1B)+������(1B)+��ʼ�Ĵ�����ַ(2B)+�Ĵ�������(2B)";
		} else {
			String aString1 = datas[0];
			String aString = aString1 + "-02-01-00";

			String bString = crcUtils.getCrc(aString.getBytes());

			return "Rx:" + aString + "-" + bString.substring(0, 2) + "-" + bString.substring(2, 4);

		}

	}

}