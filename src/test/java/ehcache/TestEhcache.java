package ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zno.PersonService;

@ContextConfiguration(locations = { "classpath:applicationContext-Ehcache.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEhcache {

	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private PersonService personService;

	@Test
	public void show() {
		// 未缓存时耗时
		long t1 = System.currentTimeMillis();
		System.out.println(personService.getPerson().toString());
		long t2 = System.currentTimeMillis();
		System.out.println("耗时：" + (t2 - t1));
		// 缓存后耗时
		long t3 = System.currentTimeMillis();
		System.out.println(personService.getPerson().toString());
		long t4 = System.currentTimeMillis();
		System.out.println("耗时：" + (t4 - t3));
		// 清除缓存后耗时
		long t5 = System.currentTimeMillis();
		personService.cleanPersonCache();
		System.out.println(personService.getPerson().toString());
		long t6 = System.currentTimeMillis();
		System.out.println("耗时：" + (t6 - t5));
		// 再次获取
		long t7 = System.currentTimeMillis();
		System.out.println(personService.getPerson().toString());
		long t8 = System.currentTimeMillis();
		System.out.println("耗时：" + (t8 - t7));
	}
	
	@Test
	public void create(){
		cacheManager.addCache("testCache");
		Cache cache = cacheManager.getCache("testCache");
		cache.put(new Element("name", "xiaoming"));
		System.out.println(cache.get("name"));
	}
}
