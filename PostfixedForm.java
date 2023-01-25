package ProiectTrei;

import java.util.NoSuchElementException;

public class PostfixedForm {
    // Postfixed (Fields)
    private MyStack<Character> operatorStack;
    private MyQueue<Character> outputQueue;

    // Evaluate Postfixed (Fields)
    private MyStack<Integer> evaluateStack;


    // Le Empty Constructor -------------------------------------------------------------------------------------------
    public PostfixedForm() {

    }


    // Create Postfixed -----------------------------------------------------------------------------------------------
    public String postFixed(String s) {
        operatorStack = new MyStack<>();
        outputQueue = new MyQueue<>();

        char element;

        for (int i = 0; i < s.length(); i++) {
            element = s.charAt(i);

            if (Character.isDigit(element)) {
                outputQueue.addElement(element);
            }
            else if (precedence(element) > 12) {
                operatorStack.addElement(element);
            }
            else if (precedence(element) > 0) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.readElement()) >= precedence(element)) {
                    outputQueue.addElement(operatorStack.removeElement());
                }
                operatorStack.addElement(element);
            }
            else if (element == '(') {
                operatorStack.addElement(element);
            }
            else if (element == ')') {
                while (operatorStack.readElement() != '(') {
                    if (operatorStack.isEmpty()) {
                        return "Parantezele nu sunt puse bine !!!";
                    }
                    outputQueue.addElement(operatorStack.removeElement());

                    if (operatorStack.readElement() == '(') {
                        operatorStack.removeElement();
                        break;
                    }
                }

                if (!operatorStack.isEmpty()) {
                    if (precedence(operatorStack.readElement()) > 12) {
                        outputQueue.addElement(operatorStack.removeElement());
                    }
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            if (operatorStack.readElement() == '(') {
                operatorStack.removeElement();
            }
            outputQueue.addElement(operatorStack.removeElement());
        }
        return printValues(outputQueue);
    }

    private String printValues(MyQueue<Character> q) {
        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            sb.append(q.removeElement());
        }
        return String.valueOf(sb);
    }

    private int precedence(char c) {
        switch (c) {
            case '+', '-':
                return 11;
            case '*', '/':
                return 12;
            case '^':
                return 13;
        }
        return -1;
    }


    // Evaluate Postfixed ---------------------------------------------------------------------------------------------
    public int evaluatePostfixed(String s) {
        evaluateStack = new MyStack<>();
        char element;
        int op1;
        int op2;

        for (int i = 0; i < s.length(); i++) {
            element = s.charAt(i);

            if (Character.isDigit(element)) {
                evaluateStack.addElement(element - '0');
            }
            else {
                try {
                    op1 = evaluateStack.removeElement();
                    op2 = evaluateStack.removeElement();
                } catch (NoSuchElementException e) {
                    System.out.println("Expresia postfixata este gresita !!! -> " + e);
                    return -1;
                }

                switch (element) {
                    case '+':
                        evaluateStack.addElement(op2 + op1);
                        break;
                    case '-':
                        evaluateStack.addElement(op2 - op1);
                        break;
                    case '*':
                        evaluateStack.addElement(op2 * op1);
                        break;
                    case '/':
                        evaluateStack.addElement(op2 / op1);
                        break;
                    case '^':
                        evaluateStack.addElement(powerOfANr(op2, op1));
                        break;
                    default:
                        System.out.println("Expresia postfixata este gresita !!!");
                        return -1;
                }
            }
        }
        return printIntValues(evaluateStack);
    }

    private int powerOfANr(int op2, int op1) {
        if (op1 == 0) {
            return 1;
        }
        else {
            return op2 * powerOfANr(op2, --op1);
        }
    }

    private int printIntValues(MyStack<Integer> stack) {
        int result = 0;

        while (!stack.isEmpty()) {
            result = result * 10 + stack.removeElement();
        }
        return result;
    }
}