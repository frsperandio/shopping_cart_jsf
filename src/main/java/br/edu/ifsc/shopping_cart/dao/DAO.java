package br.edu.ifsc.shopping_cart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.edu.ifsc.shopping_cart.modelo.BaseEntity;
import br.edu.ifsc.shopping_cart.util.JPAUtil;

public abstract class DAO<T extends BaseEntity> {
	private Class<T> classe;
	protected EntityManager em;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
		em = new JPAUtil().getEntityManager();
	}
	
	public void adiciona(T t) {
		geraIdEAdiciona(t);
	}

	public void atualiza(T t) {
		try {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}

	public T buscaPorId(Long id) {
		return em.find(classe, id);
	}

	public Long contaTodos() {
		Query query = em.createQuery("SELECT COUNT(t) FROM " + classe.toString() + " t");
		return (Long)query.getSingleResult();
	}

	protected void geraIdEAdiciona(T t) {
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		return lista;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
		return lista;
	}

	public void remove(Long id) {
		try {
			em.getTransaction().begin();
			em.remove(em.find(classe, id));
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}

	public void remove(T t) {
		remove(t.getId());
	}
}
