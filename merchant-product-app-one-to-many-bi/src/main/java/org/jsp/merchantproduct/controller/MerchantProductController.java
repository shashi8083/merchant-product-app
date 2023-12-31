package org.jsp.merchantproduct.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.merchantproduct.dao.MerchantDao;
import org.jsp.merchantproduct.dao.ProductDao;
import org.jsp.merchantproduct.dto.Merchant;
import org.jsp.merchantproduct.dto.Product;

public class MerchantProductController {

	private static Scanner s = new Scanner(System.in);
	private static MerchantDao merchantDao = new MerchantDao();
	private static ProductDao productDao = new ProductDao();

	public static void main(String[] args) {
		System.out.println("1.Save Merchant");
		System.out.println("2.Update Merchant");
		System.out.println("3.Find Merchant By Id");
		System.out.println("4.Verify Merchant By Phone and Password");
		System.out.println("5.Verify Merchant By Email and Password");
		System.out.println("6.Save Product");
		System.out.println("7.Update Product");
		System.out.println("8.Find Products  By merchant id");
		System.out.println("9.Find Products By Brand");
		System.out.println("10.Find Products By Category");

		switch (s.nextInt()) {
		case 1: {
			saveMerchant();
			break;
		}
		case 2: {
			updateMerchant();
			break;
		}
		case 3: {
			findById();
			break;
		}
		case 4: {
			verifyMerchantByPhone();
			break;
		}
		case 5: {
			verifyMerchantByEmail();
			break;
		}
		case 6: {
			saveProduct();
			break;
		}
		case 7: {
			updateProduct();
			break;
		}
		case 8: {
			findProductByMerchantId();
			break;
		}
		case 9: {
			findProductByProductsBrand();
			break;
		}
		case 10: {
			findProductByProductsCategory();
			break;
		}
		}
	}

	public static void saveMerchant() {
		System.out.println("Enter the Merchant name,phone,email,gst_number,password");
		Merchant m = new Merchant();
		m.setName(s.next());
		m.setPhone(s.nextLong());
		m.setEmail(s.next());
		m.setGst_number(s.next());
		m.setPassword(s.next());
		m = merchantDao.saveMerchant(m);
		System.out.println("Merchant saved with id: " + m.getId());
	}

	public static void updateMerchant() {
		System.out.println("Enter the Merchant id to update");
		int id = s.nextInt();
		System.out.println("Enter the Merchant name,phone,email,gst_number,password");
		Merchant m = new Merchant();
		m.setId(id);
		m.setName(s.next());
		m.setPhone(s.nextLong());
		m.setEmail(s.next());
		m.setGst_number(s.next());
		m.setPassword(s.next());
		m = merchantDao.updateMerchant(m);
		if(m != null)
		System.out.println("Merchant updated with id: " + m.getId()+ " updated");
		else
			System.out.println("Cannot Updated Merchant as Merchant Id is invalid");
	}

	public static void findById()// finding merchant details by merchant id
	{
		System.out.println("Enter the Merchant id to display merchant details");
		int id = s.nextInt();
		Merchant m = merchantDao.findMerchantById(id);
		if (m != null) {
			System.out.println("Merchant id: " + m.getId());
			System.out.println("Merchant Name: " + m.getName());
			System.out.println("Phone: " + m.getPhone());
			System.out.println("Email id: " + m.getEmail());
			System.out.println("GST Number: " + m.getGst_number());
		} else {
			System.err.println("Invalid Merchant Id");
		}
	}

	public static void verifyMerchantByPhone() {
		System.out.println("Enter the Merchant phone and password to display Merchant details");
		long phone = s.nextLong();
		String password = s.next();

		Merchant m = merchantDao.verifyMerchantByPhoneAndPassword(phone, password);
		if (m != null) {
			System.out.println("Verification successfull");
			System.out.println("Merchant id: " + m.getId());
			System.out.println("Merchant Name: " + m.getName());
			System.out.println("Phone: " + m.getPhone());
			System.out.println("Email id: " + m.getEmail());
			System.out.println("GST Number: " + m.getGst_number());
		} else {
			System.err.println("Invalid Merchant phone or password");
		}

	}

