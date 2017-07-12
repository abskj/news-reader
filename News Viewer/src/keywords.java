import java.io.*;
import java.util.Scanner;

/**
 * Created by sauron on 10-07-2017.
 */
public class keywords {
    words root;
    FileWriter writer;
    public keywords(){
        root=null;
        writer=null;
    }
   public keywords(String x,double y){
        root=new words(x);
        root.setScore(y);

    }
    public void createList(String url)throws IOException,FileNotFoundException {
      //FileReader reader=new FileReader(url);
      /*  File list=new File("G:\\machine_learning\\News Viewer\\"+url);
        InputStream reader=new FileInputStream(list);
        Scanner scan = new Scanner(reader);

        while (scan.hasNext()) {
         String temp=scan.nextLine();
         double d=scan.nextDouble();
            insert(temp,d);
        }*/
    }

    public void insert(words word,String x){
        if(x.equalsIgnoreCase(word.getWord())){
            double s=word.getScore();
            word.setScore(s+1);
            return;
        }
        if(word.getWord().compareToIgnoreCase(x)<0){
            if(word.getLeft()==null){
                word.setLeft(new words(x));
            }
            else {
                insert(word.getLeft(),x);

            }
        }
        else {
            if(word.getRight()==null){
                word.setRight(new words(x));
                return;
            }
            else {
                insert(word.getRight(),x);

            }
        }
    }
    public void insert(String x){
        if(root==null){
            root=new words(x);
        }
        else insert(root,x);
    }
    public void insert(String x,double d){
       if(root==null) root=new words(x,d);
       else insert(root,x,d);
    }
    public void insert(words word,String x,double d){
        if(x.equalsIgnoreCase(word.getWord())){
            double s=word.getScore();
            word.setScore(s+1);
            return;
        }
        if(word.getWord().compareToIgnoreCase(x)<0){
            if(word.getLeft()==null){
                word.setLeft(new words(x,d));
                return;
            }
            else {
                insert(word.getLeft(),x,d);

            }
        }
        else {
            if(word.getRight()==null){
                word.setRight(new words(x,d));
                return;
            }
            else {
                insert(word.getRight(),x,d);

            }
        }
    }

    public void saveList(String filename)throws IOException{
        writer=new FileWriter(filename);
        System.out.println("\nsaving file...("+filename+")\n");
        traverse(root);
        System.out.println("saving complete");
    }

    public void traverse(words word)throws IOException{
        if(word.getLeft()!=null)
                traverse(word.getLeft());
        writer.write(word.getWord()+"\n"+word.getScore()+"\n");
        if(word.getRight()!=null)
            traverse(word.getRight());
    }
    public double getScore(String token){
        double ans=0.0;
        ans=findScore(root,token);
        return ans;
    }
    public double findScore(words word,String w){
        if(word.getWord().equalsIgnoreCase(w))
            return word.getScore();
        if(word.getWord().compareToIgnoreCase(w)<0)
        {
            if(word.getLeft()==null)
                return -1;
            else
                return findScore(word.getLeft(),w);
        }

        if(word.getRight()==null){
                return -1;
            }
            else
                return findScore(word.getRight(),w);

    }
}
