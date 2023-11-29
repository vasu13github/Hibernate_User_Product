package org.jsp.UserAndProductApp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.UserAndProductApp.dto.Product;
import org.jsp.UserAndProductApp.dto.User;

public class ProductDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Product saveProduct(Product product, int user_id) {
		User u = manager.find(User.class, user_id);
		if (u != null) {
			u.getProducts().add(product);// adding product to user
			product.setUser(u);// assigning user to product
			EntityTransaction t = manager.getTransaction();
			manager.persist(product);
			t.begin();
			t.commit();
			return product;
		}
		return null;
	}

	public Product updateProduct(Product product, int user_id) {
		User u = manager.find(User.class, user_id);
		if (u != null) {
			u.getProducts().add(product);// adding product to user
			product.setUser(u);// assigning user to product
			EntityTransaction t = manager.getTransaction();
			manager.merge(product);
			t.begin();
			t.commit();
			return product;
		}
		return null;
	}

	public Product findProduct(int id) {
		return manager.find(Product.class, id);
	}

	public boolean deleteProduct(int id) {
		Product u = findProduct(id);
		if (u != null) {
			manager.remove(u);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return true;
		}
		return false;
	}

	public List<Product> findProductsByUserId(int user_id) {
		Query q = manager.createQuery("select u.products from User u where u.id=?1");
		q.setParameter(1, user_id);
		return q.getResultList();
	}

	public List<Product> findProductByCategory(String Category) {
		Query q = manager.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, Category);
		return q.getResultList();
	}
	

	public List<Product> findProductByBrand(String brand) {
		Query q = manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}
}
