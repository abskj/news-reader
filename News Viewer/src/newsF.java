import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by sauron on 27-06-2017.
 */
public class newsF {
    public static void main(String[] args)throws IOException {
        System.out.println("Ready to start");
        String[] url={"http://timesofindia.indiatimes.com/rssfeedstopstories.cms","https://www.theguardian.com/world/rss","http://feeds.bbci.co.uk/news/world/rss.xml?edition=uk"};
        Document pages[]=new Document[3];
        for (int i=0;i<3;i++){
            pages[i]= Jsoup.connect(url[i]).get();
        }
        Elements items=pages[1].getElementsByTag("item");
        for(Element item:items){
            Elements title=item.getElementsByTag("title");
            System.out.println("TITLE\n;");
            for(Element t:title){
                String txt=t.text();
                System.out.println(txt);
            }
            title=item.getElementsByTag("description");
            System.out.println("DESCRIPTION\n;");
            for(Element t:title){
                String txt=t.text();
                System.out.println(txt);
            }
            title=item.getElementsByTag("link");
            System.out.println("FOR MORE INFO CLICK\n;");
            for(Element t:title){
                String txt=t.text();
                System.out.println(txt);
            }
            title=item.getElementsByTag("pubDate");
            System.out.println("Date\n;");
            for(Element t:title){
                String txt=t.text();
                System.out.println(txt);
            }



        }
    }
}
