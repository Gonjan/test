import java.util.*;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int nums = sc.nextInt();
            sc.nextLine();

            Map<String, Integer> sorces = new HashMap<>();
            Map<String, Integer> winBalls = new HashMap<>();
            Map<String, Integer> allBalls = new HashMap<>();
            for (int i = 0; i < nums; i++) {
                String teamName = sc.nextLine();
                winBalls.put(teamName, 0);
                allBalls.put(teamName, 0);
                sorces.put(teamName, 0);
            }

            int matchNum = (nums * (nums - 1)) / 2;

            for (int i = 0; i < matchNum; i++) {
                String[] tmp = sc.nextLine().split("\\s+");
                String[] t = tmp[0].split("-");
                String[] sorce = tmp[1].split(":");
                String a = t[0];
                String b = t[1];
                int aScore = Integer.valueOf(sorce[0]);
                int bScore = Integer.valueOf(sorce[1]);
                if (aScore > bScore) {
                    int origial = sorces.get(a);
                    sorces.put(a, origial + 3);
                    origial = winBalls.get(a);
                    winBalls.put(a, origial + aScore);
                    origial = allBalls.get(a);
                    allBalls.put(a, origial + aScore);
                } else if (aScore < bScore) {
                    int origial = sorces.get(b);
                    sorces.put(b, origial + 3);
                    origial = winBalls.get(b);
                    winBalls.put(b, origial + bScore);
                    origial = allBalls.get(b);
                    allBalls.put(b, origial + bScore);
                } else {
                    int origial = sorces.get(a);
                    sorces.put(a, origial + 1);
                    origial = winBalls.get(a);
                    winBalls.put(a, origial + aScore);
                    origial = allBalls.get(a);
                    allBalls.put(a, origial + aScore);
                    origial = sorces.get(b);
                    sorces.put(b, origial + 1);
                    origial = winBalls.get(b);
                    winBalls.put(b, origial + bScore);
                    origial = allBalls.get(b);
                    allBalls.put(b, origial + bScore);

                }
            }

            Main.ValueComparator vc = new ValueComparator();
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(sorces.entrySet());
            Collections.sort(entryList, vc);
            int count = 0;
            List<Map.Entry<String, Integer>> entryList1 = new ArrayList<Map.Entry<String, Integer>>(winBalls.entrySet());

            Collections.sort(entryList1, vc);
            List<Map.Entry<String, Integer>> entryList2 = new ArrayList<Map.Entry<String, Integer>>(allBalls.entrySet());

            Collections.sort(entryList2, vc);

            int value = -1;
            String key = "";
            List<String> res = new ArrayList<>();
            for (Entry<String, Integer> e : entryList) {
                int newValue = e.getValue();
                if ("".equals(key)) {
                    key = e.getKey();
                }
                String newKey = e.getKey();
                if (++count > nums / 2) {
                    break;
                }
                if (value != newValue) {
                    res.add(newKey);
                    value = newValue;
                } else {
                    int v1 = 0;
                    int v2 = 0;
                    for (Entry<String, Integer> e1 : entryList1) {
                        if (e1.getKey().equals(key)) {
                            v1 = e1.getValue();
                        }
                        if (e1.getKey().equals(newKey)) {
                            v2 = e1.getValue();
                        }
                    }
                    if (v1 != v2) {
                        if (v1 > v2) {
                            res.add(key);

                        } else {
                            res.add(newKey);

                        }
                    } else {
                        int v3 = 0;
                        int v4 = 0;
                        for (Entry<String, Integer> e1 : entryList2) {
                            if (e1.getKey().equals(key)) {
                                v3 = e1.getValue();
                            }
                            if (e1.getKey().equals(newKey)) {
                                v4 = e1.getValue();
                            }
                        }
                        if (v3 != v4) {
                            if (v3 > v4) {
                                res.add(key);
                            } else {
                                res.add(newKey);
                            }
                        }
                    }
                }

            }
            Collections.sort(res);
            for(String s:res){
                System.out.println(s);
            }

        }
    }

    private static class ValueComparator implements Comparator<Map.Entry<String,Integer>>
    {
        public int compare(Map.Entry<String,Integer> m,Map.Entry<String,Integer> n)
        {
            return n.getValue()-m.getValue();
        }
    }


}