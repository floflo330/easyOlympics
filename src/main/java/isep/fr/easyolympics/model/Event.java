package isep.fr.easyolympics.model;

import java.sql.Date;





public class Event {
    private int idEvent;
    private String name;
    private String place;
    private String date;
    private String time;
    private int idSport;
    private String sportName;

    public Event(int idEvent, String name, String place, String date, String time, int idSport, String sportName) {
        this.idEvent = idEvent;
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
        this.idSport = idSport;
        this.sportName = sportName;
    }

    public Event(Date eventDate, String eventTime, String eventName, String eventSport, String eventPlace) {
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIdSport() {
        return idSport;
    }

    public void setIdSport(int idSport) {
        this.idSport = idSport;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSport() {
        return sportName;
    }
}
