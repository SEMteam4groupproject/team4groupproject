package com.napier.team4groupproject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * The {@code App} class provides main entry point to app
 * calls methods to connect and disconnect from database
 * has calls to individual reports for testing this will not be in release
 * has commented call to menu which will be user interface
 */

public class App
{

    /**
     * Default database host
     *
     * <p>This fields holds the partial address of the database host which will be used by default.</p>
     */
    private static String databaseLocation = "localhost:33060";

    /**
     * Default connection delay
     *
     * <p>This field holds the number in milliseconds for which the database connection retries will be delayed.</p>
     */
    private static int databaseDelay = 30000;

    /**
     * Formatting method for SQL query results
     *
     * <p>This method formats the SQL query result, using metadata to find the number of columns, the needed width for
     * each column (with a minimum of 20 char), and the names of each column. If there are no rows it displays
     * 'No matching data found. Please check your spelling and try again.' since it will be caused by a user input
     * which was passed to the query.<p/>
     *
     * @param resultSet is the result of the SQL query
     * @return a formatted string containing the data from the resultSet
     */
    public static String FormatOutput(ResultSet resultSet){
        StringBuilder output = new StringBuilder();
        int rowCount = 0;

        // try in case of exceptions with ResultSet or ResultSetMetaData
        try{
            // new instance of ResultSetMetaData to get the data specific to this ResultSet
            // please note that metaData methods use indices starting at 1 instead of 0!
            ResultSetMetaData metaData = resultSet.getMetaData();

            // this gets the number of columns from metadata so we can loop through them
            int columnCount = metaData.getColumnCount();

            // this will hold display widths for each column
            int[] columnWidths = new int[columnCount];

            // get column width for each column, with a minimum width of 20 char
            for (int i = 1; i <= columnCount; i++) {
                columnWidths[i-1] = Math.max(20, metaData.getColumnDisplaySize(i));
            }

            // add line break
            output.append("\n");

            // get column label (not name, so it works with aliases) of all columns
            for (int i = 1; i <= columnCount; i++) {
                output.append(String.format("%-" + columnWidths[i-1] + "s", metaData.getColumnLabel(i)));
            }
            // add line break
            output.append("\n");

            // get every row
            while(resultSet.next()){
                // add one to row count
                rowCount++;
                // get content of each column
                for (int i = 1; i <= columnCount; i++) {
                    output.append(String.format("%-" + columnWidths[i-1] + "s", resultSet.getString(i)));
                }
                // add line break
                output.append("\n");
            }
        }
        // catch any exceptions
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        // if the query does not return any results
        if (rowCount == 0){
            output.setLength(0);
            output.append("No matching data found. Please check your spelling and try again.");
        }
        // return formatted output as a string
        return output.toString();
    }

    /**
     * Main method of the app
     *
     * <p>This is the main method of the app which is called when the app is executed.</p>
     *
     * @param args standard string array for java main class to receive command-line arguments
     */
    public static void main(String[] args) throws SQLException {
        // prints header
        System.out.println("Population Information System");

        // Creates instance of DatabaseConnection
        DatabaseConnection sql = new DatabaseConnection();

        // Set database connection arguments
        if (args.length > 1) {
            databaseLocation = args[0];
            databaseDelay = Integer.parseInt(args[1]);
        }

        // skips things using other classes if run in the unit test environment
        if (!"UnitTest".equals(System.getProperty("Environment"))) {
            // connects to database
            sql.connect(databaseLocation, databaseDelay);

            // Check for interactive mode
            if (args.length > 2 && "NON_INTERACTIVE".equalsIgnoreCase(args[2])) {
                System.setProperty("NON_INTERACTIVE", "true");
            }

            // Calls menu passes DB connection as parameters
            Menu.mainMenu(sql);

            // Disconnect from database
            sql.disconnect();
        }
    }
}