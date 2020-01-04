package bgu.spl.net.srv;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class serverData {
    private static class serverDataHolder {
        private static serverData instance = new serverData();
    }

    private ConcurrentHashMap<String, List<User>> genreFollowers;

    private serverData() {
        genreFollowers = new ConcurrentHashMap<>();
    }

    public static serverData getInstance(){
        return serverDataHolder.instance;

    }

    public ConcurrentHashMap<String, List<User>> getGenreFollowers() {
        return genreFollowers;
    }
}
