package healthyhome.dao;

import java.util.List;

import healthyhome.bean.BaseEntity;
import healthyhome.bean.UserMessage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("BaseDao")
public class BaseDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 
	 * @param <E>
	 * @param instance
	 * @return
	 */

	public void save(BaseEntity transientInstance) {
		System.out.println("saving instance");
		try {
			getSession().save(transientInstance);
			System.out.println("save successful");
		} catch (RuntimeException re) {
			System.err.println("save failed");
			re.printStackTrace();
			throw re;
		}
	}

	//修改
	public void attachDirty(BaseEntity instance) {
		System.out.println("attaching dirty instance");
		try {
			getSession().beginTransaction();
			getSession().saveOrUpdate(instance);
			getSession().getTransaction().commit();
			getSession().flush();
			System.out.println("attach successful");
		} catch (RuntimeException re) {
			System.err.println("attach failed");
			throw re;
		}
	}
	public void delete(BaseEntity persistentInstance) {
        System.out.println("deleting instance");
        try {
        	getSession().beginTransaction();
            getSession().delete(persistentInstance);
            getSession().getTransaction().commit();
			getSession().flush();
            System.out.println("delete successful");
        } catch (RuntimeException re) {
           System.err.println("delete failed");
            throw re;
        }
    }
	@SuppressWarnings("unchecked")
	public <E> E findById(Integer id,String classFullName) {
		System.out.println("getting User instance with id: " + id);
		try {
			E instance = (E) getSession().get(classFullName, id);
			return instance;
		} catch (RuntimeException re) {
			System.err.println("get failed");
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public <E> E findById(Integer id) {
		System.out.println("getting User instance with id: " + id);
		E instance = (E) new Object();
		try {
			instance = (E) getSession().get(instance.getClass().getName(), id);
			return instance;
		} catch (RuntimeException re) {
			System.err.println("get failed");
			throw re;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <E> List<E>  findByExample(E instance) {
		System.out.println("finding an instance by example");
		try {
			List results = getSession().createCriteria(instance.getClass().getName())
					.add(Example.create(instance)).list();
			System.out.println("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			System.err.println("find by example failed");
			throw re;
		}
	}
	/**
	 * 编写测试用例
	 * @param args
	 */
	public static void main(String[] args){
		
	}
}
