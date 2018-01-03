/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import entity.DeliveryMan;
import java.util.Scanner;
import static client.FoodDeliveryService.list;
/**
 *
 * @author user1
 */
public class moduleB {

	public moduleB() {

		Scanner scanner = new Scanner(System.in);
		DeliveryMan deliveryMan;
		int answer;
		String moreStaff;
		int deliveryManUpdateNumber;
		int moreUpdate;
		String stringHolder = "";
		int intHolder;

		do {
			System.out.println("0. End");
			System.out.println("1. Add Delivery Man");
			System.out.println("2. View Delivery Man Detail");
			System.out.println("3. Update Delivery Man Detail");
			System.out.println("4. View Delivery Man Report");

			System.out.print("Enter you choice : ");
			String inputStr = scanner.nextLine();
			
			if (isDigit(inputStr)) {
				answer = Integer.parseInt(inputStr);
			} else {
				answer = -1;
			}
			
			if (answer == -1) {
				spacing();
				System.out.println("You have input an invalid character please try again");
				
			} else if (answer == 1) {
				do {
					spacing();

					deliveryMan = new DeliveryMan();

					System.out.print("Enter name               : ");
					deliveryMan.setName(scanner.nextLine());
					System.out.print("Enter status             : ");
					deliveryMan.setStatus(scanner.nextLine());
					System.out.print("Enter age                : ");
					stringHolder = scanner.nextLine();
					if (isDigit(stringHolder)) {
						deliveryMan.setAge(Integer.parseInt(stringHolder));
					}
					System.out.print("Enter salary             : ");
					stringHolder = scanner.nextLine();
					if (isDigit(stringHolder)) {
						deliveryMan.setSalary(Double.parseDouble(stringHolder));
					}
					
					System.out.print("Enter address            : ");
					deliveryMan.setAddress(scanner.nextLine());
					System.out.print("Enter phone number       : ");
					deliveryMan.setPhoneNumber(scanner.nextLine());
					deliveryMan.setNumberOfDelivery(0);
					deliveryMan.setTaskStatus("Clock Out");
					deliveryMan.setExperience(0);
					deliveryMan.setDistanceTravel(0);
					deliveryMan.setId("DM" + (list.size()+1));
					list.add(deliveryMan);

					System.out.print("Do you wish to continue to add staff member? (Y)");
					moreStaff = scanner.nextLine();

				} while (moreStaff.equals("Y") || moreStaff.equals("y"));

			} else if (answer == 2) {

				spacing();
				if (deliveryManList().isEmpty()) {
					System.out.println("No Delivery Man In Database");
				} else {
					System.out.println("Delivery Man List: ");
				}
				System.out.println(deliveryManList());
			} else if (answer == 3) {
				spacing();
				for (int i = 0; i < list.size(); i++) {
					System.out.println((i + 1) + ". " + list.get(i).getName());
				}
				System.out.print("Please select the delivery man you wish to update: ");

				deliveryManUpdateNumber = Integer.parseInt(scanner.nextLine()) - 1;

				deliveryMan = new DeliveryMan();
				deliveryMan = list.get(deliveryManUpdateNumber);
				do {

					spacing();
					System.out.println(" 1. Name              : " + deliveryMan.getName());
					System.out.println(" 2. Status            : " + deliveryMan.getStatus());
					System.out.println(" 3. Age               : " + deliveryMan.getAge());
					System.out.println(" 4. Number of Delivery: " + deliveryMan.getNumberOfDelivery());
					System.out.println(" 5. Salary            : " + deliveryMan.getSalary());
					System.out.println(" 6. Address           : " + deliveryMan.getAddress());
					System.out.println(" 7. Phone Number      : " + deliveryMan.getPhoneNumber());
					System.out.println(" 8. Task Status       : " + deliveryMan.getTaskStatus());
					System.out.println(" 9. Experience        : " + deliveryMan.getExperience());
					System.out.println("10. DistanceTravel    : " + deliveryMan.getDistanceTravel());
					System.out.println(" 0. End");
					System.out.print("Select detail wish to update: ");
					moreUpdate = Integer.parseInt(scanner.nextLine());
					switch (moreUpdate) {
						case 1:
							System.out.println("Current Name is " + deliveryMan.getName());
							System.out.print("New update name is ");
							deliveryMan.setName(scanner.nextLine());
							break;
						case 2:
							System.out.println("Current Status is " + deliveryMan.getStatus());
							System.out.print("New update status is ");
							deliveryMan.setStatus(scanner.nextLine());
							break;
						case 3:
							System.out.println("Current age is " + deliveryMan.getAge());
							System.out.print("New update age is ");
							deliveryMan.setAge(Integer.parseInt(scanner.nextLine()));
							break;
						case 4:
							System.out.println("Current number of delivery is " + deliveryMan.getNumberOfDelivery());
							System.out.print("New update number of delivery is ");
							deliveryMan.setNumberOfDelivery(Integer.parseInt(scanner.nextLine()));
							break;
						case 5:
							System.out.println("Current salary is " + deliveryMan.getSalary());
							System.out.print("New update salary is ");
							deliveryMan.setSalary(Double.parseDouble(scanner.nextLine()));
							break;
						case 6:
							System.out.println("Current address is " + deliveryMan.getAddress());
							System.out.print("New update address is ");
							deliveryMan.setAddress(scanner.nextLine());
							break;
						case 7:
							System.out.println("Current phone number is " + deliveryMan.getPhoneNumber());
							System.out.print("New update phone number is ");
							deliveryMan.setPhoneNumber(scanner.nextLine());
							break;
						case 8:
							System.out.println("Current Task Status is " + deliveryMan.getTaskStatus());
							System.out.print("New update task status is ");
							deliveryMan.setTaskStatus(scanner.nextLine());
							break;
						case 9:
							System.out.println("Current Experience is " + deliveryMan.getExperience());
							System.out.print("New update experience is ");
							deliveryMan.setExperience(Integer.parseInt(scanner.nextLine()));
							break;
						case 10:
							System.out.println("Current Distance Travel is " + deliveryMan.getDistanceTravel());
							System.out.print("New update distance travel is ");
							deliveryMan.setDistanceTravel(Double.parseDouble(scanner.nextLine()));
							break;
					}
				} while (moreUpdate != 0);

				list.set(deliveryManUpdateNumber, deliveryMan);

			} else if (answer == 4) {
				spacing();
				System.out.println("Delivery Man Report   Number of delivery made  Distance Travel Made (KM) ");
				double averageDelivery = 0;
				int[] numberOfDelivery = new int[list.size()];
				int[] index = new int[list.size()];
				for (int i = 0; i < list.size(); i++) {
					numberOfDelivery[i] = list.get(i).getNumberOfDelivery();
					index[i] = i;
				}
				
				int[] descIndex = list.getDesc(index, numberOfDelivery);
				
				for (int i = 0; i < list.size(); i++) {
					averageDelivery += list.get(descIndex[i]).getNumberOfDelivery();
					System.out.println(i + 1 + ". " + list.get(descIndex[i]).getName() + "\t\t" + list.get(descIndex[i]).getNumberOfDelivery() + "\t\t" + list.get(descIndex[i]).getDistanceTravel());
				}
				System.out.println("Daily Number of Delivery Report");
				System.out.println("Average Delivery Made   : " + averageDelivery / list.size());
				System.out.println("Highest Delivery Made   : " + list.get(descIndex[0]).getNumberOfDelivery());
				System.out.println("Lowest Delivery Made    : " + list.get(descIndex[list.size()-1]).getNumberOfDelivery());

			} else {
				System.out.println("Please Enter from 0 ~ 4");
			}
			spacing();
		} while (answer != 0);

	}

	public static String deliveryManList() {
		String deliveryList = "";
		System.out.println("The size is " + list.size());
		for (int i = 0; i < list.size(); i++) {
			deliveryList += list.get(i).toString();
			deliveryList += "  \n";
		}
		return deliveryList;
	}

	
	public static boolean isDigit(String validateString) {
		for (int i = 0; i < validateString.length(); i++) {
			if (!Character.isDigit(validateString.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isDigit(int validateInt) {	
		return Character.isDigit(validateInt);
	}
	

	private static void spacing() {
		System.out.println("\n\n\n\n\n");
	}        
        
}
