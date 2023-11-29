package org.jsp.UserAndProductApp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.UserAndProductApp.dao.ProductDao;
import org.jsp.UserAndProductApp.dao.UserDao;
import org.jsp.UserAndProductApp.dto.Product;
import org.jsp.UserAndProductApp.dto.User;

public class UserController {
	static Scanner s = new Scanner(System.in);
	static ProductDao pDao = new ProductDao();
	static UserDao uDao = new UserDao();

	public static void main(String[] args) {
		System.out.println("1.Register or save the user");
		System.out.println("2.update");
		System.out.println("3.verfy user by phone and password");
		System.out.println("4.verify user by email and password");
		System.out.println("5.Add product ");
		System.out.println("6.View products by User id");
		System.out.println("7.View products by category");
		System.out.println("8.view products by brand");
		int choice = s.nextInt();
		switch (choice) {
		case 1:
			save();
			break;

		case 2:
			update();
			break;

		case 3:
			verifyBYPhoneAndPassowrd();
			break;
		case 8:
			verifyByBrand();
			break;
		case 6:
			viewProductByUserId();
			break;
		case 7:
			verifyByCategory();
			break;

		case 5:
			addProducts();
			break;

		}

	}

	public static void save() {
		System.out.println("enter your name,email ,phone and password to save");
		String name = s.next();
		String email = s.next();
		long phone = s.nextLong();
		String password = s.next();

		User u = new User();
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		u.setPhone(phone);
		u = uDao.saveUser(u);
		System.out.println("you are registered with id:" + u.getId());

	}

	public static void update() {
		System.out.println("enter your id to update the details");
		int id = s.nextInt();
		System.out.println("enter your name,email ,phone and password to save");
		String name = s.next();
		String email = s.next();
		long phone = s.nextLong();
		String password = s.next();

		User u = new User();
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		u.setPhone(phone);
		u = uDao.updateUser(u);
		System.out.println("you are deregistered with id:" + u.getId());

	}

	public static void verifyBYPhoneAndPassowrd() {
		System.out.println("enter phone and password to get details ");
		long phone = s.nextLong();
		String password = s.next();

		User u = uDao.VerifyUser(phone, password);
		if (u != null) {
			System.out.println(" name :" + u.getName());
			System.out.println("id :" + u.getId());
		} else {
			System.out.println("invalid phone and password");
		}

	}

	public static void verifyByCategory() {
		System.out.println("enter the category");
		String category = s.next();
		List<Product> p = (List<Product>) pDao.findProductByCategory(category);
		for (Product p1 : p) {
			System.out.println("brand" + p1.getBrand());
			System.out.println("name" + p1.getName());
			System.out.println("price" + p1.getPrice());
		}
	}

	public static void verifyByBrand() {
		System.out.println("enter the category");
		String brand = s.next();
		List<Product> p = (List<Product>) pDao.findProductByBrand(brand);
		for (Product p1 : p) {
			System.out.println("category" + p1.getCategory());
			System.out.println("name" + p1.getName());
			System.out.println("price" + p1.getPrice());
		}
	}

	public static void addProducts() {
		System.out.println("enter your id to add the products");
		int uid = s.nextInt();
		System.out.println("enter the name,brand,category,price");
		String name = s.next();
		String brand = s.next();
		String category = s.next();
		double price = s.nextDouble();

		Product p = new Product();
		p.setId(uid);
		p.setBrand(brand);
		p.setCategory(category);
		p.setName(name);
		p.setPrice(price);
		p = pDao.saveProduct(p, uid);
		if (p != null) {
			System.out.println("product added to the cart with id:" + p.getId());
		} else {
			System.out.println("User with the entered id is not present ");
		}
	}

	public static void viewProductByUserId() {
		System.out.println("enter your id to display products");
		int id = s.nextInt();
		List<Product> products = pDao.findProductsByUserId(id);
		for (Product p : products) {
			System.out.println(p);
		}
	}

}
