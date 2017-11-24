package com.inventivestack.swipecards.widget;

/**
 * Created by akumar1 on 11/24/2017.
 */

public class LinearRegression {
    private final int N;
    private final double alpha, beta;
    private final double R2;
    private final double svar, svar0, svar1;

    public LinearRegression(float[] x, float[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        N = x.length;

        // first pass
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
        for (int i = 0; i < N; i++) sumx += x[i];
        for (int i = 0; i < N; i++) sumx2 += x[i] * x[i];
        for (int i = 0; i < N; i++) sumy += y[i];
        double xbar = sumx / N;
        double ybar = sumy / N;

        // second pass: compute summary statistics
        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < N; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        beta = xybar / xxbar;
        alpha = ybar - beta * xbar;

        // more statistical analysis
        double rss = 0.0;      // residual sum of squares
        double ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < N; i++) {
            double fit = beta * x[i] + alpha;
            rss += (fit - y[i]) * (fit - y[i]);
            ssr += (fit - ybar) * (fit - ybar);
        }

        int degreesOfFreedom = N - 2;
        R2 = ssr / yybar;
        svar = rss / degreesOfFreedom;
        svar1 = svar / xxbar;
        svar0 = svar / N + xbar * xbar * svar1;
    }

    public double intercept() {
        return alpha;
    }

    public double slope() {
        return beta;
    }

    public double R2() {
        return R2;
    }

    public double interceptStdErr() {
        return Math.sqrt(svar0);
    }

    public double slopeStdErr() {
        return Math.sqrt(svar1);
    }

    public double predict(double x) {
        return beta * x + alpha;
    }

    public String toString() {
        String s = "";
        s += String.format("%.2f N + %.2f", slope(), intercept());
        return s + "  (R^2 = " + String.format("%.3f", R2()) + ")";
    }


}