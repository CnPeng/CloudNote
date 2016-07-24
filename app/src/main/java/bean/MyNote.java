package bean;

/**
 * Created by Peng on 2016/7/24.
 */
public class MyNote {
    private String _id;
    private String title;
    private String content;
    private String time;



    public MyNote(String _id, String content, String time, String title) {
        this._id = _id;
        this.content = content;
        this.time = time;
        this.title = title;
    }

    public MyNote() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
