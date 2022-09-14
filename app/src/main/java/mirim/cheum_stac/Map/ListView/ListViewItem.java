package mirim.cheum_stac.Map.ListView;

public class ListViewItem {
    private String name;
    private String location ;

    public void setTitle(String name) {
        this.name = name ;
    }
    public void setDesc(String desc) {
        this.location = desc ;
    }

    public String getTitle() {
        return this.name;
    }
    public String getDesc() {
        return this.location;
    }
}