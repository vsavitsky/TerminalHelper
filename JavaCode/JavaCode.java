import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.lang.Long;

class JavaCode {

	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {

        final File folder = args.length > 0 ? new File(args[args.length-1]) : new File(".");
        if (folder == null || folder.listFiles() == null) {
        	System.out.println("Something wrong. Check params...");
        } else {
			getFilesForFolderDescription(folder);
		}
		System.out.println(ANSI_RESET);

    }

    private static void getFilesForFolderDescription (final File folder) {
    	List<String> directoryDescriptions = new ArrayList<>();
    	List<String> hiddenDirectoryDescriptions = new ArrayList<>();
    	List<String> filesDescriptions = new ArrayList<>();
    	List<String> hiddenFilesDescriptions = new ArrayList<>();
    	for (final File fileEntry : folder.listFiles()) {
    		Boolean isHidden = fileEntry.getName().charAt(0) == '.';
	        if (fileEntry.isDirectory()) {
	        	String directoryDescription = "";
	        	directoryDescription += isHidden ?  ANSI_WHITE : ANSI_YELLOW;
	            // getFilesForFolderDescription(fileEntry);
	            directoryDescription += fileEntry.getName() + " - []";
	            directoryDescription += isHidden ? ANSI_WHITE : ANSI_YELLOW;
	            if (isHidden) {
	            	hiddenDirectoryDescriptions.add(directoryDescription);
	            } else {
	            	directoryDescriptions.add(directoryDescription);
	            }
	        } else {
	        	String fileDescription = "";
	        	fileDescription += isHidden ? ANSI_WHITE : ANSI_BLUE;
	            fileDescription += fileEntry.getName() + " - " ;
	            fileDescription += " [ " + getRoundedValue(fileEntry.length()) + " ]" ;
	            fileDescription += isHidden ? ANSI_WHITE : ANSI_BLUE;
	            // will be deleted
	            // System.out.println(fileEntry.length() + " - length REAL" );
	            
	            if (isHidden) {
	            	hiddenFilesDescriptions.add(fileDescription);
	            } else {
	            	filesDescriptions.add(fileDescription);
	            }
	        }
	    }
	    // for (String directoryDescription : hiddenDirectoryDescriptions) {
	    // 	System.out.println(directoryDescription);
	    // }
	    for (String directoryDescription : directoryDescriptions) {
	    	System.out.println(directoryDescription);
	    }
	    // for (String fileDescription : hiddenFilesDescriptions) {
	    // 	System.out.println(fileDescription);
	    // }
	    for (String fileDescription : filesDescriptions) {
	    	System.out.println(fileDescription);
	    }
    }

    private static String getRoundedValue (Long size) {
    	List<String> postfixes = Arrays.asList("b","Kb","Mb","Gb","Tb");

    	Long result = size;
    	Long postResult = 0L;
    	Integer iteration = 0;
    	while (result / 1000 > 0) {
			iteration++;
			postResult = result % 1000;
    		result /= 1000;
    	}
    	return result +	"." + postResult + " " + postfixes.get(iteration);
    }
}

// java -cp "/home/vsavitsky/IdeaProjects/JavaCode/JavaCode" JavaCode $1 $2 $3