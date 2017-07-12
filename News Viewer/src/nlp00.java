import com.thaiopensource.xml.tok.Tokenizer;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by sauron on 28-06-2017.
 * This class demonstrates various Tokenizers
 */
public class nlp00 {
    public static void main(String[] args)throws IOException,FileNotFoundException {
        Scanner ab=new Scanner("This simple implementation has several shortcomings. If we needed our contractions\n" +
                "to be identified and possibly split, as demonstrated with the first token, then this\n" +
                "implementation fails to do it. Also, the last word of the sentence was returned with a\n" +
                "period attached to it.");
        String txt="This simple implementation has several shortcomings. If we needed our contractions\n" +
                "to be identified and possibly split, as demonstrated with the first token, then this\n" +
                "implementation fails to do it. Also, the last word of the sentence was returned with a\n" +
                "period attached to it.";
        List<String> list=new ArrayList<>();
        while (ab.hasNext()){
            String token=ab.next();
            list.add(token);
        }
        for(String token:list){
            System.out.println(token);
        }
        System.out.println("STring split function:\n");
        String[] tokens=txt.split("\\s+");
        for(String token:list){
            System.out.println(token);
        }
        System.out.println("Apache OpenNLPTokenizer:");
        SimpleTokenizer x=SimpleTokenizer.INSTANCE;
        String[] ts=x.tokenize(txt);
        for (String token:ts){
            System.out.println(token);
        }

        System.out.println("SimpleTokenizer(OpenNLP)");
        System.out.println("APache maximum entropy tokenizer");
                try{
                    File model_file=new File("G:\\machine learning\\News Viewer\\en-token.bin");

                    InputStream x1=new FileInputStream(model_file);
                    TokenizerModel model=new TokenizerModel(x1);
                    TokenizerME tokenizer=new TokenizerME(model);
                   tokens=tokenizer.tokenize(txt);
                    for(String token:tokens){
                        System.out.println(token);
                    }

        }catch (Exception e){
                    System.out.println("Error found------\n\n"+e);
                }


    }
}
