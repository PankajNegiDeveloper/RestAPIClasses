package in.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortLengthOfStringByLengthOfStringInJava {
	public static void main(String[] args) {
		List<String> string = new ArrayList<>();
		string.add("Pank");
		string.add("Panka");
		string.add("Amit");
		string.add("Vk");
		string.add("Himani");
		string.add("Divya");

		Collections.sort(string, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
//				return s1.length() - s2.length(); //sorting by length
				return s1.compareTo(s2); // sorting by alphabet
			}
		});
		string.stream().forEach(i -> System.out.println(i));
	}
}
