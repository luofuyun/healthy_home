package healthyhome.dao.cardao;

import healthyhome.bean.CarInfo;
import healthyhome.dao.BaseDao;

import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
@Repository("CarInfoDao")
public class CarInfoDao extends BaseDao {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CarInfo> findByExample(CarInfo instance) {
		System.out.println("finding an instance by example");
		try {
			List results = getSession().createCriteria(CarInfo.class.toString().substring(6))
					.add(Example.create(instance)).list();
			System.out.println("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			System.err.println("find by example failed");
			throw re;
		}
	}
}
