import com.intellij.util.cls.ClsFormatException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sauron on 12-07-2017.
 */
public class news implements Serializable  {
    ArrayList<article> newsList;
    news(){
        newsList=new ArrayList<article>(5000);
    }

    public void download()throws IOException {
        try {
            FileReader reader = new FileReader("urlList.txt");
                    Scanner scan=new Scanner(reader);

            String category="";
            while (scan.hasNext()){

                String line=scan.nextLine();
                if(line.charAt(0)!='h'){
                    category=line;
                    continue;
                }
                System.out.println(line);

                Document doc= Jsoup.connect(line).get();
                Elements items=doc.getElementsByTag("item");
                for(Element item:items){
                    article a=new article("");
                    Elements title=item.getElementsByTag("title");
                    for(Element t:title){
                        a.setHeadline(t.text());
                        a.setCategory(category);
                    }
                    title=item.getElementsByTag("description");
                    for(Element t:title){
                       a.setDetail(t.text());
                    }
                    title=item.getElementsByTag("link");
                    for(Element t:title){
                        a.setUrl(t.text());
                    }
                    title=item.getElementsByTag("pubDate");
                    for(Element t:title){
                        a.setDate(t.text());
                    }
                    System.out.println(a.getHeadline());
                    newsList.add(a);




                }
            }

        }
        catch (Exception e){
            System.out.println("Error");
        }
    }
    public static void save(news obj)throws IOException,ClassNotFoundException
    {
        FileOutputStream fos=new FileOutputStream("newsData.ser");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.close();

    }
    public static news read()throws IOException,ClassNotFoundException{
    FileInputStream fis=new FileInputStream("newsData.ser");
    ObjectInputStream ois=new ObjectInputStream(fis);
    news obj=(news)ois.readObject();
    ois.close();
    return obj;
    }

    public void displayNewsz()throws IOException,ClassNotFoundException {
        for (int i = 0; i < newsList.size(); i++) {
            article a=newsList.get(i);
            System.out.println(a.getHeadline() + "\nCategory:" + a.getCategory() + "\n" + a.getDetail() + "\n");
        }


    }

    public static void main(String[] args) throws IOException,ClassNotFoundException{

        System.out.println("Downloading news from sources...");
        news obj=new news();
       obj.download();
       obj.displayNewsz();
        System.out.println("downloading complete...saving them to disk");
        save(obj);
        System.out.println("\n\n\n\nsaving done..displaying the result");

        news obj2=read();
        obj2.displayNewsz();
    }
}
