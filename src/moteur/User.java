package moteur;

import java.sql.Date;

public class User {
	
	private int id;
    private String name;
    private int age;
    private Date date;
    private boolean covid19;
    private String pays;
    private int distance;
    
    public User(int Id,String Name,int Age, Date Date, boolean Covid19,String Pays, int Distance)
    {
        this.id = Id;
        this.name = Name;
        this.age = Age;
        this.date = Date;
        this.pays = Pays;
        this.covid19 = Covid19;
        this.distance = Distance;
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
    public Date getDate() {
    	return date;
    }
    
   public String getPays() {
	   return pays;
   }
   
   public String getCovid19() {
	   if(covid19 == true) {
		   return "Oui";
	   }else {
		   	return "Non";
	   }
   }
   
   public int getDistance() {
	   return distance;
   }
}
