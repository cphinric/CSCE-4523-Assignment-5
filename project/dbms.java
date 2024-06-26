import java.sql.*;
public class dbms {
    private Connection connection;
    private Statement statement;

    public dbms(){
        connection = null;
        statement = null;
    }
    public static void main(String[] args){

        //set up connection to database
        String username = "cphinric";
        String mySQLpassword = "Pahx5loh";
        dbms dbms = new dbms();
        try{
            dbms.initDatabase(username, mySQLpassword);
        }catch(Exception e){
            e.printStackTrace();
        }

        //find out what to do
        if(args[0].equals("addGame")){
            int gameID = dbms.getNextGameID();
            int teamID1 = dbms.getTeamID(args[1]);
            int teamID2=dbms.getTeamID(args[2]);
            String score1=args[3];
            String score2=args[4];
            String date=args[5];
            String query = 
                "INSERT INTO GAME(gameID, teamID1, teamID2, score1, score2, date) "+
                "VALUES(" + gameID + ", '"+ teamID1 + "', '" + teamID2 + "', '" +
                            score1 + "', '" + score2 + "', '" +
                            date + "');";
            //System.out.print(query);
            if(dbms.updateAndConfirm(query)){
                dbms.SQLQueryToHTMLTable("SELECT * FROM GAME WHERE GameID = "+gameID+";");
            }else{
                System.out.print("<p>Error adding game</p>");
            }
        }
        else if(args[0].equals("addPlayer")){
            //I think, if you input first and last name, you will break the code
            //it might see it as 2 seperate arguments and get confused
            //should fix
            int playerID = dbms.getNextPlayerID();
            int teamID = dbms.getTeamID(args[1]);
            String name = args[2];
            String position = args[3];
            String query = 
                "INSERT INTO PLAYER(PlayerID, TeamID, Name, Position)"+
                "VALUES("+
                    playerID+", "+
                    teamID+", '"+
                    name+"', '"+
                    position+"');";
            //System.out.print(query);
            if(dbms.updateAndConfirm(query)){
                dbms.SQLQueryToHTMLTable("SELECT * FROM PLAYER WHERE PlayerID = "+playerID+";");    
            }else{
                System.out.print("<p>Error adding player</p>");
            }

        }
        else if(args[0].equals("viewPlayersOnTeam")){
            int team = dbms.getTeamID(args[1]);
            if(team != 0){
                String s = "SELECT TEAM.Location, TEAM.Nickname, PLAYER.Name, PLAYER.Position " +
                            "FROM PLAYER " +
                            "INNER JOIN TEAM ON PLAYER.TeamID = TEAM.TeamID " +
                            "WHERE PLAYER.TeamID = " + team + ";";
                //System.out.print(s);
                dbms.SQLQueryToHTMLTable(s);
            }else{
                System.out.print("<h2>Invalid team name!</h2><br>" +
                "<p>Remember, you need to use the nickname, not the location.<br>" +
                "\'Cowboys\'' would work, but not \'Dallas\'</p>");
            }
        }
        else if(args[0].equals("viewPlayersOnTeamByPosition")){
            int team = dbms.getTeamID(args[1]);
            String position = args[2];
            if(team != 0){
                String s = "SELECT Location, Nickname, Name, Position FROM PLAYER " +
                            "INNER JOIN TEAM ON PLAYER.TeamID = TEAM.TeamID " +
                            "WHERE PLAYER.TeamID = " + team + " " + 
                            "AND PLAYER.Position = '"+ position + "';";
                dbms.SQLQueryToHTMLTable(s);
            }else{
                System.out.print("<h2>Invalid team name!</h2><br>" +
                "<p>Remember, you need to use the nickname, not the location.<br>" +
                "\'Cowboys\'' would work, but not \'Dallas\'</p>");
            }
        }
        else if(args[0].equals("viewTeams")){
            String query = 
                "SELECT  " +
                    "Winner.Location, " +
                    "Winner.Nickname, " +
                    "Winner.Conference, " +
                    "COUNT(CASE " +
                        "WHEN GAME.Score1 > GAME.Score2 THEN GAME.TeamID1 " +
                        "WHEN GAME.Score1 < GAME.Score2 THEN GAME.TeamID2 " +
                    "END) AS Wins, " +
                    "COUNT(CASE " +
                        "WHEN GAME.Score1 > GAME.Score2 AND Winner.Conference = Loser.Conference then GAME.TeamID1 " +
                        "WHEN GAME.Score1 < GAME.Score2 AND Winner.Conference = Loser.Conference then GAME.TeamID2 " +
                    "END) AS 'Conference Wins' " +
                "FROM TEAM as Winner " +
                "LEFT JOIN GAME ON Winner.TeamID = " +
                    "CASE " +
                        "WHEN GAME.score1 > GAME.score2 THEN GAME.TeamID1 " +
                        "WHEN GAME.score1 < GAME.score2 THEN GAME.TeamID2 " +
                    "END " +
                "LEFT JOIN TEAM AS Loser ON Loser.TeamID = " +
                    "CASE " +
                        "WHEN Winner.TeamID = GAME.TeamID1 THEN GAME.TeamID2 " +
                        "WHEN Winner.TeamID = GAME.TeamID2 THEN GAME.TeamID1 " +
                    "END " +
                "GROUP BY Winner.TeamID " +
                "ORDER BY Winner.Conference ASC, Wins DESC, 'Conference Wins' DESC;";
            
            dbms.SQLQueryToHTMLTable(query);
        }
        else if(args[0].equals("viewGames")){
            /**
             * currently mostly works.
             * The column names should probably change
             * to better show who's home and away
             * but thats a problem for later
            */
            int team = dbms.getTeamID(args[1]);
            String query = "SELECT GAME.Date, " +
                "HomeTeam.Location AS HomeLocation, HomeTeam.Nickname AS HomeNickname, " +
                "GAME.Score1 AS HomeScore, GAME.Score2 AS AwayScore, " +
                "AwayTeam.Location AS AwayLocation, AwayTeam.Nickname AS AwayNickname, " +
                "CASE " +
                    "WHEN GAME.Score1 > GAME.Score2 AND HomeTeam.TeamID = '" + team + "' THEN 'Won' " +
                    "WHEN GAME.Score1 < GAME.Score2 AND AwayTeam.TeamID = '" + team + "' THEN 'Won' " +
                    "WHEN GAME.Score1 > GAME.Score2 AND AwayTeam.TeamID = '" + team + "' THEN 'Lost' " +
                    "WHEN GAME.Score1 < GAME.Score2 AND HomeTeam.TeamID = '" + team + "' THEN 'Lost' " +
                    "ELSE 'Draw' " +
                "END AS Result " +
                "FROM GAME " +
                "INNER JOIN TEAM AS HomeTeam ON GAME.TeamID1 = HomeTeam.TeamID " +
                "INNER JOIN TEAM AS AwayTeam ON GAME.TeamID2 = AwayTeam.TeamID " +
                "WHERE HomeTeam.TeamID = '" + team + "' " +
                "OR AwayTeam.TeamID = '" + team + "';";
            dbms.SQLQueryToHTMLTable(query);
        }
        else if(args[0].equals("viewAllOnDate")){
            //System.out.print("date: " + args[1] +"<br>");
            String query =  "SELECT " +
                            "GAME.Date AS Date, HomeTeam.Location AS Location, " +
                            "HomeTeam.Location AS 'Home', HomeTeam.Nickname AS 'Team', " +
                            "GAME.Score1 AS 'Home Score', GAME.Score2 AS 'Away Score', " +
                            "AwayTeam.Location AS 'Away', AwayTeam.Nickname AS 'Team' " + 
                            "FROM GAME " +
                            "INNER JOIN TEAM AS HomeTeam ON GAME.TeamID1 = HomeTeam.TeamID " +
                            "INNER JOIN TEAM AS AwayTeam ON GAME.TeamID2 = AwayTeam.TeamID " +
                            "WHERE GAME.Date = '" + args[1] + "';";
            //System.out.print("query: "+ query +"<br>");
            dbms.SQLQueryToHTMLTable(query);
        }
        else{
            System.out.println("<p>The wrong arguments were passed to java</p>");
        }

        //disconnect
        try{
            dbms.disconnect();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // init and testing
    // Assumes that the tables are already created
    public void initDatabase(String Username, String Password) throws SQLException {

        connect(Username, Password);
        // create a statement to hold mysql queries
        statement = connection.createStatement();
    }

    //connect to the database
    public void connect(String username, String mySQLpassword) throws SQLException {
        try {
            String url = "jdbc:mysql://localhost/" + username + "?useSSL=false";
            //System.out.println(url);
            connection = DriverManager.getConnection(url, username, mySQLpassword);
        } catch (Exception e) {
            throw e;
        }
    }

    //disconnect from the database
    public void disconnect() throws SQLException{

        connection.close();
        statement.close();
    }

    /**
     * @param
     *          s query to be executed, as a string
     * @return
     *          nothing
     * @purpose
     *          prints result of query s, as an HTML table
     */
    public void SQLQueryToHTMLTable(String s){
        try {
            ResultSet data = statement.executeQuery(s);
            ResultSetMetaData metadata = data.getMetaData();
            int numColumns = metadata.getColumnCount();
            
            System.out.print("<table id=\"queryTable\"><tr>");
            for (int i = 1; i <= numColumns; i++) {
                System.out.print("<th>"+metadata.getColumnLabel(i)+"</th>");
            }
            System.out.print("</tr>");
            String columnValue;
            while(data.next()){
                System.out.print("<tr>");
                for (int i = 1; i <= numColumns; i++) {
                    columnValue = data.getString(i);
                    System.out.print("<td>"+columnValue+"</td>");
                }
                System.out.print("</tr>");
            }
            System.out.print("</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param
     *      s string to be sent to SQL
     * @return 
     *      boolean representing whether the query was processed
     * @purpose
     *      attempts to update the database
     *      returns true if the database changed at all
     *      returns false otherwise
     */
    public boolean updateAndConfirm(String s){
        try{
            return statement.executeUpdate(s) > 0;
        }catch(Exception e){
            e.printStackTrace();
        }return false;
        
    }
    /**
     * 
     * @param
     *      nickname string containing the nickname of a team
     * @return
     *      int representing TeamID,
     *      or 0 if team not found
     * @notes
     *      Must be the nickname of the team, not the location
     */
    public int getTeamID(String nickname){
        try{
            String query = "SELECT TeamID FROM TEAM " +
                           "WHERE Nickname = '"+nickname+"';";
            ResultSet data = statement.executeQuery(query);
            while(data.next()){
                return data.getInt(1);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }return 0;
    }
    /**
     * 
     * @param
     *      none
     * @return
     *      maximum game ID + 1
     *      or 0 if an error occurred
     */
    public int getNextGameID(){
        try{
            String query = "SELECT MAX(GameID) FROM GAME;";
            ResultSet data = statement.executeQuery(query);
            while(data.next()){
                return (data.getInt(1)) + 1;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }return 0;
    }
    /**
     * 
     * @param
     *      none
     * @return
     *      maximum player ID + 1
     *      or 0 if an error occurred
     */
    public int getNextPlayerID(){
        try{
            String query = "SELECT MAX(PlayerID) FROM PLAYER;";
            ResultSet data = statement.executeQuery(query);
            while(data.next()){
                return (data.getInt(1)) + 1;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }return 0;
    }
}
