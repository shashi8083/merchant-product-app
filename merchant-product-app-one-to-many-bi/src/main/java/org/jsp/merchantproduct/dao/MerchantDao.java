package org.jsp.merchantproduct.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.merchantproduct.dto.Merchant;

public class MerchantDao {
	private EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public Merchant saveMerchant(Merchant merchant) {
		EntityTransaction transaction  = manager.getTransaction();
		manager.persist(merchant);
		transaction.begin();
		transaction.commit();
		return merchant;
	}
	
	public Merchant updateMerchant(Merchant merchant) {
		Merchant dbMerchant = findMerchantById(merchant.getId());
		if(dbMerchant != null) {
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPassword(merchant.getPassword());
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return dbMerchant;
		}
		return null;
	}
	public Merchant findMerchantById(int id) {
		return manager.find(Merchant.class,id);
	}
	
	public Merchant verifyMerchantByPhoneAndPassword(long phone,String password) {
		Query q = manager.createQuery("select m from Merchant m where m.phone=?1 and m.password=?2");
		q.setParameter(1,phone);
		q.setParameter(2,password);
		try {
			return (Merchant) q.getSingleResult();
			
		}catch(NoResultException e) {
			System.err.println("Invalid Phone and Password");
			return null;
		}
	}
	public Merchant verifyMerchantByEmailAndPassword(String email,String password) {
		Query q = manager.createQuery("select m From Merchant m where m.email=?1 and m.password=?2");
		q.setParameter(1,email);
		q.setParameter(2,password);
		try {
			return (Merchant) q.getSingleResult();
		}catch(NoResultException e ) {
			return null;
		}
	}
	
}
