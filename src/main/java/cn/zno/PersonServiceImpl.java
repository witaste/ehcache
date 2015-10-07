package cn.zno;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

	@Cacheable(value = "personCache", key = "123456") 
	public Person getPerson(){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person person = new Person();
		Car car = new Car();
		car.setBrand("benz");
		person.setAge(11);
		person.setCar(car);
		return person;
	}

	@CacheEvict(value = "personCache", key = "123456")  
	public void cleanPersonCache() {
		System.out.println("clean personCache[123456]");
	}
	
}
