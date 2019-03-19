package schultz.gsu.codinga4;

public class Test {

	public static void main(String[] args) {
		Character[] data = {'T', 'S', 'P', 'G', 'R', 'O', 'N',
				'A', 'E', 'C', 'A', 'I', 'M'};

		ArrayHeap<Character> heap = new ArrayHeap<Character>(data);
		
		System.out.println(heap);
		
		heap.removeMax();
		
		System.out.println(heap);
	}

}
