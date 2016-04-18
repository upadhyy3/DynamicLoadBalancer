/**
 * 
 */
import java.util.Map;
import java.util.HashMap;
/**
 * @author Piyush
 *
 */
public class CommandMap {
	

    private static final String CMD_HELP = "help";
    private static final String CMD_CONNECT = "connect";
    private static final String CMD_START = "start";
    private static final String CMD_LOAD_JOB = "ld";
    private static final String CMD_LIST_JOBS= "lsjob";

    private static Map<String, String> funcMap = new HashMap<String, String>();

    public static void init() {
        funcMap.put(CMD_HELP, "printHelp");
        funcMap.put(CMD_CONNECT, "connectNode");
        funcMap.put(CMD_START, "startNode");
        funcMap.put(CMD_LIST_JOBS, "listJobs");
        funcMap.put(CMD_LOAD_JOB, "loadJob");
    }
    
    public static String getFuncName(String str) {
        return funcMap.get(str);
    }

    public static Integer size() {
        return funcMap.size();
    }

    public static Map<String, String> getMap() {
        return funcMap;
    }
}
