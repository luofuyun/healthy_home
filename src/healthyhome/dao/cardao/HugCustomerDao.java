package healthyhome.dao.cardao;

import healthyhome.bean.HugCustomer;
import healthyhome.dao.BaseDao;

import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
@Repository("HugCustomerDao")
public class HugCustomerDao extends BaseDao {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HugCustomer> findByExample(HugCustomer instance) {
		System.out.println("finding an instance by example");
		try {
			List results = getSession().createCriteria(HugCustomer
					.class.toString().substring(6))
					.add(Example.create(instance)).list();
			System.out.println("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			System.err.println("find by example failed");
			throw re;
		}
	}
}
