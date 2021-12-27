import java.util.Stack;

public class evaluation {
    // ==============================================================================================================================================================================
    // Question_1.1 : infix evaluation
    // =============================
    private int evaluate(int v1, char op, int v2) {
        if (op == '*') {
            return v1 * v2;
        } else if (op == '/') {
            return v1 / v2;
        } else if (op == '+') {
            return v1 + v2;
        } else {
            return v1 - v2;
        }
    }

    private int priority(char op) {
        if (op == '*' || op == '/') {
            return 2;
        } else if (op == '+' || op == '-') {
            return 1;
        } else {
            return 0;
        }
    }

    public int infixEvaluation(String exp) {
        int len = exp.length();
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<Integer> vstack = new Stack<>(); // operand stack

        for (int idx = 0; idx < len; idx++) {
            char ch = exp.charAt(idx);
            if (ch == ' ') {
                continue;
            } else if (ch >= '0' && ch <= '9') {
                // operand encountered
                vstack.push((int) (ch - '0'));
            } else if (ch == '(' || ostack.size() == 0) {
                ostack.push(ch);
            } else if (ch == ')') {
                while (ostack.peek() != '(') {
                    int val2 = vstack.pop();
                    int val1 = vstack.pop();
                    char op = ostack.pop();

                    int res = evaluate(val1, op, val2);
                    vstack.push(res);
                }
                // also pop opening bracket
                ostack.pop(); // '('
            } else {
                // operator
                while (ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    int val2 = vstack.pop();
                    int val1 = vstack.pop();
                    char op = ostack.pop();

                    int res = evaluate(val1, op, val2);
                    vstack.push(res);
                }
                ostack.push(ch); // also push the operator
            }
        }

        while (ostack.size() > 0) {
            int val2 = vstack.pop();
            int val1 = vstack.pop();
            char op = ostack.pop();

            int res = evaluate(val1, op, val2);
            vstack.push(res);
        }

        return vstack.pop();
    }

    // ==============================================================================================================================================================================
    // Question_1.2 : infix to prefix
    // ============================
    public String infixToPrefix(String exp) {
        int len = exp.length();
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<String> vstack = new Stack<>(); // operand stack

        for (int idx = 0; idx < len; idx++) {
            char ch = exp.charAt(idx);
            if (ch == ' ') {
                continue;
            } else if (ch >= '0' && ch <= '9') {
                // operand encountered
                vstack.push(ch + "");
            } else if (ch == '(' || ostack.size() == 0) {
                ostack.push(ch);
            } else if (ch == ')') {
                while (ostack.peek() != '(') {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    String res = op + val1 + val2;
                    vstack.push(res);
                }
                // also pop opening bracket
                ostack.pop(); // '('
            } else {
                // operator
                while (ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    String res = op + val1 + val2;
                    vstack.push(res);
                }
                ostack.push(ch); // also push the operator
            }
        }

        while (ostack.size() > 0) {
            String val2 = vstack.pop();
            String val1 = vstack.pop();
            char op = ostack.pop();

            String res = op + val1 + val2;
            vstack.push(res);
        }

        return vstack.pop();
    }

    // ==============================================================================================================================================================================
    // Question_1.3 : infix to postfix
    // ============================
    public String infixTopostfix(String exp) {
        int len = exp.length();
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<String> vstack = new Stack<>(); // operand stack

        for (int idx = 0; idx < len; idx++) {
            char ch = exp.charAt(idx);
            if (ch == ' ') {
                continue;
            } else if (ch >= '0' && ch <= '9') {
                // operand encountered
                vstack.push(ch + "");
            } else if (ch == '(' || ostack.size() == 0) {
                ostack.push(ch);
            } else if (ch == ')') {
                while (ostack.peek() != '(') {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    String res = val1 + val2 + op;
                    vstack.push(res);
                }
                // also pop opening bracket
                ostack.pop(); // '('
            } else {
                // operator
                while (ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    String res = val1 + val2 + op;
                    vstack.push(res);
                }
                ostack.push(ch); // also push the operator
            }
        }

        while (ostack.size() > 0) {
            String val2 = vstack.pop();
            String val1 = vstack.pop();
            char op = ostack.pop();

            String res = val1 + val2 + op;
            vstack.push(res);
        }

        return vstack.pop();
    }

