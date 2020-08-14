package com.yyatsiuk.greedy;

import java.util.Scanner;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        int numRefills = 0;
        int currentRefill = 0;

        int[] road = new int[stops.length + 2];
        road[0] = 0;
        road[road.length - 1] = dist;

        System.arraycopy(stops, 0, road, 1, stops.length);

        while (currentRefill < road.length - 1 && dist - tank > 0) {
            int lastRefill = currentRefill;

            while (currentRefill < road.length - 1) {
                if (road[currentRefill + 1] - road[lastRefill] <= tank) {
                    currentRefill++;
                } else {
                    break;
                }
            }

            if (currentRefill == lastRefill) {
                return -1;
            }

            if (currentRefill < road.length - 1) {
                numRefills++;
            }
        }

        return numRefills;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int[] stops = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}