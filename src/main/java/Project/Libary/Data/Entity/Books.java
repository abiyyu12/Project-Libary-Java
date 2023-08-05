package Project.Libary.Data.Entity;

public class Books {
    private String callno;
    private String name;
    private String auth;
    private String publisher;
    private int qty;
    private int id;
    private int issued;
    private String date;

    public int getIssued() {
        return issued;
    }

    public Books() {
    }

    public void setIssued(int issued) {
        this.issued = issued;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Books(String callno, String name, String auth, String publisher, int qty) {
        this.callno = callno;
        this.name = name;
        this.auth = auth;
        this.publisher = publisher;
        this.qty = qty;
    }
}
