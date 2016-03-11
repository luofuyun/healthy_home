package healthyhome.dao;

import healthyhome.bean.RoleMenu;

import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

@Repository("RoleMenuDao")
public class RoleMenuDao extends BaseDao {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RoleMenu> findByExample(RoleMenu instance) {
		System.out.println("finding an instance by example");
		try {
			List results = getSession().createCriteria(RoleMenu.class.toString().substring(6))
					.add(Example.create(instance)).list();
			System.out.println("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			System.err.println("find by example failed");
			throw re;
		}
	}
}
