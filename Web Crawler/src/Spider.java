import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {

	private static final int MAX_PAGES_TO_SEARCH=10;
	private Set<String> pagesVisited = new HashSet<String>();
	// we use a hash set to store pages we visited because
	// we don't want any duplicates
	// We use a linked list to store pages we want to visit, 
	// because we can add it to the end of the list, 
	// utilizing a BFS approach
	private List<String> pagesToVisit = new LinkedList<String>();
	
	/**
	 * Gets the next URL from the linked list in order and checks
	 * to make sure it hasn't been visited already
	 * @return the next url to check
	 */
	private String nextUrl() {
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		}while(this.pagesVisited.contains(nextUrl));
		
		this.pagesVisited.add(nextUrl);
		return nextUrl;
	}
	
	/**
	 * The main launching point of the spider. It creates a Crawler that will
	 * make an HTTP request and parse the web page
	 * 
	 * @param url, the starting point of the spider
	 * @param targetWord, the word that you are looking for
	 */
	public void search(String url, String targetWord) {
		while (this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) 
		{
			String currentUrl;
			Crawler crawler = new Crawler();
			
			if (this.pagesToVisit.isEmpty()) {
				currentUrl = url;
				this.pagesVisited.add(url);
			}
			else {
				currentUrl = nextUrl();
			}
			crawler.crawl(currentUrl);
			boolean success = crawler.searchForWord(targetWord);
			if (success) {
	              System.out.println(String.format("**Success** Word %s found at %s", targetWord, currentUrl));
				break;
			}
			this.pagesToVisit.addAll(crawler.getLinks());
		}
		System.out.printf("**Done** Vistited %d sites", this.pagesVisited.size());
	}
}
