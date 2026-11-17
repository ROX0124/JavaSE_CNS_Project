package chapter3.oop;

public class MyDateTest {
	public static void main(String[] args) {
		MyDate date = new MyDate();
//		date.day = 32;
		date.setMonth(13);
		System.out.println(date.getMonth());
		
		MyDate date2 = new MyDate(2026,13,16);
		System.out.println(date2.getMonth());
	}
}
