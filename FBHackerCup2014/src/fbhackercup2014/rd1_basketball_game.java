/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fbhackercup2014;

import java.util.*;

/**
 *
 * @author Siva Prasad
 */
class player {

    String name;
    int shot;
    int height;
    int draft;
    int time;
}

public class rd1_basketball_game {

    static List<player> sortPlayers(List<player> pl) {
        List<player> s_pl = new ArrayList<player>();

        for (player p : pl) {
            int idx = 0;
            for (player s : s_pl) {
                if (s.shot > p.shot) {
                    idx++;
                } else if((s.shot == p.shot) && (s.shot > p.shot)) {
                    idx++;
                }
            }
            s_pl.add(idx, p);
        }
        int idx = 1;
        for (player p : s_pl) {
            p.draft = idx;
            idx++;
        }
        return s_pl;
    }

    static List<player> incTime(List<player> pl) {
        for (player p : pl) {
            p.time++;
        }
        return pl;
    }

    static player outPlayer(List<player> pl) {
        player p = pl.get(0);
        for (player s : pl) {
            if (s.time >= p.time) {
                if (s.draft > p.draft) {
                    p = s;
                }
            }
        }
        return p;
    }

    static player inPlayer(List<player> pl) {
        player p = pl.get(0);
        for (player s : pl) {
            if (s.time <= p.time) {
                if (s.draft < p.draft) {
                    p = s;
                }
            }
        }
        return p;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int t = reader.nextInt();
        for (int i = 0; i < t; i++) {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int p = reader.nextInt();

            List<player> players = new ArrayList<player>();

            for (int j = 0; j < n; j++) {
                player pl = new player();
                pl.name = reader.next();
                pl.shot = reader.nextInt();
                pl.height = reader.nextInt();
                pl.draft = 0;

                players.add(pl);
            }
            players = sortPlayers(players);

            List<player> team1 = new ArrayList<player>();
            List<player> team2 = new ArrayList<player>();

            int idx = 1;
            for (player tp : players) {
                if (idx % 2 != 0) {
                    team1.add(tp);
                } else {
                    team2.add(tp);
                }
                idx++;
            }

            List<player> pt1 = new ArrayList<player>();
            List<player> pt2 = new ArrayList<player>();

            for (idx = 0; idx < p; idx++) {
                pt1.add(team1.remove(0));
                pt2.add(team2.remove(0));
            }

            for (idx = 0; idx < m; idx++) {
                pt1 = incTime(pt1);
                pt2 = incTime(pt2);

                if (team1.size() > 0) {
                    player t1 = outPlayer(pt1);
                    player t2 = inPlayer(team1);

                    pt1.remove(t1);
                    pt1.add(t2);
                    team1.add(t1);
                    team1.remove(t2);
                }
                if (team2.size() > 0) {
                    player t1 = outPlayer(pt2);
                    player t2 = inPlayer(team2);

                    pt2.remove(t1);
                    pt2.add(t2);
                    team2.add(t1);
                    team2.remove(t2);
                }
            }

            pt1.addAll(pt2);
            Collections.sort(pt1, new Comparator<player>() {
                public int compare(final player object1, final player object2) {
                    return object1.name.compareTo(object2.name);
                }
            });
            System.out.print("Case #" + (i + 1) + ":");
            for (player s : pt1) {
                System.out.print(" " + s.name);
            }
            System.out.println("");


        }
    }
}
