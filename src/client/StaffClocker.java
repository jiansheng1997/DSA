/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import ADT.List;
import entity.DeliveryMan;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class StaffClocker {

	private Scanner scanner = new Scanner(System.in);

	public List<DeliveryMan> staffClockerMain(List<DeliveryMan> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ". " + list.get(i).getName());
		}

		System.out.print("Please select your name: ");
		int choice = Integer.parseInt(scanner.nextLine()) - 1;
		DeliveryMan deliveryMan = list.get(choice);
		if (deliveryMan.getTaskStatus().equals("Clock Out")) {
			deliveryMan.setTaskStatus("Clock In");
			System.out.println("Welcome Back To Work");
		} else {
			deliveryMan.setTaskStatus("Clock Out");
			System.out.println("Have a nice");
		}
		list.set(choice, deliveryMan);
		return list;
	}
}
