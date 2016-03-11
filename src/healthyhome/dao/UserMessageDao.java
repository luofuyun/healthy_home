package healthyhome.dao;

import healthyhome.bean.UserMessage;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

@Repository("UserMessageDao")
public class UserMessageDao extends BaseDao {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserMessage> findByExample(UserMessage instance) {
		System.out.println("finding an instance by example");
		try {
			List results = getSession().createCriteria(UserMessage.class.toString().substring(6))
					.add(Example.create(instance)).list();
			System.out.println("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			System.err.println("find by example failed");
			throw re;
		}
	}
	public List<UserMessage> getPageList(int pageSize,int nowPage){
		Integer start = (nowPage - 1) * pageSize;
		Integer end = nowPage * pageSize;
		String queryString = "from UserMessage ";
		System.out.println("DAO:Query HQL by page :" + queryString);
		Query query = getSession().createQuery(queryString);
		query.setFirstResult(start).setMaxResults(pageSize);// 使用hibernate的分页查询
		query.setCacheable(true);
		return query.list();
	}
}
