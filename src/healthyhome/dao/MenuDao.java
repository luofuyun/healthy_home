package healthyhome.dao;

import healthyhome.bean.Menu;
import healthyhome.bean.UserMessage;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

@Repository("MenuDao")
public class MenuDao extends BaseDao {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Menu> findByExample(Menu instance) {
		System.out.println("finding an instance by example");
		try {
			List results = getSession().createCriteria(Menu.class.toString().substring(6))
					.add(Example.create(instance)).list();
			System.out.println("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			System.err.println("find by example failed");
			throw re;
		}
	}
	public Menu findById(Integer id) {
		System.out.println("getting Menu instance with id: " + id);
		try {
			Menu instance = (Menu) getSession().get("healthyhome.bean.Menu", id);
			return instance;
		} catch (RuntimeException re) {
			System.err.println("get failed");
			throw re;
		}
	}
	public List<UserMessage> getPageList(int pageSize,int nowPage){
		Integer start = (nowPage - 1) * pageSize;
		Integer end = nowPage * pageSize;
		String queryString = "from Menu ";
		System.out.println("DAO:Query HQL by page :" + queryString);
		Query query = getSession().createQuery(queryString);
		query.setFirstResult(start).setMaxResults(pageSize);// 使用hibernate的分页查询
		query.setCacheable(true);
		return query.list();
	}
}
