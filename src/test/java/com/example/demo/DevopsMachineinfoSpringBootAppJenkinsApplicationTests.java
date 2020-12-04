package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DevopsMachineinfoSpringBootAppJenkinsApplicationTests {
	HomeController h=new HomeController();
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testpositive()
	{
		assertEquals(101, h.D2B(5));
	
	}
	@Test
	public void testnegative()
	{
		assertEquals(0, h.D2B(-9));
	}
	
	@Test
	public void testZeroInput()
	{
		assertEquals(0, h.D2B(0));
	}
	
	 @Test
   public void testNameMkyong() {

	       
	        assertEquals("Hello mkyong", h.getMessage("mkyong"));

	    }
	 
	
}
