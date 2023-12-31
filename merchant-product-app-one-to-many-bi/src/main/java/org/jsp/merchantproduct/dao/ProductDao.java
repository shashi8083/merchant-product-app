package org.jsp.merchantproduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.merchantproduct.dto.Merchant;
import org.jsp.merchantproduct.dto.Product;

public class ProductDao {
	private EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Product saveProduct(Product product,int merchant_id) {
		Merchant m = manager.find(Merchant.class,merchant_id);
		if(m != null) {
			m.getProducts().add(product);
			product.setMerchant(m);
			manager.persist(product);
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		transaction.commit();
		return product;
	}
		return null;
	}

	public Product updateProduct(Product product) {
		Product dbProduct = manager.find(Product.class, product.getId());
		if (dbProduct != null) {

			dbProduct.setName(product.getName());
			dbProduct.setBrand(product.getBrand());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setCost(product.getCost());
			dbProduct.setImage_url(product.getImage_url());
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return dbProduct;
		}
		return null;
	}

	public List<Product> findProductByMerchantId(int merchant_id) {
		Query q = manager.createQuery("select m.products from Merchant m where m.id=?1");
		q.setParameter(1, merchant_id);
		return q.getResultList();

	}

	public List<Product> findProductByBrand(String brand) {
		Query q = manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}

	public List<Product> findProductByCategory(String category) {
		Query q = manager.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, category);
		return q.getResultList();
	}

}