	public static void verifyMerchantByEmail() {
		System.out.println("Enter the Merchant email and password to display Merchant details");
		String email = s.next();
		String password = s.next();

		Merchant m = merchantDao.verifyMerchantByEmailAndPassword(email, password);
		if (m != null) {
			System.out.println("Verification successfull");
			System.out.println("Merchant id: " + m.getId());
			System.out.println("Merchant Name: " + m.getName());
			System.out.println("Phone: " + m.getPhone());
			System.out.println("Email id: " + m.getEmail());
			System.out.println("GST Number: " + m.getGst_number());
		} else {
			System.err.println("Invalid Merchant email or password");
		}
	}

	public static void saveProduct() {
		System.out.println("Enter the Merchant id to add Product details");
		int merchant_id = s.nextInt();
		System.out.println("Enter the Product name,brand,category,description,cost and image_url");
		Product p = new Product();
		p.setName(s.next());
		p.setBrand(s.next());
		p.setCategory(s.next());
		p.setDescription(s.next());
		p.setCost(s.nextDouble());
		p.setImage_url(s.next());
		p = productDao.saveProduct(p, merchant_id);
		if (p != null)
			System.out.println(" Product added with id: " + p.getId());
		else
			System.out.println("Cannot add Product as Merchant Id is invalid");
	}

	public static void updateProduct() {
		System.out.println("Enter the Product id, name,brand,category,description,cost and image_url to update");
		Product p = new Product();
		p.setId(s.nextInt());
		p.setName(s.next());
		p.setBrand(s.next());
		p.setCategory(s.next());
		p.setDescription(s.next());
		p.setCost(s.nextDouble());
		p.setImage_url(s.next());
		p = productDao.updateProduct(p);
		if (p != null)
			System.out.println(" Product with id: " + p.getId()+ " updated");
		else
			System.out.println("Cannot update Product as Product Id is invalid");
	}
	public static void findProductByMerchantId() {
		System.out.println("Enter the Merchant Id to display Product details");
		int merchant_id = s.nextInt();
		List<Product> products = productDao.findProductByMerchantId(merchant_id);
		if(products.size() > 0) {
			for(Product p : products) {
				System.out.println("Product name: "+p.getName());
				System.out.println("Brand: "+p.getBrand());
				System.out.println("Category: "+p.getCategory());
				System.out.println("Description: "+p.getDescription());
				System.out.println("Cost: "+p.getCost());
				System.out.println("Image_url: "+p.getImage_url());
				System.out.println("------------------------------");
			}
		}else {
				System.err.println("Invalid merchant id");
			}
		}
	public static void findProductByProductsBrand() {
		System.out.println("Enter the Product brand to display product details");
		String brand = s.next();
		List<Product> products = productDao.findProductByBrand(brand);
		if(products.size() > 0) {
			for(Product p : products) {
				System.out.println("Product name: "+p.getName());
				System.out.println("Brand: "+p.getBrand());
				System.out.println("Category: "+p.getCategory());
				System.out.println("Description: "+p.getDescription());
				System.out.println("Cost: "+p.getCost());
				System.out.println("Image_url: "+p.getImage_url());
				System.out.println("------------------------------");
			}
		}else {
				System.err.println("Invalid Product brand");
			}
	}
	public static void findProductByProductsCategory() {
		System.out.println("Enter the product category to display product details");
		String category = s.next();
		List<Product> products = productDao.findProductByCategory(category);
		if(products.size() > 0) {
			for(Product p : products) {
				System.out.println("Product name: "+p.getName());
				System.out.println("Brand: "+p.getBrand());
				System.out.println("Category: "+p.getCategory());
				System.out.println("Description: "+p.getDescription());
				System.out.println("Cost: "+p.getCost());
				System.out.println("Image_url: "+p.getImage_url());
				System.out.println("------------------------------");
			}
		}else {
				System.err.println("Invalid Product category");
			}
	}

}
