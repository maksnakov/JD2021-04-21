package by.it.nikitko.calc;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final Map<String, Integer> PRIORITY = Map.of(
            "=", 0,
            "+", 1, "-", 1,
            "*", 2, "/", 2
    );

    Logger logger = Logger.INSTANCE;

    Var calc(String expression) throws CalcException {
        logger.log(expression);
        expression = scopesFinder(expression);

        List<String> operands = new ArrayList<>(Arrays.asList(expression.split(Patterns.OPERATION)));
        List<String> operations = new ArrayList<>();
        Matcher matcherOperation = Pattern
                .compile(Patterns.OPERATION)
                .matcher(expression);
        while (matcherOperation.find()) {
            operations.add(matcherOperation.group());
        }

        while (operations.size() > 0) {
            int index = getIndexOperation(operations);
            String operation = operations.remove(index);
            String left = operands.remove(index);
            String right = operands.remove(index);
            String result = calcOneOperation(left, operation, right).toString();
            operands.add(index, result);
        }
        Var result = VarCreator.createVar(operands.get(0));
        logger.log(result.toString());
        return result;
       // return Var.createVar(operands.get(0));

    }

    private int getIndexOperation(List<String> operations) {
        int index = -1;
        int currentPriority = -1;
        for (int i = 0; i < operations.size(); i++) {
            String operationsString = operations.get(i);
            if (PRIORITY.get(operationsString) > currentPriority) {
                currentPriority = PRIORITY.get(operationsString);
                index = i;
            }
        }
        return index;
    }

    private Object calcOneOperation(String leftString, String operationString, String rightString) throws CalcException {

        Var right = VarCreator.createVar(rightString);
        if (operationString.equals("=")) {
            VarRepo.save(leftString, right);
            return right;
        }
        Var left = VarCreator.createVar(leftString);
        if (left == null || right == null) {
            throw new CalcException(ConsoleRunner.manager.get(Messages.INCORRECT_EXPRESSION));
        }
        switch (operationString) {
            case "+":
                return left.add(right);
            case "-":
                return left.sub(right);
            case "*":
                return left.mul(right);
            case "/":
                return left.div(right);
        }
        throw new CalcException(ConsoleRunner.manager.get(Messages.ERROR));
    }

    public String scopesFinder(String expression) throws CalcException {

        ArrayDeque<Character> expressionCharDeque = new ArrayDeque<>();
        ArrayDeque<Character> leftPartExpression = new ArrayDeque<>();
        StringBuilder currentScopeSB = new StringBuilder();
        StringBuilder result = new StringBuilder();

        char[] charArray = expression.toCharArray();
        for (char c : charArray) {
            expressionCharDeque.add(c);
        }

        while (expressionCharDeque.contains(')')) {
            currentScopeSB.delete(0, currentScopeSB.length());
            char currentChar = expressionCharDeque.pollFirst();
            leftPartExpression.add(currentChar);
            if (currentChar == ')') {
                while (currentChar != '(' & !leftPartExpression.isEmpty()) {
                    currentChar = leftPartExpression.pollLast();
                    currentScopeSB.append(currentChar);
                }
                String currScopeString = currentScopeSB.reverse().toString().replaceAll("[()]", "");
                char[] currentScopeResult = calc(currScopeString).toString().toCharArray();
                for (int i = currentScopeResult.length - 1; i >= 0; i--) {
                    expressionCharDeque.addFirst(currentScopeResult[i]);
                }
                while (!leftPartExpression.isEmpty()) {
                    expressionCharDeque.addFirst(leftPartExpression.pollLast());
                }
            }
        }
        for (Character character : expressionCharDeque) {
            result.append(character);
        }
        return result.toString();
        //  System.out.println(calc(result.toString()));
    }
}
