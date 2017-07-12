import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.ling.Datum;
import edu.stanford.nlp.objectbank.ObjectBank;
import opennlp.tools.doccat.*;
import opennlp.tools.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.text.normalizer.UTF16;

import java.io.*;
import java.util.Scanner;

/**
 * Created by sauron on 07-07-2017.
 */
public class categorizer {
    public static void main(String[] args)throws IOException {
        categorizer obj=new categorizer();
        System.out.println("Creating training Set...");
       obj.create_trainingSet();
        System.out.println("Training Set Created\nCreating model...");
       obj.create_model();
       obj.categorize("profit ");
      System.out.println("\n\n\n\n\n\n\nNow testing stanfordNLP\n\n");
        obj.classifier();

    }

    public void classifier(){
        ColumnDataClassifier cdc=new ColumnDataClassifier("cdc.prop");
        Classifier<String,String> sam=cdc.makeClassifier(cdc.readTrainingExamples("headline.train"));
        for (String line :
                ObjectBank.getLineIterator("headline.test","utf-8")) {
            Datum<String,String> datum=cdc.makeDatumFromLine(line);
            System.out.println("Datum ("+line+"]\t Predicted Category: "+sam.classOf(datum));
        }

    }

    public void create_model()throws FileNotFoundException,IOException{
        DoccatModel model = null;
       try {


      MarkableFileInputStreamFactory input=new MarkableFileInputStreamFactory(new File("headline.train"));
             OutputStream dataOut =new FileOutputStream("headlineCategory.model");
        {
            ObjectStream<String> lineStream= new PlainTextByLineStream(input, "UTF-8");
            ObjectStream<DocumentSample> sampleStream =new DocumentSampleStream(lineStream);
            model = DocumentCategorizerME.train("en", sampleStream, TrainingParameters.defaultParams(), new DoccatFactory());
            OutputStream modelOut = null;
            modelOut = new BufferedOutputStream(dataOut);
            model.serialize(modelOut);
        }
        }catch (IOException e) {
// Handle exceptions
        }
    }
    public void categorize(String input)throws FileNotFoundException,IOException{
        String[] inputs={input,""};
        try(InputStream modelIn=new FileInputStream(new File("headlineCategory.model"))){
            DoccatModel model=new DoccatModel(modelIn);
            DocumentCategorizerME sam=new DocumentCategorizerME(model);
            double[] outcomes=sam.categorize(inputs);
            for (int i = 0; i < sam.getNumberOfCategories(); i++) {
                String category=sam.getCategory(i);
                System.out.println(category+" : "+outcomes[i]);
            }
        }
        catch (Exception e){
            System.out.println("There was an error\n"+e.getMessage()+"\n"+e);
        }
    }

    public void create_trainingSet()throws IOException,FileNotFoundException{
        FileReader fr=new FileReader("urlList.txt");
        Scanner scan=new Scanner(fr) ;
        FileWriter writer=new FileWriter("headline.train");
        String category="";
        int i=0;
        System.out.println(i++);
        while (scan.hasNext()) {
            System.out.println(i++);
            String token=scan.nextLine();
            System.out.println(token);
            if(token.charAt(0)!='h')
                category=token;
            else{
                Document doc= Jsoup.connect(token).get();
                Elements items=doc.getElementsByTag("item");
                for (Element item:items){
                    Elements heads=item.getElementsByTag("title");
                    for (Element head:heads){
                        String x=head.text();
                        writer.append(category+" "+x+"\n");

                    }
                }

            }
        }
    }




}
