package org.example;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1316">https://acm.timus.ru/problem.aspx?space=1&num=1316</a>
     */
    public Double getProfit(List<String> actionList) {
        List<Double> bit = new ArrayList<>();
        final long[] count = {0};
        actionList.forEach(operation -> {
                    String[] split = operation.split(" ");
                    Double slot = Double.parseDouble(split[1]);
                    switch (split[0]) {
                        case "BID":
                            bit.add(slot);
                            break;
                        case "SALE":
                            long countBolvanok = Long.parseLong(split[2]);
                            long countFilter = bit.stream().filter(f -> f >= slot).count();
                            count[0] = count[0] + Math.min(countBolvanok, countFilter);
                            break;
                        case "DEL":
                            bit.remove(slot);
                            break;
                    }
                }

        );
        return count[0] * 0.01;
    }

    /**
     * <h1>Задание 2.</h1>
     * Решить задачу <br/>
     * <a href="https://informatics.msk.ru/mod/statements/view.php?id=1974&chapterid=2782#1">https://informatics.msk.ru/mod/statements/view.php?id=1974&chapterid=2782#1</a><br/>
     */
    public List<Integer> getLeaveOrder(List<String> actionList) {
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < actionList.size(); i++) {
            String[] split = actionList.get(i).split(" ");
            var operation = split[0];
            var value = Integer.parseInt(split[1]);
            if (operation.equals("+")) {
                if (i - 1 >= 0 && actionList.get(i - 1).split(" ")[0].equals("?")) {
                    set.add(value + result.get(result.size() - 1));
                }else{
                    set.add(value);
                }
            } else if (operation.equals("?")) {
                result.add(set.stream()
                        .filter(f -> f >= value)
                        .min(Integer::compare)
                        .orElse(-1)
                );
            }
        }
        return result;
    }
}
