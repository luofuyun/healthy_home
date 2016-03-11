package healthyhome.dao;

import healthyhome.bean.HealthKnowledge;

import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("HealthKnowledgeDao")
public class HealthKnowledgeDao extends BaseDao {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HealthKnowledge> findByExample(HealthKnowledge instance) {
		System.out.println("finding an instance by example");
		try {
			List results = getSession().createCriteria(HealthKnowledge.class.toString().substring(6))
					.add(Example.create(instance)).list();
			System.out.println("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			System.err.println("find by example failed");
			throw re;
		}
	}
}
