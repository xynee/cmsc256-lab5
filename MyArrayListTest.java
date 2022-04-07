package cmsc256;

import static org.junit.Assert.*;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MyArrayListTest {

	protected List<Integer> mylist;
	protected List<Integer> list;

	@Before
	public void setUp() throws Exception {
		list = new ArrayList<Integer>();  // this list is the Java ArrayList
		list.add(1);
		list.add(2);
		list.add(3);

		mylist = new MyArrayList<Integer>(); // this is a MyArrayList list
		mylist.addAll(list);
	}

	@Test
	public void testMyList() {
		assertEquals(3, mylist.size());
	}

	@Test
	public void testAdd1() {
		for (int i = 4; i < 20; i++) {
			mylist.add(i);
		}
		MatcherAssert.assertThat(mylist.get(18), is(19));
	}


	@Test  // is the correct position set?
	public void testAddInt2() {
		mylist.add(1, 5);
		MatcherAssert.assertThat(mylist.get(1), is(5));
	}

	@Test  // does the list size increment by 1?
	public void testAddInt3() {
		mylist.add(1, 5);
		MatcherAssert.assertThat(mylist.size(), is(4));
	}

	@Test  (expected=IndexOutOfBoundsException.class)
	public void testAddInt4() {
		mylist.add(-1, 0);
	} 

	@Test  (expected=IndexOutOfBoundsException.class)
	public void testAddInt5() {
		mylist.add(4, 0);
	} 

	@Test  
	public void testAddInt6() {
		mylist.add(0, 6);
		MatcherAssert.assertThat(mylist.get(0), is(6));
	}

	@Test  
	public void testAddInt7() {
		mylist.add(1, 5);
		mylist.add(4, 0);
		mylist.add(0, 6);
		mylist.add(5, 7);
		MatcherAssert.assertThat(mylist.get(5), is(7));
	}

	@Test
	public void testAddAllCollectionOfQextendsT1() {
		mylist.addAll(list);
		mylist.addAll(list);
		mylist.addAll(list);
		assertEquals(12, mylist.size());
	}

	@Test
	public void testAddAllCollectionOfQextendsT2() {
		mylist.addAll(list);
		mylist.addAll(list);
		mylist.addAll(list);
		MatcherAssert.assertThat(mylist.get(5), is(3));
	}

	@Test
	public void testClear() {
		mylist.clear();
		assertEquals(0, mylist.size());
	}

	@Test
	public void testContains1() {
		assertTrue(mylist.contains(1));
	}

	@Test
	public void testContains2() {
		assertFalse(mylist.contains(4));
	}

	@Test
	public void testContains3() {
		assertFalse(mylist.contains(null));
	}

	@Test
	public void testContains4() {
		mylist.add(null);
		assertTrue(mylist.contains(null));
	}

	@Test
	public void testContainsAll() {
		assertTrue(mylist.containsAll(list));
	}

	@Test
	public void testGet() {
		MatcherAssert.assertThat(mylist.get(1), is(2));
	}

	@Test	// test element in the first position
	public void testIndexOf1() {
		assertEquals(0, mylist.indexOf(1));
	}

	@Test // test element in the last position
	public void testIndexOf2() {
		assertEquals(2, mylist.indexOf(3));
	}

	@Test	// test an element not in the list
	public void testIndexOf() {
		assertEquals(-1, mylist.indexOf(42));
	}

	@Test
	public void testIndexOfNull1() {
		assertEquals("Null not in list", -1, mylist.indexOf(null));
	}

	@Test
	public void testIndexOfNull2() {
		mylist.add(null);
		assertEquals("Null added to list", 3, mylist.indexOf(null));
	}

	@Test
	public void testIsEmpty1() {
		assertFalse(mylist.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		mylist.clear();
		assertTrue(mylist.isEmpty());
	}

	@Test
	public void testIterator1() {
		Iterator<Integer> iter = mylist.iterator();
		MatcherAssert.assertThat(iter.next(), is(1));
	}

	@Test
	public void testIterator2() {
		Iterator<Integer> iter = mylist.iterator();
		iter.next();
		MatcherAssert.assertThat(iter.next(), is(2));
	}

	@Test
	public void testIterator3() {
		Iterator<Integer> iter = mylist.iterator();
		iter.next();
		iter.next();
		MatcherAssert.assertThat(iter.next(), is(3));
	}

	@Test
	public void testIterator4() {
		Iterator<Integer> iter = mylist.iterator();
		iter.next();
		iter.next();
		iter.next();
		assertFalse(iter.hasNext());
	}

	@Test
	public void testLastIndexOf() {
		mylist.add(2);
		assertEquals(3, mylist.lastIndexOf(2));
	}

	@Test  // removing middle element in the list - checking return value
	public void testRemoveObject1a() {
		assertTrue(mylist.remove(Integer.valueOf("2")));
	}
	
	@Test  // removing middle element in the list - checking correct size
	public void testRemoveObject1b() {
		mylist.remove(Integer.valueOf("2"));
		assertEquals(2, mylist.size());
	}
	
	@Test  // removing middle element in the list  - checking updated positions
	public void testRemoveObject1c() {
		mylist.remove(Integer.valueOf("2"));
		MatcherAssert.assertThat(mylist.get(1), is(3));
	}
	
	@Test  // removing the first element in the list - checking return value
	public void testRemoveObject2a() {
		mylist.remove(Integer.valueOf("2"));
		assertTrue(mylist.remove(Integer.valueOf("1")));
	}
	
	@Test  // removing the first element in the list - checking correct size
	public void testRemoveObject2b() {
		mylist.remove(Integer.valueOf("2"));
		mylist.remove(Integer.valueOf("1"));
		assertEquals(1, mylist.size());
	}
	
	@Test  // removing the first element in the list - checking updated positions
	public void testRemoveObject2c() {
		mylist.remove(Integer.valueOf("2"));
		mylist.remove(Integer.valueOf("1"));
		MatcherAssert.assertThat(mylist.get(0), is(3));
	}

	@Test // try removing the element NOT in the list - checking return value
	public void testRemoveObject3a() {
		assertFalse(mylist.remove(Integer.valueOf("5")));
	}
	
	@Test // try removing the element NOT in the list - checking that size is unchanged
	public void testRemoveObject3b() {
		mylist.remove(Integer.valueOf("5"));
		assertEquals(3, mylist.size());
	}
	
	@Test // try removing the element NOT in the list - checking first element is unchanged
	public void testRemoveObject3c() {
		mylist.remove(Integer.valueOf("5"));
		MatcherAssert.assertThat(mylist.get(0), is(1));
	}

	@Test  // removing the last element in the list - checking return value
	public void testRemoveObject4a() {
		mylist.remove(Integer.valueOf("2"));
		mylist.remove(Integer.valueOf("1"));
		assertTrue(mylist.remove(Integer.valueOf("3")));
	}
	
	@Test  // removing the last element in the list - checking size of empty list
	public void testRemoveObject4b() {
		mylist.remove(Integer.valueOf("2"));
		mylist.remove(Integer.valueOf("1"));
		mylist.remove(Integer.valueOf("3"));
		assertEquals(0, mylist.size());
	}

	@Test
	public void testRemoveInt1() {
		Integer val = mylist.remove(1);
		assertEquals(Integer.valueOf("2"), val);
	}
	
	@Test
	public void testRemoveInt2() {
		Integer val = mylist.remove(1);
		assertEquals(2, mylist.size());
	}
	
	@Test
	public void testRemoveInt3() {
		Integer val = mylist.remove(1);
		MatcherAssert.assertThat(mylist.get(1), is(3));
	}


	@Test
	public void testRemoveAll() {
		mylist.removeAll(list);
		assertEquals(0, mylist.size());
	}

	@Test	// test setting the first position in the list - checking return value
	public void testSet1a() {
		Integer val = mylist.set(0, 6);
		assertEquals(1, val.intValue());
	}	
	
	@Test	// test setting the first position in the list - checking list updated
	public void testSet1b() {
		Integer val = mylist.set(0, 6);
		MatcherAssert.assertThat(mylist.get(0), is(Integer.valueOf("6")));
	}
	
	@Test	// test setting middle position in the list - checking return value
	public void testSet2a() {
		Integer val = mylist.set(1, 5);
		assertEquals(2, val.intValue());
	}	
	
	@Test	// test setting middle position in the list - checking list updated
	public void testSet2b() {
		Integer val = mylist.set(1, 5);
		MatcherAssert.assertThat(mylist.get(1), is(Integer.valueOf("5")));
	}
	
	@Test	// test setting the last position in the list - checking return value
	public void testSet3a() {
		Integer val = mylist.set(2, 7);
		assertEquals(3, val.intValue());
	}	
	
	@Test	// test setting the last position in the list - checking list updated
	public void testSet3b() {
		Integer val = mylist.set(2, 7);
		MatcherAssert.assertThat(mylist.get(2), is(Integer.valueOf("7")));
	}

	@Test  (expected=IndexOutOfBoundsException.class) 
	public void testSetExceptions1() {
		// try a negative position
		mylist.set(-1, 0);
	}
	
	@Test  (expected=IndexOutOfBoundsException.class)
	public void testSetExceptions2() {
		// try a position larger than the list
		mylist.set(4, 0);
	}

	@Test
	public void testSize() {
		assertEquals(3, mylist.size());
	}

	@Test
	public void testSubList() {
		mylist.addAll(list);
		List<Integer> sub = mylist.subList(1, 4);
		MatcherAssert.assertThat(sub.get(1), is(3));
	}

	@Test
	public void testToArray() {
		Object[] array = mylist.toArray();
		MatcherAssert.assertThat((Integer)array[0], is(1));
	}

}
