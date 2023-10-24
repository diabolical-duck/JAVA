package tester;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.app.core.Brand;
import com.app.core.Material;
import com.app.core.StyleWithPen;
import utils.PenValidationRules;

import static java.time.LocalDate.*;

public class Tester {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			boolean flag = false;

			Map<String, StyleWithPen> penMap = new HashMap<>();

			while (!flag) {
				System.out.println("\n1. Add new pen. \t \t \t \t " + "2. Update stock of a Pen. \n \n"
						+ "3. Set Discount of 20% for all \t \t \t "
						+ "4. Remove pens which are never sold \n   pens which are not sold for 3 months."
						+ "    \t once since past 9 months.\n \n" + "5. Display details of specified pen. \t \t "
						+ "0. Exit");
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println(
								"\nEnter the details of: stockId, brandName, color, inkColor, materialName, stock, "
										+ "stockUpdateDate, stockListingDate and price");

						StyleWithPen pen = PenValidationRules.validateAllInputs(sc.next(), sc.next(), sc.next(),
								sc.next(), sc.next(), sc.nextInt(), sc.next(), sc.next(), sc.nextInt(), penMap);

						penMap.put(pen.getStockId(), pen);
						System.out.println("pen Stock details have been successfully entered !!");
						break;

					case 2:

						System.out.println("Enter the stock ID, newStock quantity, and UpdateDate:");
						String temp1 = sc.next();
						int temp2 = sc.nextInt();
						PenValidationRules.updateStockOfPen(temp1, temp2, sc.next(), penMap);

						StyleWithPen ref = penMap.get(temp1);
						System.out.println("Successfully Updated stock on date: " + ref.getStockUpdateDate() + "\nwith updated value of stock is: " + ref.getStock() + "\n");

						break;

					case 3:
						
						   for(Map.Entry<String, StyleWithPen> elem : penMap.entrySet())
						   {
							   StyleWithPen temp_ref = elem.getValue();
//							   LocalDate start_date = temp_ref.getStockListingDate();
							   Period diff = Period.between(temp_ref.getStockListingDate(),now());
							   int in_years = diff.getYears()*12;
							   if((in_years+diff.getMonths())>3)
							   {
								   temp_ref.setDiscount(20);
							   }   
						   }
						   System.out.println("Successfully set the applicable discount !!");
						   
						
						
						break;

					case 4:
						break;

					case 5:
						
						System.out.print("Enter the StockID: ");
//						StyleWithPen showDetails = penMap.get(sc.next());
						
						System.out.println(penMap.get(sc.next()));
						break;

					case 0:
						System.out.println("Exiting ...");
						flag = true;
						break;

					default:
						System.out.println("\nOops, you have entered a wrong choice, please try again:");
						break;
						
					}
				} catch (Exception e) {
					System.out.println("\n \nfound an exception !!");
					e.printStackTrace();
					sc.nextLine();
				}

			}
		}

	}
}
