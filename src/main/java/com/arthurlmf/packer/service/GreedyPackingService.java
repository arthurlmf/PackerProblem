package com.arthurlmf.packer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.arthurlmf.packer.model.Packable;
import com.arthurlmf.packer.model.Thing;
/**
 * A class that provides an greedy implementation of a Packing Service.
 * @author arthurfarias
 * 
 */

public class GreedyPackingService implements PackingService {
	
	//unicode value for euro sign
	private static final String EURO = "\u20AC";

	private String filePath;
	private String output; 
	//this map stores the initial map after reading from file
	private final Map<Integer, List<Packable>> initialMap; 
	//this other map stores the final map after calculation
	private final Map<Integer, List<Packable>> resultMap;
	private static PackingService instance;// Singleton pattern
	
	/**
	 * Returns a new PackingService instance based on the greedy statregy
	 * @return a new PackingService instance
	 */
	public static PackingService getInstance() {
		if (instance == null) {
			instance = new GreedyPackingService();
		}
		return instance;
	}
	/**
	 * Private constructor of this class
	 */
	private GreedyPackingService() {
		initialMap = createMap();
		resultMap = createMap();
	}
	/**
	 * Provides the greed solution implementation for an packing service
	 * @param filePath the absolute path to a test file as a String
	 * @return the solution of a packing challenge
	 */
	
	@Override
	public String pack(String filePath) {
		this.filePath = filePath;
		initMapping();
		calculate();
		prepareOutput();

		return output;
	}

	/**
	 * Create a initial mapping before calculation. It maps from an Integer as weight 
	 * of a pack to its List of Packables (which includes Things)
	 */
	private Map<Integer, List<Packable>> initMapping() {
		
		//open the file and for each line run the consumer implementation
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
        	// creates a consumer
        	Consumer<String> consumer = m -> {
    			Stream<String> linesStream = Stream.of(m);
    			
    			//the line must be splitted by colom
    			List<String> list = linesStream		 	
    				 	.map(str -> str.split(":"))
    				 	.filter(t -> t.length > 1) 
    				 	.flatMap(array->Arrays.stream(array))
    				 	.map(str -> str.trim().replace("(", "").replace(")", ""))
    					.collect(Collectors.toList());
    			
    			//take the first part: a number which goes to packageWeight
    			Integer packageWeight = Integer.parseInt(list.get(0));
    			//take the second part: a string of things
    			Stream<String> thingsStream = Stream.of(list.get(1).split(" "));
    			
    			//the things must be splitted by comma
    			List<Packable> collect = thingsStream
    					.map(thing -> thing.split(","))
    					.filter(t -> t.length > 1) 
    					.map(t ->  createPackable(t[0],t[1], t[2])) // create a Thing for each thing in the sintrg
    					//Sort them by using Comparable interface (Strategy pattern). 
    					//The compareTo method implemented using a greedy approach.
    					.sorted() 
    					.collect(Collectors.toList());
    	        
    			initialMap.put(packageWeight, collect); // stores in the initial map
    		};
        	//for eacho line execute a consumer
        	stream.forEach(consumer);
        	
        	
		} catch (IOException e) {
            e.printStackTrace();
        }
		return initialMap;
	}
	/**
	 * Calculates the solution using the greedy algorithm
	 */
	private void calculate() {
		Set<Entry<Integer, List<Packable>>> entrySet = initialMap.entrySet();
		
		//iterate over the entries 
		for (Entry<Integer, List<Packable>> entry : entrySet) {
			//the map key is the weight limit
			Integer weighLimit = entry.getKey();
			//creates a result list. I choose ArrayList because
			//the number of elements is not big. 
			List<Packable> resultList = new ArrayList<Packable>();
			//a balance is needed to specify the remaining weight in the package 
			Double balance = weighLimit.doubleValue();
			
			//for each Packable in the list
			for (Packable thing : initialMap.get(weighLimit)) {
				//As the packables are sorted properly. 
				//A greedy approach is to get the most valuable possible: a local optimal
				// So add the most valuable if the balance permits
				if (thing.getWeight() <= balance) {
					resultList.add(thing);  
					balance -= thing.getWeight(); //update the remaining balance
				}
			}
			resultMap.put(weighLimit, resultList); // populate the result Map
		}
	}
	/**
	 * Prepare the output field that must be returned as a solution
	 */
	private void prepareOutput() {
		String result = "";
		Set<Entry<Integer, List<Packable>>> entrySet = resultMap.entrySet();
		
		// for each entry in the resultMap 
		for (Entry<Integer, List<Packable>> entry : entrySet) {
			List<Packable> list = resultMap.get(entry.getKey()); // get the list of packables
			if (list.isEmpty()) {
				result += "-"; 		// if there is no packable in the list then use "-"
			} else {
				for (Packable thing : list) { // for each packable in the list retrieve this number
					result += thing.getNumber() + ",";
				}
				result = result.substring(0, result.length() - 1);
			}
			result += "\n";
		}
		output = result.trim();
	}
	
	
	/**
	 * A Factory Method that creates a packable with given string parameters. 
	 * They should be number as a string 
	 * @param number the thing number as String  
	 * @param weight the thing weight as String
	 * @param cost the thing cost as String
	 */
	protected Packable createPackable(String number, final String weight, final String cost) {
		return new Thing(Integer.parseInt(number), Double.parseDouble(weight),
				Double.parseDouble(cost.replace(EURO, "")));
	}
	/**
	 * A Factory Method that creates a Map for the greedy packing service
	 * @return Map<Integer, List<Packable>> a map
	 */
	protected Map<Integer, List<Packable>> createMap() {
		return new LinkedHashMap<Integer, List<Packable>>();
	}
}