    // ==============================================================================================================================================================================
    // Question_3.1 : postfix evaluation
    public int postfixEvaluation(String exp) {
        int len = exp.length();
        Stack<Integer> vstack = new Stack<>(); // operand stack
        for (int idx = 0; idx < len; idx++) {
            char ch = exp.charAt(idx);
            if (ch >= '0' && ch <= '9') {
                // operand occured
                vstack.push((int) (ch - '0'));
            } else {
                // ch == operator
                char op = ch;
                int val2 = vstack.pop();
                int val1 = vstack.pop();
                int res = evaluate(val1, op, val2);
                vstack.push(res);
            }
        }

        return vstack.pop();
    }

    // ==============================================================================================================================================================================
    // Question_3.2 : postfix to infix
    public String postfixToInfix(String exp) {
        int len = exp.length();
        Stack<String> vstack = new Stack<>(); // operand stack
        for (int idx = 0; idx < len; idx++) {
            char ch = exp.charAt(idx);
            if (ch >= '0' && ch <= '9') {
                // operand occured
                vstack.push(ch + "");
            } else {
                // ch == operator
                char op = ch;
                String val2 = vstack.pop();
                String val1 = vstack.pop();
                String res = "(" + val1 + op + val2 + ")";
                vstack.push(res);
            }
        }

        return vstack.pop();
    }

    // ==============================================================================================================================================================================
    // Question_3.3 : postfix to prefix
    public String postfixToPrefix(String exp) {
        int len = exp.length();
        Stack<String> vstack = new Stack<>(); // operand stack
        for (int idx = 0; idx < len; idx++) {
            char ch = exp.charAt(idx);
            if (ch >= '0' && ch <= '9') {
                // operand occured
                vstack.push(ch + "");
            } else {
                // ch == operator
                char op = ch;
                String val2 = vstack.pop();
                String val1 = vstack.pop();
                String res = op + val1 + val2;
                vstack.push(res);
            }
        }

        return vstack.pop();
    }

    // ==============================================================================================================================================================================
    // Question_2.1 : prefix evaluation
    public int prefixEvaluation(String exp) {
        int len = exp.length();
        Stack<Integer> vstack = new Stack<>(); // operand stack
        for (int idx = len - 1; idx >= 0; idx--) {
            char ch = exp.charAt(idx);
            if (ch >= '0' && ch <= '9') {
                // operand occured
                vstack.push(ch - '0');
            } else {
                // ch == operator
                char op = ch;
                int val1 = vstack.pop();
                int val2 = vstack.pop();
                int res = evaluate(val1, op, val2);
                vstack.push(res);
            }
        }

        return vstack.pop();
    }

    // ==============================================================================================================================================================================
    // Question_2.2 : prefix to infix
    public String prefixToInfix(String exp) {
        int len = exp.length();
        Stack<String> vstack = new Stack<>(); // operand stack
        for (int idx = len - 1; idx >= 0; idx--) {
            char ch = exp.charAt(idx);
            if (ch >= '0' && ch <= '9') {
                // operand occured
                vstack.push(ch + "");
            } else {
                // ch == operator
                char op = ch;
                String val1 = vstack.pop();
                String val2 = vstack.pop();
                String res = "(" + val1 + ch + val2 + ")";
                vstack.push(res);
            }
        }

        return vstack.pop();
    }

    // ==============================================================================================================================================================================
    // Question_2.3 : prefix to postfix
    public String prefixToPostfix(String exp) {
        int len = exp.length();
        Stack<String> vstack = new Stack<>(); // operand stack
        for (int idx = len - 1; idx >= 0; idx--) {
            char ch = exp.charAt(idx);
            if (ch >= '0' && ch <= '9') {
                // operand occured
                vstack.push(ch + "");
            } else {
                // ch == operator
                char op = ch;
                String val1 = vstack.pop();
                String val2 = vstack.pop();
                String res = val1 + val2 + ch ;
                vstack.push(res);
            }
        }

        return vstack.pop();
    }
}