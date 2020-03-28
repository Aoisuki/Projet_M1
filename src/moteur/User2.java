package moteur;

import java.sql.Date;

public class User2 {
	
	private int id;
	/*
    private String name;
    private int age;
    private Date date;
    private boolean covid19;
    private String pays;
    private int distance;
    */
	private String stage;
	private Date date;
	private float distance;
	private String origin;
	private String destination;
	private String type;
	private String winner;
	private String winner_country;
	private int cmp;
    public User2(int Id,String Stage, Date Date, float Distance, String Origin, String Destination, String Type, String Winner, String Winner_Country, int Cmp)
    {
        this.id = Id;
        this.stage = Stage;
        this.date = Date;
        this.distance = Distance;
        this.origin = Origin;
        this.destination = Destination;
        this.type = Type;
        this.winner = Winner;
        this.winner_country = Winner_Country;
        this.cmp = Cmp;
    }
    
    public int getId()
    {
        return id;
    }
    
   public String getStage() {
	   return stage;
   }
   
   public Date getDate() {
	   return date;
   }
   
   public float getDistance() {
	   return distance;
   }
   
   public String getOrigin() {
	   return origin;
   }

   public String getDestination() {
	   return destination;
   }
   
   public String getType() {
	   return type;
   }
   
   public String getWinner() {
	   return winner;
   }
   
   public String getWinnerCountry() {
	   return winner_country;
   }
   
   public int getCmp() {
	   return cmp;
   }

}
