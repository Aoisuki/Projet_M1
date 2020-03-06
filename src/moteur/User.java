package moteur;

public class User {
	
	private int id;
    private String name;
    private int age;
    
    
    public User(int Id,String Name,int Age)
    {
        this.id = Id;
        this.name = Name;
        this.age = Age;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getAge()
    {
        return age;
    }
}
