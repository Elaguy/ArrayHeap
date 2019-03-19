/*
 * Min Heap
 */

package schultz.gsu.codinga4;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayHeap<T extends Comparable<T>> {
	
	private ArrayList<T> data;
	
	public ArrayHeap() {
		data = null;
	}
	
	public ArrayHeap(T[] data) {
		this.data = new ArrayList<T>(Arrays.asList(data));
	}
	
	public T getRoot() {
		return data.get(0);
	}

	public int size(int nodeRank) {
		if(data.get(nodeRank) == null)
			return 0;
		
		return 1 + size(getLeftChildRank(nodeRank)) + size(getRightChildRank(nodeRank));
	}
	
	public boolean isEmpty() {
		return size(0) == 0;
	}

	public int height(int nodeRank) {
		// added in case either left or right child is null, avoiding NullPointerException
		// not needed if tree is a proper binary tree
		if(data.get(nodeRank) == null)
			return 0;
		
		if(this.isExternal(nodeRank))
			return 0;
		
		return 1 + Math.max(height(getLeftChildRank(nodeRank)), height(getRightChildRank(nodeRank)));
	}
	
	public int getLeftChildRank(int nodeRank) {
		int result = 2 * nodeRank + 1;
		
		if(result > data.size() - 1 || result < 0 || data.get(result) == null)
			return -1;
		
		return result;
	}
	
	public int getRightChildRank(int nodeRank) {
		int result = 2 * nodeRank + 2;
		
		if(result > data.size() - 1 || result < 0 || data.get(result) == null)
			return -1;
		
		return result;
	}
	
	public int getParentRank(int nodeRank) {
		int result = (nodeRank - 1) / 2;
		
		if(result > data.size() - 1 || result < 0 || data.get(result) == null)
			return -1;
		
		return result;
	}
	
	public boolean isInternal(int nodeRank) {
		return data.get(getLeftChildRank(nodeRank)) != null || data.get(getRightChildRank(nodeRank)) != null;
	}
	
	public boolean isExternal(int nodeRank) {
		return !isInternal(nodeRank);
	}
	
	public int swap(int index1, int index2) {
		T temp = data.get(index1);
		
		data.set(index1, data.get(index2));
		data.set(index2, temp);
		
		return index2;
	}
	
	public int getSmallerChild(int nodeRank) {
		if(getLeftChildRank(nodeRank) == -1 && getRightChildRank(nodeRank) == -1)
			return 0;
		if(getLeftChildRank(nodeRank) == -1)
			return getRightChildRank(nodeRank);
		else if(getRightChildRank(nodeRank) == -1)
			return getLeftChildRank(nodeRank);
		
		int result = data.get(getLeftChildRank(nodeRank)).compareTo(data.get(getRightChildRank(nodeRank)));
		
		if(result < 0)
			return getLeftChildRank(nodeRank);
		else if(result > 0)
			return getRightChildRank(nodeRank);
		
		return -1;
	}
	
	public int getLargerChild(int nodeRank) {
		if(getLeftChildRank(nodeRank) == -1 && getRightChildRank(nodeRank) == -1)
			return 0;
		if(getLeftChildRank(nodeRank) == -1)
			return getRightChildRank(nodeRank);
		else if(getRightChildRank(nodeRank) == -1)
			return getLeftChildRank(nodeRank);
		
		int result = data.get(getLeftChildRank(nodeRank)).compareTo(data.get(getRightChildRank(nodeRank)));
		
		if(result < 0)
			return getRightChildRank(nodeRank);
		else if(result > 0)
			return getLeftChildRank(nodeRank);
		
		return -1;
	}
	
//	public void insert(T element) {
//		data.add(element);
//		
//		int insertedRank = data.size() - 1;
//		while((data.get(insertedRank).compareTo(data.get(getParentRank(insertedRank)))) < 0)
//			insertedRank = swap(insertedRank, getParentRank(insertedRank));
//	}
	
	public void insertMin(T element) {
		data.add(element);
		
		shiftUpMin(data.size() - 1);
	}
	
	public void insertMax(T element) {
		data.add(element);
		
		shiftUpMax(data.size() - 1);
	}
	
	public void shiftUpMin(int nodeRank) {
		if(nodeRank != 0) {
			int parentRank = getParentRank(nodeRank);
			
			if(data.get(nodeRank).compareTo(data.get(parentRank)) < 0) {
				swap(nodeRank, parentRank);
				shiftUpMin(parentRank);
			}
		}
	}
	
	public void shiftUpMax(int nodeRank) {
		if(nodeRank != 0) {
			int parentRank = getParentRank(nodeRank);
			
			if(data.get(nodeRank).compareTo(data.get(parentRank)) > 0) {
				swap(nodeRank, parentRank);
				shiftUpMax(parentRank);
			}
		}
	}
	
	public void removeMin() {
		if(data.size() != 0) {
			data.remove(0);
			data.add(0, data.get(data.size()-1));
			data.remove(data.size()-1);
			
			shiftDownMin(0);
		}
		
		else
			System.out.println("Heap is empty, cannot removeMin!");
	}
	
	public void removeMax() {
		if(data.size() != 0) {
			data.remove(0);
			data.add(0, data.get(data.size()-1));
			data.remove(data.size()-1);
			
			shiftDownMax(0);
		}
		
		else
			System.out.println("Heap is empty, cannot removeMax!");
	}
	
	public void shiftDownMin(int nodeRank) {
		if(!isExternal(nodeRank)) {
			int smallerChildRank = getSmallerChild(nodeRank);
			
			if(data.get(nodeRank).compareTo(data.get(smallerChildRank)) > 0) {
				swap(nodeRank, smallerChildRank);
				shiftDownMin(smallerChildRank);
			}
			
		}
	}
	
	public void shiftDownMax(int nodeRank) {
		if(!isExternal(nodeRank)) {
			int largerChildRank = getLargerChild(nodeRank);
			
			if(data.get(nodeRank).compareTo(data.get(largerChildRank)) < 0) {
				swap(nodeRank, largerChildRank);
				shiftDownMax(largerChildRank);
			}
			
		}
	}
	
	public String toString() {
		return data.toString();
	}
	
}
