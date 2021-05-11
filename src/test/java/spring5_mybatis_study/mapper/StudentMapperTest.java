package spring5_mybatis_study.mapper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring5_mybatis_study.config.ContextRoot;
import spring5_mybatis_study.dto.PhoneNumber;
import spring5_mybatis_study.dto.Student;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class} )
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentMapperTest {

	private static final  Log log = LogFactory.getLog(StudentMapperTest.class);	
	
	@Autowired
	private StudentMapper mapper;
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Test
	public void test01SelcetStudentById() {
		//sysout 이름 가져오기랑 같음
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Student student = new Student();
		student.setStudId(1);
		Student selectStudent = mapper.selcetStudentById(student);
		log.debug(selectStudent.toString());
		Assert.assertNotNull(selectStudent);	
		
		
	}
	@Test
	public void test02SelectStudentByWithResultMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Student student = new Student();
		student.setStudId(1);
		
		Student selectedStd = mapper.selectStudetnByIdWithResultMap(student);
		log.debug(selectedStd.toString());
		Assert.assertNotNull(selectedStd);	
	}
	
	@Test 
	public void test05SelectStudentByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		List<Student> list = mapper.selectStudentByAll();
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println);
		
	}
@Test
	public void test03InsertStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1990, 2, 28);
		
		Student student = new Student();
		student.setStudId(3);
		student.setName("홍길동3");
		student.setEmail("lee@test.co.kr");
		student.setDob(newDate.getTime());
		student.setPhone(new PhoneNumber("010-1234-1234"));
		int res = mapper.insertStudent(student);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test06DeleteStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int deleteStudent = mapper.deleteStudent(3);
		Assert.assertEquals(1, deleteStudent);		
	}
	
	@Test
	public void test04UpdateStudent() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Student student = new Student();
		student.setStudId(3);
		student.setName("홍길동4");
		student.setEmail("lee2@test.co.kr");
		student.setPhone(new PhoneNumber("010-1234-1234"));
		student.setDob(new Date());
		
		int res = mapper.updateStudent(student);
		Assert.assertEquals(1, res);
		
//		student.setEmail("timothy@gmail.com");
//		student.setPhone(new PhoneNumber("123-123-1234"));
//		student.setDob(new GregorianCalendar(1988, 04, 25).getTime());
//		result = mapper.updateStudent(student);
//		Assert.assertSame(1, result);		
	}
	@Test
	public void test07SelectStudentByAllForHashMap() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		List<Map<String, Object>> list = mapper.selectStudentByAllForHashMap();
		Assert.assertNotNull(list);
		
		for(Map<String, Object> map : list) {
			for(Entry<String, Object> e : map.entrySet()	) {
				log.debug(String.format("%s -> %s", e.getKey(), e.getValue()));
			}
		}
	}
	
	@Test
	public void test08SelectStudentByIdAssociation() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Student student = new Student();
		student.setStudId(1);
		Student selctedStd = mapper.selectStudentByIdAssociation(student);
		
		Assert.assertNotNull(selctedStd);
		
		log.debug(selctedStd.toString());
		
	}

}