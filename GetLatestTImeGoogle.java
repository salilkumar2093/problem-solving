package com.manager.parking;

import java.util.LinkedHashMap;

public class TrainPassenger {
	public static void main(String... args) {
		TrainPassenger obj = new TrainPassenger();
		long[] trainTime = { 100, 110, 115, 140 };
		long[] capacity = { 1, 3, 2, 2 };
		long[] passengerTimes1 = { 95, 100, 110, 115, 125, 130 };
		long[] passengerTimes2 = { 95, 100, 110 };
		long[] passengerTimes3 = { 95, 100, 110, 115, 125 };
		long[] passengerTimes4 = { 95, 105, 110, 115, 125 };
		System.out.println(obj.getLatestTime(trainTime, capacity, passengerTimes1));
		System.out.println(obj.getLatestTime(trainTime, capacity, passengerTimes2));
		// 100,110,110
		System.out.println(obj.getLatestTime(trainTime, capacity, passengerTimes3));
		System.out.println(obj.getLatestTime(trainTime, capacity, passengerTimes4));

	}

	long getLatestTime(long[] trainTime, long[] capacity, long[] passengerTimes) {
		if (trainTime.length == 0 || capacity.length == 0)
			return -1;
		trainTime = trainTime.clone();
		capacity = capacity.clone();
		long result = trainTime[trainTime.length - 1];
		if (passengerTimes.length == 0)
			return result;

		// filter zero capacity trains

		int passengerIndex = 0, trainIndex = 0;
		while (trainIndex < trainTime.length && passengerIndex < passengerTimes.length) {
			/*
			 * cases when trainIndex should increment train capacity is zero, or passenger
			 * time is greater than train time
			 */
			if (capacity[trainIndex] == 0 || trainTime[trainIndex] < passengerTimes[passengerIndex]) {
				trainIndex++;
				continue;
			}
			passengerIndex++;
			capacity[trainIndex]--;
		}

		// train last he and capacity bhi zero he toh last passenger -1
		if (capacity[capacity.length - 1] == 0) {
			return passengerTimes[passengerIndex - 1] - 1;
		}
		return result;

	}

	LinkedHashMap<Long, Integer> filterZeroCapacityTrains(LinkedHashMap<Long, Integer> trainTimesCapacity) {
		// filter logic
		LinkedHashMap<Long, Integer> result = new LinkedHashMap<Long, Integer>(trainTimesCapacity);
		return result;
	}

}
//train platform readable versio,
// list of long 

/*
 * List [10:00, 10:15, 10:30, 10:40] Capacity [1,3,2,0] passenger1 [9:55,
 * 10:00,10:10, 10:15, 10:25, 10:30] =>10:29:59 passenger2 [9:55, 10:00, 10:10]
 * =>10:30 passenger3 [9:55, 10:00, 10:10, 10:15, 10:25] =>10:30 passenger4
 * [9:55, 10:05, 10:10, 10:15, 10:25] =>10:30
 * 
 * 
 * base checks filter zero capacity trains
 * 
 * 
 * 
 */
