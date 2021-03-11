package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class FirstTaskSolution implements FirstTask {

    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        ArrayList<Integer> vertexes = new ArrayList<>();
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.offerFirst(startIndex);
        while (!que.isEmpty()) {
            int visited = que.peekFirst();
            vertexes.add(visited);
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[i][visited] ) {
                    boolean isRight = true;
                    for (Integer vertex: vertexes) {
                        if (i == vertex) {
                            isRight = false;
                            break;
                        }
                    }
                    if (isRight) que.offerLast(i);
                }
            }
            que.pollFirst();
        }
        for (int i = 0; i < vertexes.size(); i++) {
            for (int j = 0; j < vertexes.size(); j++) {
                if (vertexes.get(i).equals(vertexes.get(j)) && i != j) {
                    vertexes.remove(j);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < vertexes.size() - 1; i++) {
            result.append(vertexes.get(i)).append(",");
        }
        result.append(vertexes.get(vertexes.size() - 1));
        return result.toString();
    }

    @Override
    public Boolean validateBrackets(String s) {
        char a = '(', b = '[', c = '{';
        char a1 = ')', b1 = ']', c1 = '}';
        char[] chara = s.toCharArray();
        ArrayDeque<Character> symbols = new ArrayDeque<>();
        for (Character chars : chara) {
            if (chars == a || chars == b || chars == c) {
                symbols.add(chars);
                continue;
            }
            if (chars != a1 && chars != b1 && chars != c1) continue;
            if (symbols.isEmpty()) return false;
            if (symbols.peekLast() == a && chars == a1 || symbols.peekLast() == b && chars == b1 || symbols.peekLast() == c && chars == c1) symbols.removeLast();
        }
        return symbols.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
        String plus = "+", minus = "-", drob = "/", umnojit = "*";
        String[] strings = s.split(" ");
        Stack<Long> result = new Stack<>();
        for (String value : strings) {
            if (check(value)) {
                result.push(Long.parseLong(value));
            } else if (plus.equals(value)){
                Long temp1 = result.pop();
                Long temp2 = result.pop();
                Long result1 = temp1 + temp2;
                result.push(result1);
            } else if (minus.equals(value)) {
                Long temp1 = result.pop();
                Long temp2 = result.pop();
                Long result1 = temp1 - temp2;
                result.push(result1);
            } else if (drob.equals(value)) {
                Long temp1 = result.pop();
                Long temp2 = result.pop();
                Long result1 = temp1 / temp2;
                result.push(result1);
            } else if (umnojit.equals(value)) {
                Long temp1 = result.pop();
                Long temp2 = result.pop();
                Long result1 = temp1 * temp2;
                result.push(result1);
            } else {
                System.out.println("Неверно введены данные!");
                return 0L;
            }
        }
        Long returning = result.pop();
        if (!result.isEmpty()) {
            System.out.println("Некорректно введены данные!");
            return 0L;
        }
        return returning;
    }

    private boolean check(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
