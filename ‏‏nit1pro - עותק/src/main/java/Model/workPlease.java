package Model;

import java.sql.Date;

public class workPlease {
    public static void main(String[]args){
        SQLModel shit = SQLModel.GetInstance();
        shit.createTable();
        //User myUser = new User("myNameHere", "sksksk", new Date(1990, 5, 2), "Yofi", "Tofi", "Nesher");
        //shit.insertRecordToTable("tbl_users", myUser);
        User myUser = new User("toDelete", "sksksk", new Date(1990, 5, 2), "Yofi", "Tofi", "Nesher");
        shit.insertRecordToTable("tbl_users", myUser);
        System.out.println(shit.selectFromTable("toDelete"));
        shit.deleteRecordFromTable(myUser);
    }
}
