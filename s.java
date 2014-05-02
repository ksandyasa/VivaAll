public class Social {
    protected String id, name, email;
    
    public Social () {
        id = "";
        name = "";
        email = "";
    }
    
    public Social(String i, String n, String e) {
        id = i;
        name = n;
        email = e;
    }
    
    public void setId(String i) {
        id = i;
    }
    
    public String getId() {
        return id;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public String getName() {
        return name;
    }
    
    public void setEmail(String e) {
        email = e;
    }
    
    public String getEmail() {
        return email;
    }
}