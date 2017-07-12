/**
 * Created by sauron on 10-07-2017.
 */
import opennlp.tools.tokenize.SimpleTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.io.*;
import java.util.Scanner;

public class categorizer01 {

    private String[] dStopWords = {"i", "a", "about", "an",
            "are", "as", "at", "be", "by", "com", "for", "from", "how",
            "in", "is", "it", "of", "on", "or", "that", "the", "this",
            "to", "was", "what", "when", "where", "who", "will", "with"};
    private static HashSet stopWords = new HashSet();
    int noc=7;

    public categorizer01(){
        stopWords.addAll(Arrays.asList(dStopWords));
    }
    public void update(String x)throws IOException,FileNotFoundException{
        BufferedReader ab=new BufferedReader(new FileReader(x));
        while(ab.ready()){
            stopWords.add(ab.readLine());
        }
        noc=0;
    }

    public static void main(String[] args)throws IOException {
        categorizer01 obj=new categorizer01();
        System.out.println("fetching headlines...");
      //  obj.fetchHeadlines();
        System.out.println("creating keywords list");
        obj.createKeywordsList();


    }
    public void createKeywordsList()throws IOException,FileNotFoundException{
        System.out.println("reading data from headLines.txt");
        FileReader reader=new FileReader("datasets\\headLines.txt");
        System.out.println("\ncreating lists...");

        Scanner ab=new Scanner(reader);
       String category;
       String[] cats=new String[noc];
       keywords[] lists=new keywords[noc];
       int i=-1;
       while(ab.hasNext()){
           String line=ab.nextLine();
           if(line.charAt(0)=='_')
           {    i++;
                cats[i]=line;
               category=line.substring(1);
               System.out.println(category);
               lists[i]=new keywords();
               lists[i].createList("datasets\\list__"+category+".txt");
           }
           SimpleTokenizer tokenizer=SimpleTokenizer.INSTANCE;
           String tokens_temp[]=tokenizer.tokenize(line);
           String tokens[]=filter(tokens_temp);
           for (String token:tokens
                ) {
               lists[i].insert(token);
           }
           }
        System.out.println("Lists created\nsaving to hard disk...");

        for (int j = 0; j <noc; j++) {
            lists[j].saveList("datasets\\list_"+cats[j]+".txt");
        }

    }
    public void fetchHeadlines()throws IOException,FileNotFoundException{
        FileReader reader=new FileReader("datasets\\urls.txt");
        Scanner scan=new Scanner(reader);
        int i=1;
        String category=scan.nextLine();
      FileWriter writer=new FileWriter("datasets\\headLines.txt");
      writer.write("_"+category);
        while(scan.hasNext()) {
            String line = scan.nextLine();
            if (line.charAt(0) != 'h') {
                category = line;
               writer.write("\n_"+category);
               System.out.println("\n"+i+"/6 done");
                i++;
                }
            /*retrieve headline
            and write them to a file
             */
           else {
                Document doc = Jsoup.connect(line).get();

                Elements items = doc.getElementsByTag("item");
                for (Element item : items) {
                    Elements heads = item.getElementsByTag("title");
                    for (Element head : heads) {
                        String x = head.text();
                        writer.write("\n" + x);

                    }
                }
            }
        }
        noc=i;

    }
    public String[] filter(String[] headline){

            ArrayList<String> tokens =
                    new ArrayList<String>(Arrays.asList(headline));
            for (int i = 0; i < tokens.size(); i++) {
                if (stopWords.contains(tokens.get(i))) {
                    tokens.remove(i);
                }
            }
            return (String[]) tokens.toArray(
                    new String[tokens.size()]);
        }
    }

