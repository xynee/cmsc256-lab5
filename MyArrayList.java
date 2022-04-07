package cmsc256;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MyArrayList<T> implements List<T> {
	private int size;            // keeps track of the number of elements
	private T[] array;           // stores the elements


	@SuppressWarnings("unchecked")
	public MyArrayList() {
		// You can't instantiate an array of T[], but you can instantiate 
		// an array of Object and then cast it.  Details at
		// http://www.ibm.com/developerworks/java/library/j-jtp01255/index.html
		array = (T[]) new Object[10];
		size = 0;
	}

	@Override
	public boolean add(T newEntry) {
		if (size >= array.length) {
			// make a bigger array and copy over the elements
			doubleArray();
		}
		array[size] = newEntry;
		size++;
		return true;
	}
	
	private void doubleArray() {
		@SuppressWarnings("unchecked") // see comment in the constructor
		T[] bigger = (T[]) new Object[array.length * 2];
		System.arraycopy(array, 0, bigger, 0, array.length);
		array = bigger;
	}

	@Override
	public void add(int newPosition, T newEntry) {

		if(newPosition < 0 || newPosition > size()){
			throw new IndexOutOfBoundsException();
		}
		if(newEntry == null){
			throw new NullPointerException();
		}
		if (newPosition - size >= 0) System.arraycopy(array, size, array, size + 1, newPosition - size);
		array[newPosition] = newEntry;
		size++;

	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		boolean flag = true;
		for (T element: collection) {
			flag = flag && add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		// note: this version does not actually null out the references
		// in the array, so it might delay garbage collection.
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object element: collection) {
			if (!contains(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public int indexOf(Object target) {

		for (int i = 0; i < size; i++){
			if (target == null ? get(i) == null : target.equals(get(i))) {
				return i;
			}
		}
		return -1;
	}

	/** Checks whether an element of the array is the target.
	 *
	 * Handles the special case that the target is null.
	 *
	 * @param target object
	 * @param element object
	 */
	private boolean equals(Object target, Object element) {
		if (target == null) {
			return element == null;
		} else {
			return target.equals(element);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		// make a copy of the array
		T[] copy = Arrays.copyOf(array, size);
		// make a list and return an iterator
		return Arrays.asList(copy).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		// see notes on indexOf
		for (int i = size-1; i>=0; i--) {
			if (equals(target, array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		// make a copy of the array
		T[] copy = Arrays.copyOf(array, size);
		// make a list and return an iterator
		return Arrays.asList(copy).listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// make a copy of the array
		T[] copy = Arrays.copyOf(array, size);
		// make a list and return an iterator
		return Arrays.asList(copy).listIterator(index);
	}

	@Override
	public boolean remove(Object obj) {
		int index = indexOf(obj);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public T remove(int index) {

		if (index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		T it = array[index];
		if (size - (index + 1) >= 0) System.arraycopy(array, index + 1, array, index + 1 - 1, size - (index + 1));
		size--;
		return it;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean flag = true;
		for (Object obj: collection) {
			flag = flag && remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element){

			if (index < 0 || index >= size()) {
				throw new IndexOutOfBoundsException();
			}
			if (element == null) {
				throw new NullPointerException();
			}
			T it = array[index];
			array[index] = element;
			return it;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		T[] copy = Arrays.copyOfRange(array, fromIndex, toIndex);
		return Arrays.asList(copy);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}

	@Override
	public <U> U[] toArray(U[] array) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @param args .
	 */
	public static void main(String[] args) {
		// run a few simple tests
		MyArrayList<Integer> mal = new MyArrayList<Integer>();
		mal.add(1);
		mal.add(2);
		mal.add(3);
		System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);

		mal.remove(Integer.getInteger("2"));
		System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);
	}

}

