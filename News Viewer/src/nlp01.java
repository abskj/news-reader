
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import opennlp.uima.namefind.NameFinder;

import java.io.*;
import java.util.Scanner;

/**
 * Created by sauron on 29-06-2017.
 * Named Entity Recoginition using Apache OpenNLP
 * names of person:unfit for use
 * model files(en-ner-persons,etc) can be changed for the same method to recognise different entities
 * CONCLUSION-names not working for Indian names..requires custom training model
 *
 */
public class nlp01 {
    String paragraph;
    nlp01(){
        paragraph="";
    }
    public void read()throws FileNotFoundException{
        FileReader scan=new FileReader("paragraph.txt");
        Scanner in=new Scanner(scan);
        while(in.hasNext())
            paragraph+=in.nextLine();

    }
    public static void main(String[] args)throws IOException {
            nlp01 obj=new nlp01();
            obj.read();
            obj.findPlaces();


    }
    public void findNames()throws FileNotFoundException,IOException{
        try{
            File t=new File("G:\\machine learning\\nlp\\models\\en-token.bin");
            File model=new File("G:\\machine learning\\nlp\\models\\en-ner-person.bin");
            InputStream tokenStream=new FileInputStream(t);
            InputStream modelStream=new FileInputStream(model);
            TokenizerModel tokenModel=new TokenizerModel(tokenStream);
            Tokenizer tokenizer=new TokenizerME(tokenModel);
            TokenNameFinderModel entitymodel=new TokenNameFinderModel(modelStream);
            NameFinderME nameFInder=new NameFinderME(entitymodel);


            String[] tokens=tokenizer.tokenize(paragraph);
            Span nameSpans[]=nameFInder.find(tokens);

            for (int i = 0; i <nameSpans.length ; i++) {
                System.out.println("Span: "+nameSpans[i].toString());
                System.out.println("Entity: "+tokens[nameSpans[i].getStart()]);
            }



        }
        catch (Exception e)
        {
            System.out.println("Error found\n"+e);
        }

    }

    public void findPlaces()throws FileNotFoundException,IOException{
        try{
            File t=new File("G:\\machine learning\\nlp\\models\\en-token.bin");
            File model=new File("G:\\machine learning\\nlp\\models\\en-ner-location.bin");
            InputStream tokenStream=new FileInputStream(t);
            InputStream modelStream=new FileInputStream(model);
            TokenizerModel tokenModel=new TokenizerModel(tokenStream);
            Tokenizer tokenizer=new TokenizerME(tokenModel);
            TokenNameFinderModel entitymodel=new TokenNameFinderModel(modelStream);
            NameFinderME nameFInder=new NameFinderME(entitymodel);


            String[] tokens=tokenizer.tokenize(paragraph);
            Span nameSpans[]=nameFInder.find(tokens);

            for (int i = 0; i <nameSpans.length ; i++) {
                System.out.println("Span: "+nameSpans[i].toString());
                System.out.println("Place: "+tokens[nameSpans[i].getStart()]);
            }



        }
        catch (Exception e)
        {
            System.out.println("Error found\n"+e);
        }

    }

}