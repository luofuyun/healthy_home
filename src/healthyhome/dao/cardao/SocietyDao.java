package healthyhome.dao.cardao;

import healthyhome.bean.BaseEntity;
import healthyhome.bean.Society;
import healthyhome.dao.BaseDao;

import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
@Repository("SocietyDao")
public class SocietyDao extends BaseDao {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Society> findByExample(Society instance) {
		System.out.println("finding an instance by example");
		try {
			List results = getSession().createCriteria(Society.class.toString().substring(6))
					.add(Example.create(instance)).list();
			System.out.println("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			System.err.println("find by example failed");
			throw re;
		}
	}
	public BaseEntity findById(Integer id) {
		System.out.println("getting User instance with id: " + id);
		try {
			BaseEntity instance = (BaseEntity) getSession().get("healthyhome.bean.Society", id);
			return instance;
		} catch (RuntimeException re) {
			System.err.println("get failed");
			throw re;
		}
	}
}
