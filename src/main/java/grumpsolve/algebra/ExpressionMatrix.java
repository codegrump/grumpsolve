package grumpsolve.algebra;

public class ExpressionMatrix {

    private final int m, n;
    private final Expression[] data;

    public ExpressionMatrix(int m, int n) {
        this.m = m;
        this.n = n;
        data = new Expression[m * n];

        for (int i = 0; i < data.length; i++) {
            data[i] = Expressions.ZERO;
        }
    }

    public Expression get(int row, int col) {
        return data[index(row, col)];
    }

    public void set(int row, int col, Expression expression) {
        data[index(row, col)] = expression;
    }

    public void set(int row, int col, double v) {
        data[index(row, col)] = Expressions.c(v);
    }

    public void add(int row, int col, Expression expression) {
        int index = index(row, col);
        data[index] = Expressions.add(data[index], expression);

    }

    private int index(int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException();
        }
        return row + col * m;
    }

    public ExpressionMatrix mult(ExpressionMatrix b) {
        if (n != b.m) {
            throw new IndexOutOfBoundsException();
        }
        ExpressionMatrix c = new ExpressionMatrix(m, b.n);
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < b.n; j++) {
                for (int k = 0; k < this.n; k++) {
                    c.add(i, j, Expressions.mult(this.get(i, k), b.get(k, j)));
                }
            }
        }
        return c;
    }
}
