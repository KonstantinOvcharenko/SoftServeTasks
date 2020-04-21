package sequence;

public class LongestSequence {
	public static void main(String[] args) {
	
		// Не успел уже доделать с запросом нового файла,но обязательно доделаю
		Searcher searcher = new Searcher("File1.txt");
		try {
			searcher.t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
