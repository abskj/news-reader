/**
 * Created by sauron on 10-07-2017.
 */
public class words {
    words left,right;
    String word;
    double score;

    public words(String w){
        word=w;
        score=0.00;
    }
    public words(String w,double d){
        word=w;
        score=d;
    }
    public String getWord(){
        return word;
    }
    public double getScore(){
        return score;
    }

    public void setLeft(words left) {
        this.left = left;
    }

    public void setRight(words right) {
        this.right = right;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public words getLeft() {
        return left;
    }

    public words getRight() {
        return right;
    }

}
