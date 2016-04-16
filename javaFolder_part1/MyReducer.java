package hw2_part1;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MyReducer extends MapReduceBase
implements Reducer<Text, Text, Text, Text>{
	
	@Override
	public void reduce(Text key, Iterator<Text> 
	values, OutputCollector<Text,Text> output, Reporter reporter)throws IOException{
		

		String temp = "";
		Text text = null;
		
		int countIDF = 0;
		Text txt = new Text();
		TreeSet<String> testset = new TreeSet<String>();
		while(values.hasNext()){
			testset.add(values.next().toString());
		    countIDF++;
		}
		for(String i : testset){
			temp += i+",";
		}

		String myvalue = countIDF + "," +temp;
		output.collect(key, new Text(myvalue));
	}
	
}
