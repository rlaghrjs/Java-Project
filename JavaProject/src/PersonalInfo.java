
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class PersonalInfo {
    DB_MAN DBM = new DB_MAN();
    
    String id;
    String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean checkIDPW(String id, String password){
        String strQuery = "select * from " + id +".sign_info";
        
        try{
            DBM.dbOpen();
            DBM.rs = DBM.stmt.executeQuery(strQuery);
        
            while(DBM.rs.next()){
                if(DBM.rs.getString(1).equals(id)){     // 아이디가 있는지 확인하고 일치하는지 확인
                    setId(id);
                    if(DBM.rs.getString(2).equals(password))    // 비밀번호가 일치하는지 확인되면 true 반환
                        setPassword(password);
                        return true;
                }
            }
            return false;       // id가 없고 pwd가 일치하지않으면 false 반환
        } catch(Exception e){
            System.out.println("SQLException : " + e.getMessage());
            return false;
        }
    }
    
    public boolean checkDuplication(String id){
        String strQuery = "SELECT USERNAME FROM DBA_USERS";
        
        try{
            DBM.dbOpen();
            DBM.rs = DBM.stmt.executeQuery(strQuery);
        
            if(id.isEmpty()) return true;
            while(DBM.rs.next()){
                if(DBM.rs.getString(1).equalsIgnoreCase(id)){     // 아이디가 있는지 확인하고 이미 존재하는지 확인
                        return true;
                }
            }
            setId(id);  // id 저장하기
            return false;       // id가 없으면 false 반환
        } catch(Exception e){
            System.out.println("SQLException : " + e.getMessage());
            return false;
        }
    }
    
    public boolean checkSignUp(String pw, String pwConfirm){
        boolean check = pw.equals(pwConfirm);   // 비밀번호 일치 여부 확인
        
        try{     
            if(check){
                setPassword(pw);    // 비밀번호 저장
                DBM.dbOpen();
                DBM.rs = DBM.stmt.executeQuery("alter session set \"_ORACLE_SCRIPT\"=true");    // 세션
                DBM.rs = DBM.stmt.executeQuery("create user " + getId() + " identified by " + getPassword());   // 개인 db 생성
                DBM.rs = DBM.stmt.executeQuery("grant resource, connect, dba to " + getId());   // 권한 부여
                DBM.rs = DBM.stmt.executeQuery("create table " + getId() + ".sign_info(id varchar2(20) not null, password varchar2(100) not null)");
                DBM.rs = DBM.stmt.executeQuery("create table " + getId() + ".manage(product varchar2(100) not null, category varchar(100) not null, price varchar(100), startDate date, expiredDate date)");
                //DBM.rs = DBM.stmt.executeQuery("create table " + getId() + ".stock(id varchar2(20) not null, password varchar2(100) not null)");
                DBM.rs = DBM.stmt.executeQuery("create table " + getId() + ".log(name varchar2(100) not null, counts varchar2(1000), log_day date not null, io varchar2(100) not null)");
                String stre = "insert into " + getId() +".sign_info values('" + this.id + "', '" + getPassword() + "')";
                DBM.rs = DBM.stmt.executeQuery(stre);
                DBM.dbClose();
                return true;
            }
        } catch(Exception e){
            System.out.println("SQLException : " + e.getMessage());
            return false;
        }
        return false;
    }
}
