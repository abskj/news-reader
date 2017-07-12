import java.io.Serializable;

/**
 * Created by sauron on 12-07-2017.
 headline,category,url,publishing date,detail

 */
public class article implements Serializable{
    String headline,category,url,pdate,detail;
    article(String headlin){
        headline=headlin;
    }

    public String getCategory() {
        return category;
    }

    public String getDetail() {
        return detail;
    }

    public String getHeadline() {
        return headline;
    }

    public String getDate() {
        return pdate;
    }

    public String getUrl() {
        return url;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public void setHeadline(String x){
        headline=x;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(String pdate) {
        this.pdate = pdate;
    }
}
