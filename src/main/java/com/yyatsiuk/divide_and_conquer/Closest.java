package com.yyatsiuk.divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class Closest {

    private static class Point {
        private final long x;
        private final long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double minimalDistance(int[] x, int[] y) {
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }

        Arrays.sort(points, Comparator.comparingLong(p -> p.x));
        double d = minDistHelper(points, 0, points.length - 1);

        Point midPoint = points[(x.length - 1) / 2];

        List<Point> strip = new ArrayList<>();
        for (Point point : points) {
            if (Math.abs(midPoint.x - (double) point.x) < d)
                strip.add(point);
        }

        strip.sort(Comparator.comparingLong(p -> p.y));

        BigDecimal bd = new BigDecimal(Double.toString(stripClosest(strip, d)));
        return bd.setScale(6, RoundingMode.HALF_UP).doubleValue();
    }

    private static double minDistHelper(Point[] points, int lo, int hi) {
        if (hi - lo <= 3) {
            return brute(points, lo, hi);
        }

        int mid = lo + (hi - lo) / 2;
        double d1 = minDistHelper(points, lo, mid);
        double d2 = minDistHelper(points, mid + 1, hi);

        return Math.min(d1, d2);
    }

    private static double stripClosest(List<Point> strip, double d) {
        double minDistance = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size(); j++) {
                double distance = calculateDistance(strip.get(i), strip.get(j));
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }

        return minDistance;
    }

    private static double brute(Point[] points, int lo, int hi) {
        double minDistance = calculateDistance(points[lo], points[lo + 1]);
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                double d = calculateDistance(points[i], points[j]);
                if (d < minDistance) {
                    minDistance = d;
                }
            }
        }

        return minDistance;
    }


    private static double calculateDistance(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.x - (double) point1.x, 2) + Math.pow(point2.y - (double) point1.y, 2));
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }

        System.out.println(String.format(Locale.US, "%.6f", minimalDistance(x, y)));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
