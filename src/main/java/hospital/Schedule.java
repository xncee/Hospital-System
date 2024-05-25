package hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Schedule {
    private final String id;
    private HashMap<LocalDate, List<String>> scheduleList;
    // 2024-05-24: {4 to 5, 6 to 8, 9-11}

    public Schedule(String id, HashMap<LocalDate, List<String>> scheduleList) {
        update();
        this.id = id;
        this.scheduleList = scheduleList;
    }

    public void add(LocalDate date, int from, int to) {
        // YYYY-MM-dd
        LocalTime f = LocalTime.parse(from+"");
        List<String> l = new ArrayList<>();
        l.add(f+" to "+to);
        scheduleList.put(date, l);
    }

    private void update() {
        for (LocalDate date: scheduleList.keySet()) {
            if (date.isBefore(LocalDate.now())) {
                scheduleList.remove(date);
            }
        }
    }

    public void remove(LocalDate date, int from, int to) {
        List<String> list = scheduleList.get(date);
        list.remove(from+" to "+to);

        scheduleList.put(date, list);
        if (list.size()==0) {
            scheduleList.remove(date);
        }
    }
    public boolean isAvailable(LocalDate date, int from, int to) {
        // date: from 'to' to
        //7 to 8?
        if (scheduleList.get(date).contains(from+" to "+to)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", scheduleList=" + scheduleList +
                '}';
    }
}
