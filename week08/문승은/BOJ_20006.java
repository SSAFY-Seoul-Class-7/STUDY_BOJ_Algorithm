import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20006 {

    static int p, m;
    static List<Room> rooms;

    static class Player implements Comparable<Player> {
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }

    static class Room {
        int baseLevel;
        List<Player> players = new ArrayList<>();

        public Room(int baseLevel) {
            this.baseLevel = baseLevel;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());

            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            Player newPlayer = new Player(level, name);

            boolean isJoined = false;

            for (Room r : rooms) {
                if (r.players.size() < m && Math.abs(r.baseLevel - level) <= 10) {
                    r.players.add(newPlayer);
                    isJoined = true;
                    break;
                }
            }

            if (!isJoined) {
                Room newRoom = new Room(level);
                newRoom.players.add(newPlayer);
                rooms.add(newRoom);
            }

        }

        for (Room r : rooms) {
            if (r.players.size() == m) {
                sb.append("Started!").append("\n");
            } else {
                sb.append("Waiting!").append("\n");
            }
            Collections.sort(r.players);

            for (Player player : r.players) {
                sb.append(player.level).append(" ").append(player.name).append("\n");
            }
        }

        System.out.print(sb);
    }
}