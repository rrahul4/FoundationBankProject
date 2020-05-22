package com.batch12.rvirb.foundation.bank;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class ListMockTest {
	
	List<?> mockList = mock(List.class);
	ArrayList<String> mockArrayList = mock(ArrayList.class);
	ArrayList<String> spyArrayList = spy(ArrayList.class);	

	@Test
	@Order(1)
	public void size_basics() {
		System.out.println("Size : " + mockList.size());
		
		when(mockList.size()).thenReturn(100);
		
		System.out.println("Size : " + mockList.size());
		
		assertEquals(100,mockList.size());

	}
	
	@Test
	@Order(2)
	public void size_basics2() {
		mockArrayList.add("Test 1");
		mockArrayList.add("Test 2");
		
		System.out.println(" Inside Basics 2 : ");
		System.out.println("Size : " + mockArrayList.size());
		System.out.println("Element : " + mockArrayList.get(0));
		System.out.println("Element : " + mockArrayList.get(1));
		
		when(mockArrayList.size()).thenReturn(100);
		when(mockArrayList.get(0)).thenReturn("Hello Rahulkumar");

		System.out.println("Size : " + mockArrayList.size());
		System.out.println("Element : " + mockArrayList.get(0));
		System.out.println("Element : " + mockArrayList.get(1));

		assertEquals(100,mockArrayList.size());
		verify(mockArrayList,times(3)).size();
		verify(mockArrayList,atLeast(1)).size();			
	}

	@Test
	@Order(3)
	public void size_basics3() {
		spyArrayList.add("Test 1");
		spyArrayList.add("Test 2");
		
		System.out.println(" Inside Basics 3 : ");
		System.out.println("Size : " + spyArrayList.size());
		System.out.println("Element : " + spyArrayList.get(0));
		System.out.println("Element : " + spyArrayList.get(1));
		
		when(spyArrayList.size()).thenReturn(100);
		when(spyArrayList.get(0)).thenReturn("Hello Rahulkumar");

		System.out.println("Size : " + spyArrayList.size());
		System.out.println("Element : " + spyArrayList.get(0));
		System.out.println("Element : " + spyArrayList.get(1));

		assertEquals(100,spyArrayList.size());
		verify(spyArrayList,times(3)).size();
		verify(spyArrayList,atLeast(1)).size();
			
	}
	
}
