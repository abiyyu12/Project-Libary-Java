package Project.Libary.Data.Entity;

public class Students {
    private int id ;
    private int id_books;

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }

    private String callno;

    public Students() {
    }

    public Students(String callno, int studentid, String name, String contact) {
        this.callno = callno;
        this.studentid = studentid;
        this.name = name;
        this.contact = contact;
    }

    private int studentid;
    private String name;
    private String contact;
    private String DATE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_books() {
        return id_books;
    }

    public void setId_books(int id_books) {
        this.id_books = id_books;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

}
