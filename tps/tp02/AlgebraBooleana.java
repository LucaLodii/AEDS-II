package tps.tp02;

public class AlgebraBooleana {
    
    public static boolean evaluateBooleanExpression(String expression, boolean[] inputs) {
        // Remove todos os espaços da expressão para facilitar a análise
        expression = expression.replaceAll("\\s+", "");
        
        return evaluateExpression(expression, inputs);
    }
    
    private static boolean evaluateExpression(String expr, boolean[] inputs) {
        if (expr.length() == 0) return false;
        
        // Verifica se é uma variável (A, B, C, etc.)
        if (expr.length() == 1 && Character.isUpperCase(expr.charAt(0))) {
            int index = expr.charAt(0) - 'A';
            return inputs[index];
        }
        
        // Verifica se é uma chamada de função
        if (expr.startsWith("and(")) {
            return evaluateAnd(expr.substring(4, expr.length() - 1), inputs);
        } else if (expr.startsWith("or(")) {
            return evaluateOr(expr.substring(3, expr.length() - 1), inputs);
        } else if (expr.startsWith("not(")) {
            return evaluateNot(expr.substring(4, expr.length() - 1), inputs);
        }
        
        return false;
    }
    
    private static boolean evaluateAnd(String expr, boolean[] inputs) {
        // Analisa argumentos separados por vírgula
        String[] args = parseArguments(expr);
        if (args.length < 2) return false;
        
        boolean result = evaluateExpression(args[0], inputs);
        for (int i = 1; i < args.length; i++) {
            result = result && evaluateExpression(args[i], inputs);
        }
        return result;
    }
    
    private static boolean evaluateOr(String expr, boolean[] inputs) {
        // Analisa argumentos separados por vírgula
        String[] args = parseArguments(expr);
        if (args.length < 2) return false;
        
        boolean result = evaluateExpression(args[0], inputs);
        for (int i = 1; i < args.length; i++) {
            result = result || evaluateExpression(args[i], inputs);
        }
        return result;
    }
    
    private static boolean evaluateNot(String expr, boolean[] inputs) {
        return !evaluateExpression(expr, inputs);
    }
    
    private static String[] parseArguments(String expr) {
        // Análise simples para argumentos separados por vírgula
        // Isso lida corretamente com parênteses aninhados
        java.util.List<String> args = new java.util.ArrayList<>();
        int parenCount = 0;
        int start = 0;
        
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') parenCount++;
            else if (c == ')') parenCount--;
            else if (c == ',' && parenCount == 0) {
                args.add(expr.substring(start, i));
                start = i + 1;
            }
        }
        args.add(expr.substring(start));
        
        return args.toArray(new String[0]);
    }
    
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            
            // Analisa a entrada
            String[] parts = line.split("\\s+");
            int n = Integer.parseInt(parts[0]);
            
            // Analisa as entradas binárias
            boolean[] inputs = new boolean[n];
            for (int i = 0; i < n; i++) {
                inputs[i] = parts[i + 1].equals("1");
            }
            
            // Extrai a expressão (tudo após as entradas)
            StringBuilder expression = new StringBuilder();
            for (int i = n + 1; i < parts.length; i++) {
                if (i > n + 1) expression.append(" ");
                expression.append(parts[i]);
            }
            
            // Avalia e exibe o resultado
            boolean result = evaluateBooleanExpression(expression.toString(), inputs);
            System.out.println(result ? "1" : "0");
        }
        
        scanner.close();
    }
}
