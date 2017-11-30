package com.Tianyu.dealerListDisplay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Service {
	
	public static ArrayList<Vehicle> readAndGetVehicles(File file){
		ArrayList<Vehicle> res = new ArrayList<>();
		BufferedReader buf = null;
		try {
			buf = new BufferedReader(new FileReader(file));
			String curLine = buf.readLine(
					);
			curLine = buf.readLine();
			while(curLine != null) {
				String[] arr = curLine.split("~");
				Vehicle v = new Vehicle(arr);
				res.add(v);
				curLine = buf.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public static void search(ArrayList<Vehicle> list, String str) {
		
	}
	
	
	
	
	
	public static void sortById(ArrayList<Vehicle> list, boolean isAscend) {
		Collections.sort(list, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				if(isAscend) {
					return o1.id.compareTo(o2.id);
				}else {
					return o2.id.compareTo(o1.id);
				}
			}
		});
	}
	
	public static void sortByWebId(ArrayList<Vehicle> list, boolean isAscend) {
		Collections.sort(list, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				if(isAscend) {
					return o1.webId.compareTo(o2.webId);
				}else {
					return o2.webId.compareTo(o1.webId);
				}
			}
		});
	}
	
	public static void sortByCategory(ArrayList<Vehicle> list, boolean isAscend) {
		Collections.sort(list, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				if(isAscend) {
					return o1.category.compareTo(o2.category);
				}else {
					return o2.category.compareTo(o1.category);
				}
			}
		});
	}
	
	public static void sortByYear(ArrayList<Vehicle> list, boolean isAscend) {
		Collections.sort(list, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				if(isAscend) {
					return o1.year.compareTo(o2.year);
				}else {
					return o2.year.compareTo(o1.year);
				}
			}
		});
	}
	
	public static void sortByPrice(ArrayList<Vehicle> list, boolean isAscend) {
		Collections.sort(list, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				if(isAscend) {
					return (int)(o1.price - o2.price);
				}else {
					return (int)(o2.price - o1.price);
				}
			}
		});
	}
	
	public static void sortByModel(ArrayList<Vehicle> list, boolean isAscend) {
		Collections.sort(list, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				if(isAscend) {
					return o1.model.compareTo(o2.model);
				}else {
					return o2.model.compareTo(o1.model);
				}
			}
		});
	}
	
	public static void sortByType(ArrayList<Vehicle> list, boolean isAscend) {
		Collections.sort(list, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				if(isAscend) {
					return o1.type.compareTo(o2.type);
				}else {
					return o2.type.compareTo(o1.type);
				}
			}
		});
	}
	
	public static void sortByMake(ArrayList<Vehicle> list, boolean isAscend) {
		Collections.sort(list, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				if(isAscend) {
					return o1.make.compareTo(o2.make);
				}else {
					return o2.make.compareTo(o1.make);
				}
			}
		});
	}
	
}
