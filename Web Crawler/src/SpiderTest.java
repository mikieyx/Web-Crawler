public class SpiderTest
{
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)
    {
        Spider spider = new Spider();
        spider.search("https://www.scotiabank.com/careers/en/careers/blogs/posts.the-anatomy-of-a-successful-coffee-chat.html#:~:text=Define%20your%20intent%20or%20overall,to%2020%2Dminute%20chat%20instead.", "but");
    }
}
