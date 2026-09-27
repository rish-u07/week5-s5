import java.util.Arrays;

class A5 {

    static class Player implements Comparable<Player> {

        private String name;
        private int matchesPlayed;
        private double battingAverage;
        private boolean injured;

        public Player(String name,
                      int matchesPlayed,
                      double battingAverage,
                      boolean injured) {

            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        // Rule 1: Experience-only
        static boolean isDraftable(int matchesPlayed) {

            return matchesPlayed >= 10;
        }

        // Rule 2: Matches + fitness
        static boolean isDraftable(int matchesPlayed,
                                   boolean injured) {

            return matchesPlayed >= 5 && !injured;
        }

        // Sort by batting average in descending order
        @Override
        public int compareTo(Player other) {

            return Double.compare(
                other.battingAverage,
                this.battingAverage
            );
        }

        public String getName() {
            return name;
        }

        public static String draftAndRank(Player[] players) {

            Player[] temp = new Player[players.length];

            int count = 0;

            for (int i = 0; i < players.length; i++) {

                boolean draftable;

                if (isDraftable(players[i].matchesPlayed)) {

                    draftable = true;

                } else {

                    draftable = isDraftable(
                        players[i].matchesPlayed,
                        players[i].injured
                    );
                }

                if (draftable) {
                    temp[count] = players[i];
                    count++;
                }
            }

            // Create array containing only draftable players
            Player[] draftablePlayers =
                    Arrays.copyOf(temp, count);

            // Uses compareTo()
            Arrays.sort(draftablePlayers);

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < draftablePlayers.length; i++) {

                result.append(i + 1)
                      .append(". ")
                      .append(draftablePlayers[i].name);

                if (i < draftablePlayers.length - 1) {
                    result.append(" | ");
                }
            }

            return result.toString();
        }
    }

    public static void main(String[] args) {

        Player[] players = {

            new Player("Virat", 15, 48.0, false),

            new Player("Rahul", 7, 55.0, false),

            new Player("Sameer", 3, 60.0, false),

            new Player("Dev", 12, 20.0, true)
        };

        System.out.println(
            Player.draftAndRank(players)
        );
    }
}