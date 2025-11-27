
import java.util.Date;
import java.text.SimpleDateFormat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class Log {
    DB_MAN DBM = new DB_MAN();
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    String name;
    String counts;
    Date log_day;
    String io;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public Date getLog_day() {
        return log_day;
    }

    public void setLog_day(Date log_day) {
        this.log_day = log_day;
    }

    public String getIo() {
        return io;
    }

    public void setIo(String io) {
        this.io = io;
    }
}
