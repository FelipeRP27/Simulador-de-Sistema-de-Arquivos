import java.util.ArrayList;
import java.util.List;

public class Journal {
    private List<String> log = new ArrayList<>();

    public void addEntry(String entry) {
        log.add(entry);
    }

    public void printLog() {
        System.out.println("==== JOURNAL LOG ====");
        for (String entry : log) {
            System.out.println(entry);
        }
    }
}